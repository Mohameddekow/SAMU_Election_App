package com.example.samuelectionapp.home

class HomeRepository {}

    //private val rootRef: FirebaseFirestore = FirebaseFirestore.getInstance().collection("users")


//    private val rootRef: FirebaseFirestore = FirebaseFirestore.getInstance()
//    private val userDetailsRef = rootRef.collection("users")
//
//    private val _users: MutableLiveData<List<LoginModelObject>> = MutableLiveData()
//    val users: LiveData<List<LoginModelObject>>
//        get() = _users
//
//
//    private val _user: MutableLiveData<LoginModelObject> = MutableLiveData()
//    val user: LiveData<LoginModelObject>
//        get() = _user
//
//
//    private val _result: MutableLiveData<Exception> = MutableLiveData()
//    val result: LiveData<Exception>
//        get() = _result
//
//
//    fun fetchUsers(user: LoginModelObject) {
//        userDetailsRef.get().addOnSuccessListener { snapshot ->
//            val users = mutableListOf<LoginModelObject>()
//            for (userSnapshot in snapshot) {
//                users.add(userSnapshot.toObject(LoginModelObject::class.java))
//            }
//            _users.value = users
//
//        }
//    }
//}
//
//    private lateinit var fireStoreDb: FirebaseFirestore
//
//
//    fun getUserHomeDetails(): MutableLiveData<Response> {
//        val mutableLiveData = MutableLiveData<Response>()
//
//        userDetailsRef.get().addOnCompleteListener { task ->
//            val response = Response()
//
//
//            if (task.isSuccessful) {
//                val result = task.result
//                result?.let {
//                    response.details = result.documents.mapNotNull { snapShot ->
//                        snapShot.toObject(LoginModelObject::class.java)
//                    }
//                }
//            } else {
//                response.exception = task.exception
//            }
//            mutableLiveData.value = response
//        }
//        return mutableLiveData
//    }

//}
    //
//        userDetailsRef.addSnapshotListener { value, error ->
//            var response = Response()
//            if (error == null || value != null){
//                response.details = value?.toObjects(LoginModelObject::class.java)
//            }
//            mutableLiveData.value = response
//        }
//
//       return mutableLiveData


//}


//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//    suspend fun getProductsFirestore(): List<Product> {
//        var products = listOf<Product>()
//        try {
//            products = productsRef.get().await().documents.mapNotNull { snapShot ->
//                snapShot.toObject(Product::class.java)
//            }
//        } catch (e: Exception) {
//            Log.d("TAG", e.message!!)
//        }
//        return products
//    }
//
//    suspend fun getUserHomeDetails(): List<LoginModelObject> {
//        fireStoreDb = FirebaseFirestore.getInstance()
//        val userDetailsRef = fireStoreDb.collection("users")
//
//        var userDetailsList  = listOf<LoginModelObject>()
//
//        try {
//            userDetailsList = userDetailsRef.addSnapshotListener { value, error ->
//                value.toObjects(LoginModelObject::class.java)
//            }
//
//        }catch (e: Exception){
//
//        }
//    }
//
//
//
//
//
//
//    = flow {
//        emit(Response.Loading)
//
//        try {
//            fireStoreDb = FirebaseFirestore.getInstance()
//            val userDetailsRef = fireStoreDb.collection("users")
//
//            userDetailsRef.addSnapshotListener { value, error ->
//                val userDetailsList = value?.toObjects(LoginModelObject::class.java)
//
//                for (user in userDetailsList!!){
//                    Log.d("post", user.username)
//                    // Toast.makeText(requi,"name is :+++++ :: ${user.username}", Toast.LENGTH_LONG).show()
//                }
//            }
//
//
//        }catch (e: Exception){
//            emit(Response.Error(e))
//        }
////    }
//
//
//}