package com.gmail.eamosse.imdb.ui.movieDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.eamosse.idbdata.data.Movie
import com.gmail.eamosse.idbdata.data.MovieDetail
import com.gmail.eamosse.idbdata.repository.MovieRepository
import com.gmail.eamosse.idbdata.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailViewModel(private val repository: MovieRepository) : ViewModel(){
    private val _movieDetail: MutableLiveData<MovieDetail> = MutableLiveData()
    val movieDetail: LiveData<MovieDetail>
        get() = _movieDetail

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String>
        get() = _error

    private val _similar: MutableLiveData<List<Movie>> = MutableLiveData()
    val similar: LiveData<List<Movie>>
        get() = _similar

    fun getMovieDetail(movieId:Int){
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getMovieDetail(movieId)) {
                is Result.Succes -> {
                    _movieDetail.postValue(result.data)
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }

    fun getSimilarMovies(movieId:Int){
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getSimilarMovies(movieId)) {
                is Result.Succes -> {
                    _similar.postValue(result.data)
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }

}