package com.example.samuelectionapp.fragments.contestants

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.samuelectionapp.home.UserObject
import com.example.samuelectionapp.utils.Constants.COMPUTING
import com.example.samuelectionapp.utils.Constants.CONTESTANTS
import com.example.samuelectionapp.utils.Constants.SCHOOL_ARRAY
import com.google.firebase.firestore.FirebaseFirestore
import java.lang.Exception

class ContestantsViewModel(): ViewModel() {


    private val rootRef: FirebaseFirestore = FirebaseFirestore.getInstance()

    private val contestantsRef = rootRef.collection(CONTESTANTS).whereArrayContains(SCHOOL_ARRAY, COMPUTING)
    //to get contestants details
    private val _contestants: MutableLiveData<List<ContestantsObject>> = MutableLiveData()
    val contestants: LiveData<List<ContestantsObject>>
        get() = _contestants


    //
    private val _contestant: MutableLiveData<UserObject> = MutableLiveData()
    val contestant: LiveData<UserObject>
        get() = _contestant


    //to listen for any contestants details fetching error
    private val _result: MutableLiveData<Exception?> = MutableLiveData()
    val result: LiveData<Exception?>
        get() = _result


    //    contestants: contestantsModelObject
    fun fetchContestants() {
        contestantsRef.get().addOnSuccessListener { snapshots ->
            val contestants = mutableListOf<ContestantsObject>()

            //snapshot.toObject(UserObject::class.java)?.let { users.add(it) } //snapshot.toObject(UserObject::class.java)

            for (snapshot in snapshots){
               val contestant = snapshot.toObject(ContestantsObject::class.java)
               contestants.add(contestant)
            }
           // snapshots.toObjects(ContestantsObject::class.java)

            _contestants.value = contestants

            _result.value = null

        }
        contestantsRef.get().addOnFailureListener { exception ->
            _result.value = exception
        }
    }


}