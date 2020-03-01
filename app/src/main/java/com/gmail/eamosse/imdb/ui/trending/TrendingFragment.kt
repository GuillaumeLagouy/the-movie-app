package com.gmail.eamosse.imdb.ui.trending

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.gmail.eamosse.imdb.R
import com.gmail.eamosse.imdb.databinding.TrendingFragmentBinding
import com.gmail.eamosse.imdb.ui.home.CategoryAdapter
import com.gmail.eamosse.imdb.ui.home.HomeFragmentDirections
import org.koin.androidx.viewmodel.ext.android.viewModel

class TrendingFragment : Fragment() {

    private  val trendingViewModel: TrendingViewModel by viewModel()
    private lateinit var binding: TrendingFragmentBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = TrendingFragmentBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@TrendingFragment
        }

        /*
        val textView: TextView = root.findViewById(R.id.trending_title)
        trendingViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(trendingViewModel){
            trendingMovies.observe(viewLifecycleOwner, Observer {
                binding.trendingMovies.trendingItems.adapter = TrendingMovieAdapter(it)
                binding.shimmerViewContainer.hideShimmer()

            })
            trendingPeople.observe(viewLifecycleOwner, Observer {
                binding.trendingPeople.trendingItems.adapter = TrendingPersonAdapter(it)

            })
            categories.observe(viewLifecycleOwner, Observer {
                binding.topCategories.trendingItems.adapter = CategoryAdapter(it) { category ->
                    val action = TrendingFragmentDirections.actionTrendingFragmentToListMoviesFragment(category.id)
                    findNavController().navigate(action)
                }
            })
        }
    }
}
