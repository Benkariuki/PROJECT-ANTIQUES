package com.example.TAntiques.data


import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavHostController
import com.example.TAntiques.models.Watch
import com.example.TAntiques.navigation.LOGIN_URL
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage

class WatchesViewModel(var navController: NavHostController, var context: Context) {
    var authViewModel:AuthViewModel
    var progress: ProgressDialog
    init {
        authViewModel = AuthViewModel(navController, context)
        if (!authViewModel.isLoggedIn()){
            navController.navigate(LOGIN_URL)
        }
        progress = ProgressDialog(context)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")
    }

    fun uploadWatches(name:String, category:String,description: String, price:String, filePath: Uri){
        val watchesId = System.currentTimeMillis().toString()
        val storageRef = FirebaseStorage.getInstance().getReference()
            .child("watches/$watchesId")
        progress.show()
        storageRef.putFile(filePath).addOnCompleteListener{
            progress.dismiss()
            if (it.isSuccessful){
                // Save data to db
                storageRef.downloadUrl.addOnSuccessListener {
                    var imageUrl = it.toString()
                    var watches= Watch(name,category,description,price,imageUrl,watchesId)
                    var databaseRef = FirebaseDatabase.getInstance().getReference()
                        .child("watches/$watchesId")
                    databaseRef.setValue(watches).addOnCompleteListener {
                        if (it.isSuccessful){
                            Toast.makeText(this.context, "Success", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(this.context, "Error", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }else{
                Toast.makeText(this.context, "Upload error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun allWatches(
        watches: MutableState<Watch>,
        watchees:SnapshotStateList<Watch>):SnapshotStateList<Watch>{
        progress.show()
        var ref = FirebaseDatabase.getInstance().getReference()
            .child("watches")
        ref.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                watchees.clear()
                for (snap in snapshot.children){
                    var retrievedWatch = snap.getValue(Watch::class.java)
                    watches.value = retrievedWatch!!
                    watchees.add(retrievedWatch)
                }
                progress.dismiss()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "DB locked", Toast.LENGTH_SHORT).show()
            }
        })
        return watchees
    }

    fun deletewatch(watchId:String){
        var ref = FirebaseDatabase.getInstance().getReference()
            .child("watches/$watchId")
        ref.removeValue()
        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
    }
}
