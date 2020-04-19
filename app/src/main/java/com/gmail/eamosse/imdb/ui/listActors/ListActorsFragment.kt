package com.gmail.eamosse.imdb.ui.listActors

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.gmail.eamosse.imdb.databinding.FragmentListActorsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class ListActorsFragment : Fragment() {
    private val viewModel: ListActorsViewModel by viewModel()
    private lateinit var binding: FragmentListActorsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListActorsBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@ListActorsFragment
            viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewModel){
            getListActors("acting")
            actors.observe(viewLifecycleOwner, Observer {
                binding.actorList.adapter = ActorAdapter(it) { actor ->
                    val action = ListActorsFragmentDirections.actionListActorsFragmentToActorDetailFragment(actor.id)
                    findNavController().navigate(action)
                }
            })
        }
    }
}