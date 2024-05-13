package com.example.TAntiques.ui.theme.screens.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.TAntiques.R
import com.example.TAntiques.data.AuthViewModel
import com.example.TAntiques.navigation.SIGNUP_URL
import com.example.TAntiques.ui.theme.Almond
import com.example.TAntiques.ui.theme.Beige
import com.example.TAntiques.ui.theme.WazitoECommerceTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController:NavHostController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.pillarbox),
                contentScale = ContentScale.FillBounds,
            )
        ,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = "Login Here",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            color = Color.White,

        )
        Spacer(modifier = Modifier.height(30.dp))

        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        OutlinedTextField(
            value = email,
            onValueChange = {email = it},
            label = { Text(text = "Enter email",
                color = Color.Black,
                fontFamily = FontFamily.Serif,
                fontSize = 15.sp,
                fontWeight = FontWeight.ExtraBold
            )
                    },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            colors = TextFieldDefaults.textFieldColors(Color.Black),
            modifier= Modifier
                .padding(start = 30.dp),
            shape = RoundedCornerShape(30.dp),
            )

        Spacer(modifier = Modifier.height(30.dp))
        OutlinedTextField(
            value = password,
            onValueChange = {password = it},
            label = { Text(text = "Enter password",
                color = Color.Black,
                fontFamily = FontFamily.Serif,
                fontSize = 15.sp,
                fontWeight = FontWeight.ExtraBold
            )},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            colors = TextFieldDefaults.textFieldColors(Color.Black),
            modifier= Modifier
                .padding(start = 30.dp),
            shape = RoundedCornerShape(30.dp),

            )

        Spacer(modifier = Modifier.height(30.dp))
        val context = LocalContext.current
        val authViewModel = AuthViewModel(navController, context)

        Button(onClick = {
            authViewModel.login(email, password)
        },
            shape = RoundedCornerShape(topStart = 10.dp),
            colors = ButtonDefaults.buttonColors(Almond),
        ) {
            Text(text = "Login",
                color = Color.Black,
                fontFamily = FontFamily.Serif,
                fontSize = 10.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }
        Spacer(modifier = Modifier.height(30.dp))

        Button(onClick = {
            navController.navigate(SIGNUP_URL)
        },
            shape = RoundedCornerShape(topEnd = 10.dp),
            colors = ButtonDefaults.buttonColors(Beige),
        ) {
            Text(text = "Register instead",
                color = Color.Black,
                fontFamily = FontFamily.Serif,
                fontSize = 10.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun LoginScreenPreview(){
    WazitoECommerceTheme {
        LoginScreen(navController = rememberNavController())
    }
}