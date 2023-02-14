package com.example.musicapp.Utilities

import android.media.browse.MediaBrowser
import java.lang.Exception
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicapp.R
import com.example.musicapp.View.Adapters.MusicAdapter
import com.example.musicapp.ViewModel.MusicViewModel
import com.example.musicapp.databinding.ExoplayerFragmentBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem

private const val TAG = "ExoPlayerFragment"
class ExoPlayerFragment : Fragment() {


    private val binding by lazy {
        ExoplayerFragmentBinding.inflate(layoutInflater)
    }

    private val musicViewModel: MusicViewModel by activityViewModels()

    private var uriTrack = ""
    private var exoPlayer: ExoPlayer? = null
    private var playBackPosition = 0L
    private var playWhenReady = true

    private val musicAdapter by lazy {
        MusicAdapter {
            musicViewModel.musicTrack = it
            findNavController().navigate(R.id.action_exo_player_to_pop)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        uriTrack = musicViewModel.musicTrack
        initializePlayer()

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply { 
            backButton.setOnClickListener { onClickBackButton() }
        }
    }

    //ExoPlayer settings
    private fun initializePlayer() {
        try {
            exoPlayer = context?.let { ExoPlayer.Builder(it).build() }
            exoPlayer?.playWhenReady = true
            binding.playerView.player = exoPlayer
            val mediaItem = MediaItem.fromUri(uriTrack)
            exoPlayer?.setMediaItem(mediaItem)
            exoPlayer?.seekTo(playBackPosition)
            exoPlayer?.playWhenReady = playWhenReady
            exoPlayer?.prepare()
        } catch (e: Exception) {
            e.message
        }

    }


    //Stop exoplayer buffering
    private fun releaseExoPlayer() {
        exoPlayer?.let {
            playBackPosition = it.currentPosition
            playWhenReady = it.playWhenReady
            it.release()
            exoPlayer = null
        }
    }

    override fun onStop() {
        super.onStop()
        releaseExoPlayer()
    }

    override fun onPause() {
        super.onPause()
        releaseExoPlayer()
    }

    override fun onDestroy() {
        super.onDestroy()
        releaseExoPlayer()
    }

    private fun onClickBackButton() {
        Log.d(TAG, "onClickBackButton: ")
//        binding.backButton.setOnClickListener {
            val back = findNavController().previousBackStackEntry?.destination?.id
            when (back) {
                R.id.rock -> findNavController().navigate(R.id.action_exo_player_to_rock)
                R.id.pop -> findNavController().navigate(R.id.action_exo_player_to_pop)
                R.id.classic -> findNavController().navigate(R.id.action_exo_player_to_classic)
            }

//        }

    }


}
