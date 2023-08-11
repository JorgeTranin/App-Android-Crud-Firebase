package com.jorgetranin.app_android_crud_firebase.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jorgetranin.app_android_crud_firebase.databinding.FragmentDoingBinding


class DoingFragment : Fragment() {


    private var _binding: FragmentDoingBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDoingBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


}