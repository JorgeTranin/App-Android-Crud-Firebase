package com.jorgetranin.app_android_crud_firebase.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth
import com.jorgetranin.app_android_crud_firebase.R
import com.jorgetranin.app_android_crud_firebase.databinding.FragmentHomeBinding
import com.jorgetranin.app_android_crud_firebase.databinding.FragmentNewAccountBinding
import com.jorgetranin.app_android_crud_firebase.ui.adapter.ViewPagerAdapter


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        //configTablayout()
        setupListeners()

    }


    private fun setupListeners(){
        binding.ibLogout.setOnClickListener {
            logoutApp()
        }
    }

    private fun logoutApp() {
        auth.signOut()
        findNavController().navigate(R.id.action_homeFragment_to_loginFragment2)
    }


    private fun configTablayout() {
        val adapter = ViewPagerAdapter(requireActivity())
        binding.viewPager.adapter = adapter
/*
        adapter.addFragment(TodoFragment(), R.string.status_task_todo)
        adapter.addFragment(DoingFragment(), R.string.status_task_doing)
        adapter.addFragment(DoneFragment(), R.string.status_task_done)
*/
        binding.viewPager.offscreenPageLimit = adapter.itemCount

        TabLayoutMediator(
            binding.tabs, binding.viewPager
        ) { tab, position ->
            tab.text = getString(adapter.getTitle(position))
        }.attach()
    }
}