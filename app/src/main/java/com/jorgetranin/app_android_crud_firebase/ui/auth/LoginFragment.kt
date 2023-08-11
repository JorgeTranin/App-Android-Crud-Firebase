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
import com.jorgetranin.app_android_crud_firebase.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()

        setupListeners()
    }



    fun setupListeners(){
        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment2_to_newAccountFragment)
        }
        binding.btnRecover.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment2_to_recoverAccountFragment)
        }
        binding.btnLogin.setOnClickListener {
            binding.progressBar.isVisible = true
            validData()
        }
    }
    private fun validData(){
        val email = binding.edtEmail.text.toString().trim()
        val senha = binding.edtPassword.text.toString().trim()

        if (email.isNotEmpty() || email.isNotBlank()){
            if (senha.isNotEmpty() || senha.isNotBlank()){
                binding.progressBar.isVisible = true
                loginUser(email, senha)

            }else{
                binding.progressBar.isVisible = false
                Toast.makeText(requireContext(), "Informe sua Senha", Toast.LENGTH_SHORT).show()
            }
        }else{
            binding.progressBar.isVisible = false
            Toast.makeText(requireContext(), "Informe seu Email", Toast.LENGTH_SHORT).show()
        }

    }

    private fun loginUser(email: String, senha: String) {
        auth.signInWithEmailAndPassword(email, senha)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    binding.progressBar.isVisible = false
                    findNavController().navigate(R.id.action_loginFragment2_to_homeFragment)
                } else {
                    binding.progressBar.isVisible = false
                    Toast.makeText(requireContext(), "Erro ao Fazer Login", Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}