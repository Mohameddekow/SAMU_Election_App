package com.example.samuelectionapp.fragments.admin.contestants

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.fragment.findNavController
import com.example.samuelectionapp.R
import com.example.samuelectionapp.databinding.FragmentAddContestantsBinding
import com.example.samuelectionapp.fragments.contestants.ContestantsObject
import com.example.samuelectionapp.fragments.contestants.SchoolObject
import com.example.samuelectionapp.utils.Constants.AGRICULTURE
import com.example.samuelectionapp.utils.Constants.BUSINESS
import com.example.samuelectionapp.utils.Constants.COMPUTING
import com.example.samuelectionapp.utils.Constants.CONTESTANTS
import com.example.samuelectionapp.utils.Constants.CONTESTANTS_IMAGES
import com.example.samuelectionapp.utils.Constants.ENGINEERING
import com.example.samuelectionapp.utils.Constants.HEALTH
import com.example.samuelectionapp.utils.Constants.MATHS
import com.example.samuelectionapp.utils.Constants.MEN_REP
import com.example.samuelectionapp.utils.Constants.SCHOOL_REP
import com.example.samuelectionapp.utils.Constants.WOMEN_REP
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage


private const val PICK_IMAGE_CODE = 1234

class AddContestantsFragment() : Fragment() {

    private var _binding: FragmentAddContestantsBinding? = null
    private val binding get() = _binding!!

    private var photoUri: Uri? = null

    private lateinit var firebaseStorage: FirebaseStorage
    private lateinit var firestoreDb: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAddContestantsBinding.inflate(inflater, container, false)

        //initialize firebase storage
        firebaseStorage = FirebaseStorage.getInstance()
        firestoreDb = FirebaseFirestore.getInstance()


        binding.btnRegister.setOnClickListener {
            val contestantName = binding.etName.text.toString().trim()
            val contestantEmail = binding.etEmail.text.toString().trim()
            val contestantRegNumber = binding.etRegNumber.text.toString().trim()
            val contestantPosition = binding.etPosition.text.toString().trim()
            var contestantsSchool = binding.etSchool.text.toString()
            if (!checkInputs(contestantName,contestantEmail,contestantRegNumber, contestantsSchool, contestantPosition)){
                //checking for inputs and return error if no input found
            }else{
                addContestantToFireStore()
            }
        }


        binding.addImageFrameLayout.setOnClickListener {
            pickImageFromGallery()

        }







