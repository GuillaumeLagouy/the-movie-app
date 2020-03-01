package com.gmail.eamosse.imdb.ui.movieDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.gmail.eamosse.imdb.databinding.FragmentMovieDetailBinding
import com.gmail.eamosse.imdb.ui.listMovies.MovieAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailFragment : Fragment() {
    private  val viewModel: MovieDetailViewModel by viewModel()
    private lateinit var binding: FragmentMovieDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@MovieDetailFragment
            viewModel = this@MovieDetailFragment.viewModel

        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewModel){
            getMovieDetail(MovieDetailFragmentArgs.fromBundle(arguments!!).movieId)
            movieDetail.observe(viewLifecycleOwner, Observer {
                binding.item = it
                binding.productionCompaniesList.adapter = CompaniesAdapter(it.productionCompanies)
            })
            getSimilarMovies(MovieDetailFragmentArgs.fromBundle(arguments!!).movieId)
            similar.observe(viewLifecycleOwner, Observer {
                binding.similarList.adapter = SimilarMoviesAdapter(it){ movie ->
                    print(movie)
                }
            })
        }
    }
}