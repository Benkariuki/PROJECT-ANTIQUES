package com.example.TAntiques.data

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavHostController
import com.example.TAntiques.models.Clothing
import com.example.TAntiques.navigation.LOGIN_URL
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage


class ClothesViewModel(var navController:NavHostController, var context: Context) {
    var authViewModel:AuthViewModel
    var progress:ProgressDialog
    init {
        authViewModel = AuthViewModel(navController, context)
        if (!authViewModel.isLoggedIn()){
            navController.navigate(LOGIN_URL)
        }
        progress = ProgressDialog(context)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")
    }

    fun uploadCloth(name:String, category:String, description:String,price:String, filePath:Uri){
        val clothId = System.currentTimeMillis().toString()
        val storageRef = FirebaseStorage.getInstance().getReference()
            .child("clothes/$clothId")
        progress.show()
        storageRef.putFile(filePath).addOnCompleteListener{
            progress.dismiss()
            if (it.isSuccessful){
                // Save data to db
                storageRef.downloadUrl.addOnSuccessListener {
                    var imageUrl = it.toString()
                    var cloth= Clothing(name, category , description ,price,imageUrl,clothId)
                    var databaseRef = FirebaseDatabase.getInstance().getReference()
                        .child("clothes/$clothId")
                    databaseRef.setValue(cloth).addOnCompleteListener {
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

    fun allCloth(
        cloth:MutableState<Clothing>,
        cloths:SnapshotStateList<Clothing>):SnapshotStateList<Clothing>{
        progress.show()
        var ref = FirebaseDatabase.getInstance().getReference()
            .child("clothes")
        ref.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                cloths.clear()
                for (snap in snapshot.children){
                    var retrievedClothing = snap.getValue(Clothing::class.java)
                    cloth.value = retrievedClothing!!
                    cloths.add(retrievedClothing)
                }
                progress.dismiss()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "DB locked", Toast.LENGTH_SHORT).show()
            }
        })
        return cloths
    }

    fun deleteCloth(clothId:String){
        var ref = FirebaseDatabase.getInstance().getReference()
            .child("clothes/$clothId")
        ref.removeValue()
        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
    }
}