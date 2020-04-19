package com.gmail.eamosse.imdb.ui.mediaType

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gmail.eamosse.imdb.databinding.MediaTypeFragmentBinding

class MediaTypeFragment : Fragment() {
    private lateinit var binding: MediaTypeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MediaTypeFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.browseMovies.setOnClickListener {
            val action = MediaTypeFragmentDirections.actionMediaTypeFragmentToHomeFragment()
            findNavController().navigate(action)
        }

        binding.browseActors.setOnClickListener {
            val action = MediaTypeFragmentDirections.actionMediaTypeFragmentToListActorsFragment()
            findNavController().navigate(action)
        }
    }

}