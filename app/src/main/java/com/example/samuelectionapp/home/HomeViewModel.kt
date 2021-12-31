package com.example.samuelectionapp.home


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.samuelectionapp.utils.Constants.USERS
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.lang.Exception

class HomeViewModel() : ViewModel() {

    val  auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val rootRef: FirebaseFirestore = FirebaseFirestore.getInstance()

    private val currentUser: String = auth.currentUser?.uid.toString()

    private val userDetailsRef = rootRef.collection(USERS).document(currentUser)

    //to get users details
    private val _users: MutableLiveData<List<UserObject>> = MutableLiveData()
    val users: LiveData<List<UserObject>>
        get() = _users


    //
    private val _user: MutableLiveData<UserObject> = MutableLiveData()
    val user: LiveData<UserObject>
        get() = _user


    //to listen for any user details fetching error
    private val _result: MutableLiveData<Exception?> = MutableLiveData()
    val result: LiveData<Exception?>
        get() = _result


    //    user: LoginModelObject
    fun fetchUsers() {
        userDetailsRef.get().addOnSuccessListener { snapshot ->
            val users = mutableListOf<UserObject>()

            snapshot.toObject(UserObject::class.java)?.let { users.add(it) } //snapshot.toObject(UserObject::class.java)

            _users.value = users

            _result.value = null

        }
        userDetailsRef.get().addOnFailureListener { exception ->
            _result.value = exception
        }
    }

}

  // fun getUserHomeDetails() = repository.getUserHomeDetails()

   // fun getUserHomeDetails() = repository.getUserHomeDetails()

//}
//
//    private val _userDetails: MutableLiveData<Response<List<LoginModelObject>>> = MutableLiveData()
//    val userDetails: LiveData<Response<List<LoginModelObject>>>
//        get() = _userDetails
//
//    fun getUserDetails(){
//        viewModelScope.launch (Dispatchers.IO){
//            repository.getUserHomeDetails()
//                .onEach { data ->
//                    _userDetails.value = data
//                }
//        }
//    }
//}







//
//class HomeViewModel() : ViewModel() {
//
//    val  auth: FirebaseAuth = FirebaseAuth.getInstance()
//    private val rootRef: FirebaseFirestore = FirebaseFirestore.getInstance()
//
//    private val currentUser: String = auth.currentUser?.uid.toString()
//    private val userDetailsRef = rootRef.collection("users").document(currentUser)
//
//    private val _users: MutableLiveData<List<UserObject>> = MutableLiveData()
//    val users: LiveData<List<UserObject>>
//        get() = _users
//
//
//    private val _user: MutableLiveData<UserObject> = MutableLiveData()
//    val user: LiveData<UserObject>
//        get() = _user
//
//
//    private val _result: MutableLiveData<Exception> = MutableLiveData()
//    val result: LiveData<Exception>
//        get() = _result
//
//
//    //    user: LoginModelObject
//    fun fetchUsers() {
//        userDetailsRef.get().addOnSuccessListener { snapshot ->
//            val users = mutableListOf<UserObject>()
//
//            snapshot.toObject(UserObject::class.java)?.let { users.add(it) }
//
//            _users.value = users
//
//        }
//    }
//
//}