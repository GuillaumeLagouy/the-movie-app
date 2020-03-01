package com.gmail.eamosse.idbdata.datasources

import com.gmail.eamosse.idbdata.api.response.*
import com.gmail.eamosse.idbdata.api.response.CategoryResponse
import com.gmail.eamosse.idbdata.api.response.MovieDetailResponse
import com.gmail.eamosse.idbdata.api.response.MoviesResponse
import com.gmail.eamosse.idbdata.api.response.TokenResponse
import com.gmail.eamosse.idbdata.api.service.MovieService
import com.gmail.eamosse.idbdata.data.Token
import com.gmail.eamosse.idbdata.utils.Result
import com.gmail.eamosse.idbdata.utils.parse
import com.gmail.eamosse.idbdata.utils.safeCall
import retrofit2.Response

/**
 * Manipule les données de l'application en utilisant un web service
 * Cette classe est interne au module, ne peut être initialisé ou exposé aux autres composants
 * de l'application
 */
internal class OnlineDataSource(private val service: MovieService) {

    /**
     * Récupérer le token du serveur
     * @return [Result<Token>]
     * Si [Result.Succes], tout s'est bien passé
     * Sinon, une erreur est survenue
     */
    suspend fun getToken(): Result<TokenResponse> {
        return safeCall {
            val response = service.getToken()
            response.parse()
        }
    }

    suspend fun getCategories(): Result<List<CategoryResponse.Genre>> {
        return safeCall {
            val response = service.getCategories()
            when (val result = response.parse()) {
                is Result.Succes -> Result.Succes(result.data.genres)
                is Result.Error -> result
            }
        }
    }

    suspend fun getListMovies(categoryId: Int): Result<List<MoviesResponse.Result>> {
        return safeCall {
            val response = service.getListMovies(categoryId)
            when (val result = response.parse()) {
                is Result.Succes -> Result.Succes(result.data.results)
                is Result.Error -> result
            }
        }
    }

    suspend fun getMovieDetail(movieId:Int): Result<MovieDetailResponse>{
        return safeCall {
            val response = service.getMovieDetail(movieId)
            when (val result = response.parse()) {
                is Result.Succes -> Result.Succes(result.data)
                is Result.Error -> result
            }
        }
    }

    suspend fun getSimilarMovies(movieId:Int): Result<List<MoviesResponse.Result>> {
        return safeCall {
            val response = service.getSimilarMovies(movieId)
            when (val result = response.parse()) {
                is Result.Succes -> Result.Succes(result.data.results)
                is Result.Error -> result
            }
        }
    }

    suspend fun getTrendingMovies(): Result<List<TrendingMoviesResponse.Result>>{
        return safeCall {
            val response : Response<TrendingMoviesResponse> = service.getTrendingMovies()
            when (val result = response.parse()) {
                is Result.Succes -> Result.Succes(result.data.results)
                is Result.Error -> result
            }
        }
    }

    suspend fun getTrendingPeople(): Result<List<TrendingPersonResponse.Person>>{
        return safeCall {
            val response : Response<TrendingPersonResponse> = service.getTrendingPeople()
            when (val result = response.parse()) {
                is Result.Succes -> Result.Succes(result.data.results)
                is Result.Error -> result
            }
        }
    }
}

