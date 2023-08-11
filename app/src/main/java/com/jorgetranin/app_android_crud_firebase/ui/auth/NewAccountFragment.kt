package com.jorgetranin.app_android_crud_firebase.ui.auth

import android.content.ContentValues.TAG
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.jorgetranin.app_android_crud_firebase.R
import com.jorgetranin.app_android_crud_firebase.databinding.FragmentNewAccountBinding

class NewAccountFragment : Fragment() {
    lateinit var auth: FirebaseAuth
    private var _binding: FragmentNewAccountBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewAccountBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        setupListeners()
    }

    private fun validData(){
        val email = binding.edtEmail.text.toString().trim()
        val senha = binding.edtPassword.text.toString().trim()

        if (email.isNotEmpty() || email.isNotBlank()){
            if (senha.isNotEmpty() || senha.isNotBlank()){
                binding.progressBar.isVisible = true
                registerUser(email, senha)

            }else{
                Toast.makeText(requireContext(), "Informe sua Senha", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(requireContext(), "Informe seu Email", Toast.LENGTH_SHORT).show()
        }

    }
    private fun registerUser(email: String, senha:String){
        auth.createUserWithEmailAndPassword(email, senha)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    binding.progressBar.isVisible = false
                    findNavController().navigate(R.id.action_newAccountFragment_to_homeFragment)
                } else {
                    binding.progressBar.isVisible = false
                    Toast.makeText(requireContext(), "Erro ao cadastrar", Toast.LENGTH_SHORT).show()
                }
            }
    }
    private fun setupListeners(){
        binding.toolbar.setOnClickListener {
            findNavController().navigate(R.id.action_newAccountFragment_to_loginFragment2)
        }
        binding.btnRegister.setOnClickListener {
            validData()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}