package com.example.TAntiques.data

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavHostController
import com.example.TAntiques.models.Vehicle
import com.example.TAntiques.navigation.LOGIN_URL
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage

class VehiclesViewModel(var navController: NavHostController, var context: Context) {
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

    fun uploadVehicle(name:String, category:String, description:String,price:String, filePath: Uri){
        val vehicleId = System.currentTimeMillis().toString()
        val storageRef = FirebaseStorage.getInstance().getReference()
            .child("vehicls/$vehicleId")
        progress.show()
        storageRef.putFile(filePath).addOnCompleteListener{
            progress.dismiss()
            if (it.isSuccessful){
                // Save data to db
                storageRef.downloadUrl.addOnSuccessListener {
                    var imageUrl = it.toString()
                    var vehicles = Vehicle(name, category , description ,price,imageUrl,vehicleId)
                    var databaseRef = FirebaseDatabase.getInstance().getReference()
                        .child("vehicls/$vehicleId")
                    databaseRef.setValue(vehicles).addOnCompleteListener {
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

    fun allVehicles(
        vehicle: MutableState<Vehicle>,
        vehicles: SnapshotStateList<Vehicle>
    ):SnapshotStateList<Vehicle>{
        progress.show()
        var ref = FirebaseDatabase.getInstance().getReference()
            .child("vehicls")
        ref.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                vehicles.clear()
                for (snap in snapshot.children){
                    var retrievedVehicle= snap.getValue(Vehicle::class.java)
                    vehicle.value = retrievedVehicle!!
                    vehicles.add(retrievedVehicle)
                }
                progress.dismiss()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "DB locked", Toast.LENGTH_SHORT).show()
            }
        })
        return vehicles
    }
    fun deletevehicle(vehicleId:String){
        var ref = FirebaseDatabase.getInstance().getReference()
            .child("vehicls/$vehicleId")
        ref.removeValue()
        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
    }
}