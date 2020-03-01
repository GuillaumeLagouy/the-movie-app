package com.gmail.eamosse.imdb.ui.movieDetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gmail.eamosse.idbdata.data.Company
import com.gmail.eamosse.imdb.databinding.ProductionCompaniesItemBinding

class CompaniesAdapter(private val items: List<Company>) : RecyclerView.Adapter<CompaniesAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ProductionCompaniesItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Company){
            binding.item = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding:ProductionCompaniesItemBinding = ProductionCompaniesItemBinding.inflate(
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