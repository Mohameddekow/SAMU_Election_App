package com.example.samuelectionapp.auth.login

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.samuelectionapp.R
import com.example.samuelectionapp.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

   // private lateinit var  viewModel: LoginViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.btnLogin.setOnClickListener {
            val email: String = binding.etLogin.text.toString().trim()
            val password: String = binding.etPassword.text.toString().trim()

            // dismiss keyboard
            val inputManager = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(binding.btnLogin.windowToken, 0)


            if (!checkInputs(email, password)) {
                //checking the inputs return empty fields error
            } else {
                //sing in
                signInUserWithEmailAndPassword(email, password)
            }

        }

        return binding.root
    }

    //authenticating user
    private fun signInUserWithEmailAndPassword(email: String, password: String) {
        binding.btnLogin.isEnabled = false
        binding.btnLogin.alpha = 0.4F
        binding.progressBar.visibility = View.VISIBLE

        //log in using firebase
        val auth: FirebaseAuth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                when {
                    task.isSuccessful -> {
                        binding.btnLogin.isEnabled = true
                        binding.btnLogin.alpha = 1F

                        binding.progressBar.visibility = View.GONE

                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)

                        val snackbar = Snackbar.make(
                            binding.root,
                            "Logged In Successfully",
                            Snackbar.LENGTH_LONG
                        )
                        snackbar.setAction(" ") {
                            //no action
                        }
                        snackbar.show()
                    }
                    task.isCanceled -> {
                        val snackbar = Snackbar.make(
                            binding.root,
                            "you canceled the login process",
                            Snackbar.LENGTH_LONG
                        )
                        snackbar.setAction(" ") {
                            //no action
                        }
                        snackbar.show()
                    }
                    else -> {
                        val snackbar = Snackbar.make(
                            binding.root,
                            task.exception.toString(),
                            Snackbar.LENGTH_LONG
                        )
                        snackbar.setAction(" ") {
                            //no action
                        }
                        snackbar.show()
                    }
                }
            }

    }


    //check 4 empty inputs
    private fun checkInputs(email: String, password: String): Boolean {

        when {

            TextUtils.isEmpty(password) && TextUtils.isEmpty(email) -> {

                val snackbar = Snackbar.make(
                    binding.root,
                    "Email and password fields cannot be empty",
                    Snackbar.LENGTH_LONG
                )
                snackbar.setAction("OK") {
                    //no action
                }
                snackbar.show()
            }
            TextUtils.isEmpty(email) -> {

                val snackbar = Snackbar.make(
                    binding.root,
                    "Email field  cannot be empty",
                    Snackbar.LENGTH_LONG
                )
                snackbar.setAction("OK") {
                    //no action
                }
                snackbar.show()
            }

            TextUtils.isEmpty(password) -> {
                val snackbar = Snackbar.make(
                    binding.root,
                    "Password field  cannot be empty",
                    Snackbar.LENGTH_LONG
                )
                snackbar.setAction("OK") {
                    //no action
                }
                snackbar.show()
            }
            else -> {
                return true
            }


        }
        return false
    }



    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}