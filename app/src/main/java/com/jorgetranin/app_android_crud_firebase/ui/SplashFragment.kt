package com.jorgetranin.app_android_crud_firebase.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.jorgetranin.app_android_crud_firebase.R
import com.jorgetranin.app_android_crud_firebase.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        val view = binding.root
        return view


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed(this::checkAuth, 2000)
        auth = FirebaseAuth.getInstance()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun checkAuth() {

        val currentUser = auth.currentUser
        if (currentUser != null) {
            findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
        } else {

            findNavController().navigate(R.id.action_splashFragment_to_loginFragment2)
        }
    }


}