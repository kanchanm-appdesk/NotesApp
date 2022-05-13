package com.example.notesapp.ui

import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.notesapp.databinding.FragmentSplashScreenBinding

class SplashScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentSplashScreenBinding.inflate(inflater, container, false).root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val settings: SharedPreferences = requireContext().getSharedPreferences("prefs", 0)
        val firstRun = settings.getBoolean("firstRun", false)
        when {
            !firstRun -> {
                //if running for first time
                //Splash will load for first time
                val editor = settings.edit()
                editor.putBoolean("firstRun", true)
                editor.apply()
                splashScreenTimer()
            }
            else ->
                findNavController().navigate(SplashScreenDirections.actionSplashScreenToListOfNotes())
        }
    }

    private fun splashScreenTimer() {
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(SplashScreenDirections.actionSplashScreenToListOfNotes())
        }, 3000)
    }
}