        return binding.root
    }


    //add contestants
    private fun addContestantToFireStore() {

        val contestantName = binding.etName.text
        val contestantEmail = binding.etEmail.text
        val contestantRegNumber = binding.etRegNumber.text
        val contestantPosition = binding.etPosition.text
        var contestantsSchool = binding.etSchool.text.toString()
        val counter = 0

        //fireStore storage reg
        //  val contestantsStorageRef = FirebaseFirestore.getInstance().collection("contestants")


        //formatting the school array in a way that can be retrieve from firebase
        val etTextName = binding.etSchool.text.toString()
        var trimmedName: String = "jjjj"

        val comp = "co"
        val health = "hea"
        val agri = "agr"
        val bus = "bus"
        val eng = "eng"
        val math = "math"

        if (comp in etTextName) {
            trimmedName = COMPUTING
            contestantsSchool = "computing and informatics "
        }

        if (health in etTextName) {
            trimmedName = HEALTH
            contestantsSchool = "health science"

        }

        if (agri in etTextName) {
            trimmedName = AGRICULTURE
            contestantsSchool = "agriculture & food science"

        }

        if (bus in etTextName) {
            trimmedName = BUSINESS
            contestantsSchool = "business and economics"

        }

        if (eng in etTextName) {
            trimmedName = ENGINEERING
            contestantsSchool = "engineering and architect"

        }

        if (math in etTextName) {
            trimmedName = MATHS
            contestantsSchool = "Pure and applied Maths"

        }


        //create the school array to add to out ContestantsObject so that it ca be added to the database as array
        val schoolArray: ArrayList<String> = arrayListOf()
        schoolArray.add(trimmedName)

//        val schoolObject: HashMap<String, String>  = hashMapOf()
//        schoolObject[trimmedName] = trimmedName


        //get photo path storage reference
        val storageRef = firebaseStorage.reference
        val photoPathRef =
            storageRef.child("${CONTESTANTS_IMAGES}/${System.currentTimeMillis()}-photo.jpg")

        //upload the photo and get the get the download Url
        binding.btnRegister.isEnabled = false
        binding.btnRegister.alpha = 0.4F

        binding.progressBar.visibility = View.VISIBLE

        val photoUploadUri = photoUri as Uri

        photoPathRef.putFile(photoUploadUri)
            .continueWithTask { photoUploadTask ->
                //retrieve the download photo Url
                //Log.d("upload info", "${photoUploadTask.result?.bytesTransferred.toString()}")
                photoPathRef.downloadUrl

            }.continueWithTask { downloadUrlTask ->
                //create contestant object
                val contestantsObject = ContestantsObject(
                    contestantName.toString(),
                    downloadUrlTask.result.toString(), //image url will now be the download url of firebase
                    contestantEmail.toString(),
                    contestantRegNumber.toString(),
                    contestantsSchool.toString(),
                    contestantPosition.toString(),
                    counter,
                    schoolArray
                )

                firestoreDb.collection(CONTESTANTS)
                    .add(contestantsObject)
            }.addOnCompleteListener { contestantCreationTask ->
                binding.btnRegister.isEnabled = true
                binding.btnRegister.alpha = 1F

                binding.progressBar.visibility = View.GONE


                if (!contestantCreationTask.isSuccessful) {
                    Log.e("contestant creation", contestantCreationTask.exception.toString())
                    Toast.makeText(
                        requireContext(),
                        "Filed to create a new contestant:: ${contestantCreationTask.exception}",
                        Toast.LENGTH_SHORT
                    ).show()

                }

                //clear all the input fields
                contestantName!!.clear()
                binding.addContestantsImage.setImageResource(0)
                contestantEmail!!.clear()
                contestantRegNumber!!.clear()
                contestantsSchool = " "
                contestantPosition!!.clear()

                // Toast.makeText(requireContext(), "created a new contestant", Toast.LENGTH_SHORT).show()


                //show the success snack bar
                val snackbar = Snackbar.make(
                    binding.root,
                    "New contestant is successfully created",
                    Snackbar.LENGTH_SHORT
                )
                snackbar.setAction(" ") {
                    //no action needed
                }
                snackbar.show()

                findNavController().navigate(R.id.action_addContestantsFragment_to_homeFragment)


            }

    }


    //image picking
    private fun pickImageFromGallery() {
        val imagePickerIntent = Intent(Intent.ACTION_GET_CONTENT)
        imagePickerIntent.type = "image/*"

        startActivityForResult(imagePickerIntent, PICK_IMAGE_CODE)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                photoUri = data?.data
                val image = binding.addContestantsImage
                image.setImageURI(photoUri)
                println(photoUri)
                Log.d("photo", photoUri.toString())
                Toast.makeText(requireContext(), photoUri.toString(), Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(requireContext(), "Image picking canceled", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    //check 4 empty inputs
    private fun checkInputs(
        name: String,
        email: String,
        regNumber: String,
        school: String,
        position: String
    ): Boolean {

        when {

            TextUtils.isEmpty(name)
                    || TextUtils.isEmpty(email)
                    || TextUtils.isEmpty(regNumber)
                    || TextUtils.isEmpty(school)
                    || TextUtils.isEmpty(position) -> {

                val snackbar = Snackbar.make(
                    binding.root,
                    "All fields are required!!",
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

//<ProgressBar
//android:id="@+id/progressBar"
//style="?android:attr/progressBarStyleHorizontal"
//android:indeterminateBehavior="cycle"
//android:layout_width="match_parent"
//android:layout_height="52dp"/>