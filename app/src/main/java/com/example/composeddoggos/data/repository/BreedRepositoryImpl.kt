package com.example.composeddoggos.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.composeddoggos.data.local.BreedDatabase
import com.example.composeddoggos.data.local.BreedsList
import com.example.composeddoggos.data.mapper.toBreed
import com.example.composeddoggos.data.mapper.toBreedArrayList
import com.example.composeddoggos.data.mapper.toBreedEntity
import com.example.composeddoggos.data.mapper.toDoggoURLs
import com.example.composeddoggos.data.remote.DoggosAPI
import com.example.composeddoggos.domain.model.Breed
import com.example.composeddoggos.domain.model.DoggoURLs
import com.example.composeddoggos.domain.repository.BreedRepository
import com.example.composeddoggos.util.Resource
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONException
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class BreedRepositoryImpl @Inject constructor(
    private val api: DoggosAPI,
    private val db: BreedDatabase
) : BreedRepository {
    private val dao = db.dao

    @RequiresApi(Build.VERSION_CODES.N)
    override suspend fun getBreeds(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<Breed>>> {
        return flow {
            emit(Resource.Loading(true))
            val localBreed = dao.searchBreed(query)
            emit(Resource.Success(data = localBreed.map { it.toBreed() }))
            val isDbEmpty = localBreed.isEmpty() && query.isBlank()
            val shouldLoadFromCache = !isDbEmpty && !fetchFromRemote
            var breeds: ArrayList<Breed> = ArrayList()
            if (shouldLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }
            val remoteBreed = try {
                val response = api.getBreeds()
                response
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }
            try {
                val body = remoteBreed?.string()
                if (body != null) {
                    val breedsList = Gson().fromJson(body, BreedsList::class.java)
                    breeds = breedsList.toBreedArrayList()
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }


            breeds.let { breed ->
                dao.clearBreeds()
                dao.insertBreeds(breeds.map { it.toBreedEntity() })
                emit(Resource.Success(data = dao.searchBreed("").map { it.toBreed() }))
                emit(Resource.Loading(false))

            }
        }
    }

    override suspend fun getDoggoURLs(breed: String): Resource<DoggoURLs> {
        val response = api.getPhotoURLs(breed)
        return try {
            Resource.Success(data = response.toDoggoURLs())
        } catch (e: IOException) {
            e.printStackTrace()
            Resource.Error("Couldnt load Doggo URLs")
        } catch (e: HttpException) {
            e.printStackTrace()
            Resource.Error("Couldnt load Doggo URLs")
        }
    }
}