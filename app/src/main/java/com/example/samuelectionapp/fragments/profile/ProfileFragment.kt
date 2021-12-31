package com.example.samuelectionapp.fragments.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.samuelectionapp.R
import com.example.samuelectionapp.databinding.FragmentProfileBinding
import com.example.samuelectionapp.utils.setImageWithPicasso
import com.google.android.material.snackbar.Snackbar


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
      //  _binding = FragmentProfileBinding.inflate(inflater, container, false)

       _binding = FragmentProfileBinding.inflate(inflater, container, false)


        //initialize viewModel
        viewModel = ProfileViewModel()

        //call the fun the fetches all the data
        viewModel.fetchUsersProfile()

        //listen for errors and success
        viewModel.profileResult.observe(viewLifecycleOwner, Observer {

            val errorOrSuccessMessage = if (it == null){
                getString(R.string.user_details)
            }else{
                getString(R.string.user_details_error, it.message)
            }

            if (it != null){
                setupSnackbar(it.message.toString())
            }

        })

        //show progress bar as the data is fetched
        binding.progressBar.visibility = View.VISIBLE
        binding.profileLinearLayout.visibility = View.GONE

        //observe the the live data
        viewModel.usersProfile.observe(viewLifecycleOwner, Observer {

            //hide progress bar when fetching data is complete
            binding.progressBar.visibility = View.GONE
            binding.profileLinearLayout.visibility = View.VISIBLE


            it.forEach {

                binding.profileName.text = getString(R.string.name, it.username)
                binding.profileEmail.text = getString(R.string.email, it.email)
                binding.profileRegNumber.text =  getString(R.string.reg_number, it.regnumber)
                setImageWithPicasso(it.profileimage, binding.profileImage)
                // binding.homeProfileImage.setImageResource(it.profileimage.toInt())
            }


            println(it)

        })




        return binding.root
    }


    private fun setupSnackbar(message: String){
        val snackbar = Snackbar.make(
            binding.root,
            message,
            Snackbar.LENGTH_SHORT
        )
        snackbar.setAction(" ") {
            //no action
        }
        snackbar.show()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}