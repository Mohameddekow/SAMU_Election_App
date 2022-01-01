package com.example.samuelectionapp.fragments.results.chooseSchool.engineering

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.samuelectionapp.fragments.contestants.ContestantsObject
import com.example.samuelectionapp.utils.Constants
import com.google.firebase.firestore.FirebaseFirestore
import java.lang.Exception

class EngineeringResultViewModel(): ViewModel() {


    private val rootRef: FirebaseFirestore = FirebaseFirestore.getInstance()

    private val engineeringRef = rootRef.collection(Constants.CONTESTANTS).whereArrayContains(
        Constants.SCHOOL_ARRAY,
        Constants.ENGINEERING
    )
    //to get contestants details
    private val _engineering: MutableLiveData<List<ContestantsObject>> = MutableLiveData()
    val engineering: LiveData<List<ContestantsObject>>
        get() = _engineering


//    //
//    private val _contestant: MutableLiveData<UserObject> = MutableLiveData()
//    val contestant: LiveData<UserObject>
//        get() = _contestant


    //to listen for any contestants details fetching error
    private val _result: MutableLiveData<Exception?> = MutableLiveData()
    val result: LiveData<Exception?>
        get() = _result


    //    contestants: contestantsModelObject
    fun fetchEngineeringSchool() {
        engineeringRef.get().addOnSuccessListener { snapshots ->
            val engineeringList = mutableListOf<ContestantsObject>()

            //snapshot.toObject(UserObject::class.java)?.let { users.add(it) } //snapshot.toObject(UserObject::class.java)

            for (snapshot in snapshots){
                val contestant = snapshot.toObject(ContestantsObject::class.java)
                engineeringList.add(contestant)


//                var idLive: MutableLiveData<List<String>> = MutableLiveData()
//                val id = snapshot.id
//                idLive.value = listOf(id)

            }
            // snapshots.toObjects(ContestantsObject::class.java)


            _engineering.value = engineeringList

            _result.value = null

        }
        engineeringRef.get().addOnFailureListener { exception ->
            _result.value = exception
        }
    }


}