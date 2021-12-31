package com.example.samuelectionapp.home

import android.os.Bundle
import android.view.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.samuelectionapp.R
import com.example.samuelectionapp.databinding.FragmentHomeBinding
import com.example.samuelectionapp.globalData.GlobalData
import com.example.samuelectionapp.utils.setImageWithPicasso
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth


class HomeFragment : Fragment(), HomeRecyclerViewAdapter.ItemClickListener {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: HomeViewModel
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)



        //setup option menu
        setHasOptionsMenu(true)

        //adapter
        val myAdapter = HomeRecyclerViewAdapter(this)

        //set data on recyclerView
        val homeData = GlobalData.HomeData
        myAdapter.setData(homeData)

        binding.apply {
            homeRecyclerview.adapter = myAdapter
            homeRecyclerview.layoutManager = GridLayoutManager(requireContext(), 2)
        }



        viewModel = HomeViewModel()
        viewModel.fetchUsers()

        //listen for errors and success
        viewModel.result.observe(viewLifecycleOwner, Observer {
            val errorOrSuccessMessage = if (it == null){
                getString(R.string.user_details)
            }else{
                getString(R.string.user_details_error, it.message)
            }

            if (it != null){
                setupSnackbar(it.message.toString())
            }

        })

        //show progress bar as the data is fetched and hide the user details placeholders
        binding.userDetailsLinearLayout.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE

        viewModel.users.observe(viewLifecycleOwner, Observer {

            //hide progress bar as the data is fetched and show the user details that is fetched from database
            binding.userDetailsLinearLayout.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE

                it.forEach {
                    binding.homeName.text = it.username
                    binding.homeRegNo.text = it.regnumber
                    setImageWithPicasso(it.profileimage, binding.homeProfileImage)
                   // binding.homeProfileImage.setImageResource(it.profileimage.toInt())
                }


                println(it)

        })

        //trying with repository
//        viewModel.getUserHomeDetails().observe(viewLifecycleOwner, Observer {
//            print(it)
//        })
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



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu,menu)

        //showing the admin panel to only the admin dr. chege
        val adminPanel = menu.findItem(R.id.adminPanel)

        auth = FirebaseAuth.getInstance()
        val adminUi = auth.currentUser?.uid.toString()

        val adminKey: String = "9Jz9Poz9iOQ0no3i8ieOxEHveSk1"

        //shoe the admin panel when the id == admkey ....dr.chege
        adminPanel.isVisible = adminUi == adminKey


        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        when(item.itemId){
            R.id.profileFragment ->{
                findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
            }
            R.id.logout ->{
                FirebaseAuth.getInstance().signOut()
                findNavController().navigate(R.id.action_homeFragment_to_loginFragment)

            }
            R.id.settingsFragment ->{
                findNavController().navigate(R.id.action_homeFragment_to_settingsFragment)
            }
            R.id.addVotersFragment ->{
                findNavController().navigate(R.id.action_homeFragment_to_addVotersFragment)
            }
            R.id.addContestantsFragment ->{
                findNavController().navigate(R.id.action_homeFragment_to_addContestantsFragment)
            }
            R.id.setElectionTimeFragment ->{
                findNavController().navigate(R.id.action_homeFragment_to_setElectionTimeFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //recyclerView item click listener
    override fun onItemClicked(view: View, homeItem: HomeItems, position: Int) {
        when(position){
           0 -> { findNavController().navigate(R.id.action_homeFragment_to_contestantsFragment)}
           1 -> { findNavController().navigate(R.id.action_homeFragment_to_chooseSchoolVoteFragment)}
           2 -> { findNavController().navigate(R.id.action_homeFragment_to_chooseSchoolResultsFragment)}
           3 -> { findNavController().navigate(R.id.action_homeFragment_to_helpFragment)}
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}