package com.example.musicapp.View.classicCatView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicapp.Utilities.BaseFragment
import com.example.musicapp.Utilities.UIState
import com.example.musicapp.View.Adapters.MusicAdapter
import com.example.musicapp.databinding.MainFragmentBinding
import com.example.musicapp.model.MusicResponse

class ClassicCatFragment: BaseFragment() {
    private val binding by lazy {
        MainFragmentBinding.inflate(layoutInflater)
    }

    private val musicAdapter by lazy {
        MusicAdapter {
            // todo handle the click to move to the details
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding.musicRv.apply {
            layoutManager  = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            adapter = musicAdapter
        }

        musicViewModel.classicCat.observe(viewLifecycleOwner)  { state ->
            when(state) {
                is UIState.LOADING -> {}
                is UIState.SUCCESS<MusicResponse> -> {
                    musicAdapter.updateItems(state.response.results ?: emptyList())
                }
                is UIState.ERROR -> {
                    showError(state.error.localizedMessage) {
                        // todo define an action
                    }
                }
            }
        }

        return binding.root
    }
}