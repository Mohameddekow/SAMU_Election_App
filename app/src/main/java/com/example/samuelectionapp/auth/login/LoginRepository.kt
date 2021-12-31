package com.example.samuelectionapp.auth.login


class LoginRepository(
   // private var fireStoreDb: FirebaseFirestore
) {
//
//    suspend fun getDetails(){
//        var myUser: LiveData<List<LoginObjectMapper>> = MutableLiveData()
//        fireStoreDb = FirebaseFirestore.getInstance()
//
//        val auth: FirebaseAuth = FirebaseAuth.getInstance()
//        val currentUser = auth.currentUser?.uid
//
//        val usersReference = fireStoreDb.collection("users/${currentUser}")
//        usersReference.addSnapshotListener { value, error ->
//            val userDetailsRef = value?.toObjects(LoginObjectMapper::class.java)
//            myUser.value
//            if (userDetailsRef != null) {
//                for (user in userDetailsRef)
//                myUser.
//            }
//        }
//
//    }
//
//    suspend fun getUserProfileDetails(): List<LoginObjectMapper> {
//
//        fireStoreDb = FirebaseFirestore.getInstance()
//        val usersReference = fireStoreDb.collection("users")
//
//        lateinit var userDetails: MutableList<LoginObjectMapper>
//
//
//        usersReference.addSnapshotListener { snapShot, error ->
//            if (snapShot == null || error != null) {
//                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show()
//            } else {
//                val userDetailsList: MutableList<LoginObjectMapper> = snapShot.toObjects(LoginObjectMapper::class.java)
//                userDetails = userDetailsList
//                for (user in userDetailsList) {
//                    Log.d("users", user.toString())
//                    println(user)
//
//                }
//            }
//        }
//
//
//    }
}