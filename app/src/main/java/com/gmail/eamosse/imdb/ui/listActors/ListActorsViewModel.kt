package com.gmail.eamosse.imdb.ui.listActors

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.eamosse.idbdata.data.Actor
import com.gmail.eamosse.idbdata.repository.MovieRepository
import com.gmail.eamosse.idbdata.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListActorsViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _actors: MutableLiveData<List<Actor>> = MutableLiveData()
    val actors: LiveData<List<Actor>>
        get() = _actors

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String>
        get() = _error

    fun getListActors(department:String){
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getActor(department)) {
                is Result.Succes -> {
                    _actors.postValue(result.data)
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }
}