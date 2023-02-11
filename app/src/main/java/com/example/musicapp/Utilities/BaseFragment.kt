package com.example.musicapp.Utilities


import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.musicapp.DI.musicApp
import com.example.musicapp.ViewModel.MusicViewModel
import javax.inject.Inject

open class BaseFragment : Fragment() {

    @Inject
    lateinit var musicViewModelFactory: MusicViewModelFactory

    protected val musicViewModel: MusicViewModel by lazy {
        ViewModelProvider(requireActivity(), musicViewModelFactory)[MusicViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        musicApp.musicComponent.inject(this)
    }

    protected fun showError(message: String, action: () -> Unit) {
        AlertDialog.Builder(requireActivity())
            .setTitle("Error Occurred")
            .setMessage(message)
            .setPositiveButton("RETRY") { dialog, _ ->
                action()
                dialog.dismiss()
            }
            .setNegativeButton("DISMISS") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

}