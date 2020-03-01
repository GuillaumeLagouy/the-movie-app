package com.gmail.eamosse.imdb.ui.listMovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.gmail.eamosse.imdb.databinding.FragmentListMoviesBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListMoviesFragment : Fragment() {

    private val viewModel: ListMoviesViewModel by viewModel()
    private lateinit var binding: FragmentListMoviesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListMoviesBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@ListMoviesFragment
            viewModel = this@ListMoviesFragment.viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewModel){
            getListMovie(ListMoviesFragmentArgs.fromBundle(arguments!!).categoryId)
            movies.observe(viewLifecycleOwner, Observer {
                binding.movieList.adapter = MovieAdapter(it) { movie ->
                    val action = ListMoviesFragmentDirections.actionListMoviesFragmentToMovieDetailFragment(movie.id)
                    findNavController().navigate(action)
                }
            })
        }
    }
}