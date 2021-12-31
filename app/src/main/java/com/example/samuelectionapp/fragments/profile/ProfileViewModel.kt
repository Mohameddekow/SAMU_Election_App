package com.example.samuelectionapp.fragments.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.samuelectionapp.home.UserObject
import com.example.samuelectionapp.utils.Constants.USERS
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.lang.Exception

class ProfileViewModel : ViewModel() {

    val  auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val rootRef: FirebaseFirestore = FirebaseFirestore.getInstance()

    private val currentUser: String = auth.currentUser?.uid.toString()

    private val userDetailsRef = rootRef.collection(USERS).document(currentUser)

    //to get users details
    private val _usersProfile: MutableLiveData<List<UserObject>> = MutableLiveData()
    val usersProfile: LiveData<List<UserObject>>
        get() = _usersProfile


//    //
//    private val _user: MutableLiveData<UserObject> = MutableLiveData()
//    val user: LiveData<UserObject>
//        get() = _user


    //to listen for any user details fetching error
    private val _profileResult: MutableLiveData<Exception?> = MutableLiveData()
    val profileResult: LiveData<Exception?>
        get() = _profileResult


    //    user: LoginModelObject
    fun fetchUsersProfile() {
        userDetailsRef.get().addOnSuccessListener { snapshot ->
            val users = mutableListOf<UserObject>()

            snapshot.toObject(UserObject::class.java)?.let { users.add(it) } //snapshot.toObject(UserObject::class.java)

            _usersProfile.value = users

            _profileResult.value = null

        }
        userDetailsRef.get().addOnFailureListener { exception ->
            _profileResult.value = exception
        }
    }


}