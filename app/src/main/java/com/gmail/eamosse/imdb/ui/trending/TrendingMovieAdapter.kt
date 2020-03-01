package com.gmail.eamosse.imdb.ui.trending

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gmail.eamosse.idbdata.data.TrendingMovie
import com.gmail.eamosse.idbdata.data.TrendingPerson
import com.gmail.eamosse.imdb.databinding.TrendingFragmentBinding
import com.gmail.eamosse.imdb.databinding.TrendingMovieItemBinding
import com.gmail.eamosse.imdb.databinding.TrendingPersonItemBinding

class TrendingMovieAdapter(private val items: List<TrendingMovie>)  : RecyclerView.Adapter<TrendingMovieAdapter.ViewHolder>(){

    inner class ViewHolder(private val binding: TrendingMovieItemBinding)  : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TrendingMovie){
            binding.item = item
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding : TrendingMovieItemBinding = TrendingMovieItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

}
