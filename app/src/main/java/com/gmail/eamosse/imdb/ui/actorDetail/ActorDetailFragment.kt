package com.gmail.eamosse.imdb.ui.actorDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.gmail.eamosse.imdb.databinding.FragmentActorDetailBinding
import com.gmail.eamosse.imdb.ui.movieDetail.SimilarMoviesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ActorDetailFragment : Fragment() {
    private val viewModel: ActorDetailViewModel by viewModel()
    private lateinit var binding: FragmentActorDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentActorDetailBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@ActorDetailFragment
            viewModel = this@ActorDetailFragment.viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewModel){
            val actorId = ActorDetailFragmentArgs.fromBundle(arguments!!).actorId
            getActorDetail(actorId)
            actorDetail.observe(viewLifecycleOwner, Observer {
                binding.item = it
            })

            getActorMovies(actorId)
            actorMovies.observe(viewLifecycleOwner, Observer {
                binding.actorDetailMovies.adapter = SimilarMoviesAdapter(it){ movie ->
                    val action = ActorDetailFragmentDirections.actionActorDetailFragementToMovieDetailFragment(movie.id)
                    findNavController().navigate(action)
                }
            })
        }
    }
}