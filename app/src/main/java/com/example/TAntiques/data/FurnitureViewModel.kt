package com.example.TAntiques.data

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavHostController
import com.example.TAntiques.models.Furniture
import com.example.TAntiques.navigation.LOGIN_URL
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage

class FurnitureViewModel(var navController: NavHostController, var context: Context) {
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
    fun uploadfurniture(name:String, category:String, description:String,price:String, filePath: Uri){
            val furnitureId = System.currentTimeMillis().toString()
            val storageRef = FirebaseStorage.getInstance().getReference()
                .child("furnitures/$furnitureId")
            progress.show()
            storageRef.putFile(filePath).addOnCompleteListener{
                progress.dismiss()
                if (it.isSuccessful){
                    // Save data to db
                    storageRef.downloadUrl.addOnSuccessListener {
                        var imageUrl = it.toString()
                        var furniture = Furniture(name, category , description ,price,imageUrl,furnitureId)
                        var databaseRef = FirebaseDatabase.getInstance().getReference()
                            .child("furnitures/$furnitureId")
                        databaseRef.setValue(furniture).addOnCompleteListener {
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

        fun allfurniture(
            furniture: MutableState<Furniture>,
            furnitures: SnapshotStateList<Furniture>
        ): SnapshotStateList<Furniture> {
            progress.show()
            var ref = FirebaseDatabase.getInstance().getReference()
                .child("furnitures")
            ref.addValueEventListener(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    furnitures.clear()
                    for (snap in snapshot.children){
                        var retrievedFurniture = snap.getValue(Furniture::class.java)
                        furniture.value = retrievedFurniture!!
                        furnitures.add(retrievedFurniture)
                    }
                    progress.dismiss()
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(context, "DB locked", Toast.LENGTH_SHORT).show()
                }
            })
            return furnitures
        }

        fun deletefurniture(furnitureId:String){
            var ref = FirebaseDatabase.getInstance().getReference()
                .child("furnitures/$furnitureId")
            ref.removeValue()
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }

}