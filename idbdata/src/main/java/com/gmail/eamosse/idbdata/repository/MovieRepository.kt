package com.gmail.eamosse.idbdata.repository

import com.gmail.eamosse.idbdata.api.response.*
import com.gmail.eamosse.idbdata.api.response.toCategory
import com.gmail.eamosse.idbdata.api.response.toEntity
import com.gmail.eamosse.idbdata.api.response.toMovie
import com.gmail.eamosse.idbdata.api.response.toToken
import com.gmail.eamosse.idbdata.data.*
import com.gmail.eamosse.idbdata.datasources.LocalDataSource
import com.gmail.eamosse.idbdata.datasources.OnlineDataSource
import com.gmail.eamosse.idbdata.utils.Result
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * La classe permettant de gérer les données de l'application
 */
class MovieRepository : KoinComponent {
    //Gestion des sources de données locales
    private val local: LocalDataSource by inject()
    //Gestion des sources de données en lignes
    private val online: OnlineDataSource by inject()

    /**
     * Récupérer le token permettant de consommer les ressources sur le serveur
     * Le résultat du datasource est converti en instance d'objets publiques
     */
    suspend fun getToken(): Result<Token> {
        return when(val result = online.getToken()) {
            is Result.Succes -> {
                //save the response in the local database
                local.saveToken(result.data.toEntity())
                //return the response
                Result.Succes(result.data.toToken())
            }
            is Result.Error -> result
        }
    }

    suspend fun getCategories(): Result<List<Category>> {
        return when(val result = online.getCategories()) {
            is Result.Succes -> {
                // On utilise la fonction map pour convertir les catégories de la réponse serveur
                // en liste de categories d'objets de l'application
                val categories = result.data.map {
                    it.toCategory()
                }
                Result.Succes(categories)
            }
            is Result.Error -> result
        }
    }

    suspend fun getListMovies(categoryId:Int): Result<List<Movie>> {
        return when(val result = online.getListMovies(categoryId)){
            is Result.Succes -> {
                val results = result.data.map {
                  it.toMovie()
                }
                Result.Succes(results)
            }
            is Result.Error -> result
        }
    }

    suspend fun getActorMovies(actorId: Int): Result<List<Movie>> {
        return when(val result = online.getActorMovies(actorId)){
            is Result.Succes -> {
                val results = result.data.map {
                    it.toMovie()
                }
                Result.Succes(results)
            }
            is Result.Error -> result
        }
    }

    suspend fun getMovieDetail(movieId:Int): Result<MovieDetail> {
        return when(val result = online.getMovieDetail(movieId)){
            is Result.Succes -> {
                val movieDetail = result.data.toMovieDetail()
                Result.Succes(movieDetail)
            }
            is Result.Error -> result
        }
    }

    suspend fun getSimilarMovies(movieId:Int): Result<List<Movie>> {
        return when(val result = online.getSimilarMovies(movieId)){
            is Result.Succes -> {
                val results = result.data.map {
                    it.toMovie()
                }
                Result.Succes(results)
            }
            is Result.Error -> result
        }
    }

    suspend fun getTrendingMovies(): Result<List<TrendingMovie>> {
        return when(val result = online.getTrendingMovies()){
            is Result.Succes -> {
                val trendingMovie = result.data.map {
                    it.toTrending()
                }
                Result.Succes(trendingMovie)
            }
            is Result.Error -> result
        }
    }

    suspend fun getTrendingPeople(): Result<List<TrendingPerson>> {
        return when(val result = online.getTrendingPeople()){
            is Result.Succes -> {
                val trendingPerson = result.data.map {
                    it.toPerson()
                }
                Result.Succes(trendingPerson)
            }
            is Result.Error -> result
        }
    }

    suspend fun getActor(department:String): Result<List<Actor>> {
        return when(val result = online.getActor(department)){
            is Result.Succes -> {
                val actor = result.data.map {
                    it.toActor()
                }
                Result.Succes(actor)
            }
            is Result.Error -> result
        }
    }

    suspend fun getActorDetail(actorId:Int): Result<ActorDetail> {
        return when(val result = online.getActorDetail(actorId)){
            is Result.Succes -> {
                val actorDetail = result.data.toActorDetail()
                Result.Succes(actorDetail)
            }
            is Result.Error -> result
        }
    }
}