package com.example.samuelectionapp.fragments.results.chooseSchool.computing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.samuelectionapp.fragments.contestants.ContestantsObject
import com.example.samuelectionapp.utils.Constants
import com.google.firebase.firestore.FirebaseFirestore
import java.lang.Exception

class ComputingResultViewModel(): ViewModel() {


    private val rootRef: FirebaseFirestore = FirebaseFirestore.getInstance()

    private val computingRef = rootRef.collection(Constants.CONTESTANTS).whereArrayContains(
        Constants.SCHOOL_ARRAY,
        Constants.COMPUTING
    )

    //to get contestants details
    private val _computing: MutableLiveData<List<ContestantsObject>> = MutableLiveData()
    val computing: LiveData<List<ContestantsObject>>
        get() = _computing


    //to listen for any contestants details fetching error
    private val _result: MutableLiveData<Exception?> = MutableLiveData()
    val result: LiveData<Exception?>
        get() = _result


    //    contestants: contestantsModelObject
    fun fetchComputingSchool() {
        computingRef.get().addOnSuccessListener { snapshots ->
            val computingList = mutableListOf<ContestantsObject>()

            //snapshot.toObject(UserObject::class.java)?.let { users.add(it) } //snapshot.toObject(UserObject::class.java)

            for (snapshot in snapshots){
                val contestant = snapshot.toObject(ContestantsObject::class.java)
                computingList.add(contestant)


//                var idLive: MutableLiveData<List<String>> = MutableLiveData()
//                val id = snapshot.id
//                idLive.value = listOf(id)

            }
            // snapshots.toObjects(ContestantsObject::class.java)


            _computing.value = computingList

            _result.value = null

        }
        computingRef.get().addOnFailureListener { exception ->
            _result.value = exception
        }
    }



}