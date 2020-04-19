package com.gmail.eamosse.imdb.ui.actorDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.eamosse.idbdata.data.ActorDetail
import com.gmail.eamosse.idbdata.data.Movie
import com.gmail.eamosse.idbdata.repository.MovieRepository
import com.gmail.eamosse.idbdata.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ActorDetailViewModel(private val repository: MovieRepository) : ViewModel(){
    private val _actorDetail: MutableLiveData<ActorDetail> = MutableLiveData()
    val actorDetail: LiveData<ActorDetail>
        get() = _actorDetail

    private val _actorMovies: MutableLiveData<List<Movie>> = MutableLiveData()
    val actorMovies: LiveData<List<Movie>>
        get() = _actorMovies

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String>
        get() = _error

    fun getActorDetail(actorId:Int){
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getActorDetail(actorId)) {
                is Result.Succes -> {
                    _actorDetail.postValue(result.data)
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }

    fun getActorMovies(actorId:Int){
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getActorMovies(actorId)) {
                is Result.Succes -> {
                    _actorMovies.postValue(result.data)
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }
}