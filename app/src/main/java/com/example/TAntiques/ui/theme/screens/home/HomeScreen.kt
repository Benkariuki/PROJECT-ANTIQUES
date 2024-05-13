package com.example.TAntiques.ui.theme.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.TAntiques.R
import com.example.TAntiques.navigation.CLOTHINGviewSCREEN_URL
import com.example.TAntiques.navigation.FURNITUREviewSCREEN_URL
import com.example.TAntiques.navigation.VEHICLEviewSCREEN_URL
import com.example.TAntiques.navigation.WATCHviewSCREEN_URL
import com.example.TAntiques.ui.theme.Almond
import com.example.TAntiques.ui.theme.WazitoECommerceTheme


data class Screen(val title: String, val icon: Int)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController:NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        var mContext = LocalContext.current
        var selected by remember { mutableIntStateOf(0) }
        Scaffold(

            content = @Composable {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .paint(
                            painter = painterResource(id = R.drawable.pillarbox),
                            contentScale = ContentScale.FillBounds,
                        )
                        .verticalScroll(rememberScrollState()),

                    )
                {
                    Text(
                        text = "T-Antiques",
                        color = Almond,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 40.sp,
                        fontFamily = FontFamily.SansSerif
                    )

                    Text(
                        text = "Acquire the timeless in the real..",
                        textDecoration = TextDecoration.Underline,
                        color = Color.White,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 28.sp,
                        fontFamily = FontFamily.Cursive
                    )

                    Spacer(modifier = Modifier.height(40.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp)
                    ) {
                        Text(
                            text = "Categories",
                            color = Color.White,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 25.sp,
                            fontFamily = FontFamily.SansSerif
                        )
                        Spacer(modifier = Modifier.width(5.dp))

                        Icon(imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription ="arrow",
                            tint = Color.White,
                            modifier = Modifier.padding(top = 7.dp)
                            )

                    }

                    Spacer(modifier = Modifier.height(25.dp))


                    //card1
                    Card (modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .clip(
                            shape = RoundedCornerShape(
                                bottomStart = 30.dp,
                                topEnd = 30.dp
                            )
                        )
                        .clickable {navController.navigate(VEHICLEviewSCREEN_URL) }
                        ,
                        colors = CardDefaults.cardColors(Color.LightGray)
                    ){
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                        ){
                            Image(painter = painterResource(id = R.drawable.car),
                                contentDescription ="green",
                                modifier = Modifier
                                    .size(70.dp)
                                    .clip(CircleShape),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text(
                                    text = "Vehicles",
                                    color = Color.Black,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 20.sp,
                                    fontFamily = FontFamily.Serif,
                                    modifier = Modifier.padding(top = 20.dp)
                                )
                            }
                        }
                    }

                    //end of card one

                    //card2
                    Card (modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .clip(
                            shape = RoundedCornerShape(
                                bottomStart = 30.dp,
                                topEnd = 30.dp
                            )
                        )
                        .clickable {navController.navigate(FURNITUREviewSCREEN_URL) }
                        ,
                        colors = CardDefaults.cardColors(Color.LightGray)
                    ){
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                        ){
                            Image(painter = painterResource(id = R.drawable.furniture),
                                contentDescription ="green",
                                modifier = Modifier
                                    .size(70.dp)
                                    .clip(CircleShape),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text(text = "Furniture",
                                    color = Color.Black,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 20.sp,
                                    modifier = Modifier.padding(top = 20.dp),
                                    fontFamily = FontFamily.Serif)
                            }
                        }
                    }
                    //end of card 2

                    //card3
                    Card (modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .clip(
                            shape = RoundedCornerShape(
                                bottomStart = 30.dp,
                                topEnd = 30.dp
                            )
                        )
                        .clickable {navController.navigate(WATCHviewSCREEN_URL) }
                        ,
                        colors = CardDefaults.cardColors(Color.LightGray)
                    ){
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                        ){
                            Image(painter = painterResource(id = R.drawable.img),
                                contentDescription ="green",
                                modifier = Modifier
                                    .size(70.dp)
                                    .clip(CircleShape),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text(
                                    text = "Watches",
                                    color = Color.Black,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 20.sp,
                                    fontFamily = FontFamily.Serif,
                                    modifier = Modifier.padding(top = 20.dp)
                                )
                            }
                        }
                    }

                    //end of card 3



                    //card 5
                    Card (modifier = Modifier
                        .clickable {navController.navigate(CLOTHINGviewSCREEN_URL) }
                        .fillMaxWidth()
                        .padding(20.dp)
                        .clip(
                            shape = RoundedCornerShape(
                                bottomStart = 30.dp,
                                topEnd = 30.dp
                            )
                        ),
                        colors = CardDefaults.cardColors(Color.LightGray)
                    ){
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                        ){
                            Image(painter = painterResource(id = R.drawable.clothing),
                                contentDescription ="green",
                                modifier = Modifier
                                    .size(70.dp)
                                    .clip(CircleShape),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text(
                                    text = "Clothes ",
                                    color = Color.Black,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 20.sp,
                                    fontFamily = FontFamily.Serif,
                                    modifier = Modifier.padding(top = 20.dp)
                                )

                            }
                        }
                    }

                    //end of card 5



                }
            }
        )
    }
}



val bottomNavItems = listOf(
    BottomNavItem(
        title = "Home",
        route="home",
        selectedIcon=Icons.Filled.Home,
        unselectedIcon=Icons.Outlined.Home,
        hasNews = false,
        badges=0
        ),

    BottomNavItem(
            title = "Add Products",
            route="add_products",
            selectedIcon=Icons.Filled.AddCircle,
            unselectedIcon=Icons.Outlined.AddCircle,
            hasNews = false,
            badges=5
         ),

    BottomNavItem(
        title = "View Products",
        route="view_products",
        selectedIcon=Icons.Filled.CheckCircle,
        unselectedIcon=Icons.Outlined.CheckCircle,
        hasNews = false,
        badges=0
    ),


)



data class BottomNavItem(
    val title :String,
    val route :String,
    val selectedIcon: ImageVector,
    val unselectedIcon :ImageVector,
    val hasNews :Boolean,
    val badges :Int
)



@Composable
@Preview(showBackground = true)
fun HomeScreenPreview(){
    WazitoECommerceTheme {
        HomeScreen(navController = rememberNavController())
    }
}