package com.jorgetranin.app_android_crud_firebase.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.jorgetranin.app_android_crud_firebase.R
import com.jorgetranin.app_android_crud_firebase.databinding.FragmentRecoverAccountBinding


class RecoverAccountFragment : Fragment() {
   private lateinit var auth: FirebaseAuth
    private var _binding: FragmentRecoverAccountBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecoverAccountBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        setupListeners()
    }

    private fun setupListeners() {
        binding.toolbar.setOnClickListener {
            findNavController().navigate(R.id.action_recoverAccountFragment_to_loginFragment2)
        }
        binding.btnSend.setOnClickListener {
            validData()
        }
    }

    private fun validData() {
        val email = binding.edtEmail.text.toString().trim()

        if (email.isNotEmpty() || email.isNotBlank()) {

            binding.progressBar.isVisible = true
            recoverUser(email)


        } else {
            Toast.makeText(requireContext(), "Informe seu Email", Toast.LENGTH_SHORT).show()
        }
    }
    private fun recoverUser(email: String){
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    binding.progressBar.isVisible = false
                    Toast.makeText(requireContext(), "Olhe seu email para recuperar conta", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_recoverAccountFragment_to_loginFragment2)
                } else {
                    binding.progressBar.isVisible = false
                    Toast.makeText(requireContext(), "Erro no email", Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}