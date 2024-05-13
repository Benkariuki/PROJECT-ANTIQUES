package com.example.TAntiques.ui.theme.screens.Splashscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.TAntiques.R
import com.example.TAntiques.navigation.LOGIN_URL
import com.example.TAntiques.ui.theme.Almond
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Splashscreen(navController: NavHostController) {

        val coroutine = rememberCoroutineScope()
        coroutine.launch {
                delay(3000)
                navController.navigate(LOGIN_URL)
        }


        Column(
                modifier = Modifier
                        .fillMaxSize()
                        .paint(
                                painter = painterResource(id = R.drawable.pillarbox),
                                contentScale = ContentScale.FillBounds
                        )
                        .horizontalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                ){

                Spacer(modifier = Modifier.height(180.dp))
                Box (modifier = Modifier
                        .fillMaxWidth()
                        .height(340.dp)
                        .width(370.dp),
                        contentAlignment = Alignment.Center,
                        )
                {
                       Column(horizontalAlignment = Alignment.CenterHorizontally,
                       ) {
                               Text(text = "T-Antiques",
                                       color = Almond,
                                       fontWeight = FontWeight.ExtraBold,
                                       fontSize = 40.sp,
                                       fontFamily = FontFamily.SansSerif
                               )

                               Text(text = "Acquire the timeless in the real.",
                                       color = Color.White,
                                       fontWeight = FontWeight.ExtraBold,
                                       fontSize = 31.sp,
                                       fontFamily = FontFamily.Cursive
                               )

                       }


                }

        }


}

@Composable
@Preview(showBackground = true)
fun SplashscreenPreview(){
        Splashscreen(navController = rememberNavController())

}