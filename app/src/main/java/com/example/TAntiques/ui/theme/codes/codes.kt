package com.example.TAntiques.ui.theme.codes

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun codes(){

    Button(onClick = {

    }) {
        Text(text = "Delete")
    }
    Button(onClick = {
        //navController.navigate(ROUTE_UPDATE_PRODUCTS+"/$id")
    }) {
        Text(text = "Update")
    }

}