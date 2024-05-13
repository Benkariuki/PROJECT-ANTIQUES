package com.example.TAntiques.ui.theme.screens.Watches

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.TAntiques.data.WatchesViewModel
import com.example.TAntiques.models.Watch
import com.example.TAntiques.navigation.FURNITUREaddSCREEN_URL
import com.example.TAntiques.navigation.WATCHaddSCREEN_URL
import com.example.TAntiques.ui.theme.Almond
import com.example.TAntiques.ui.theme.Beige
import com.example.TAntiques.ui.theme.WazitoECommerceTheme

@Composable
fun WatchViewProducts(navController:NavHostController) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        var context = LocalContext.current
        var watchesRepository = WatchesViewModel(navController, context)


        val emptyWatchesState = remember { mutableStateOf(Watch("","","","","","")) }
        var emptyWatcheesListState = remember { mutableStateListOf<Watch>() }

        var watchees= watchesRepository. allWatches(emptyWatchesState, emptyWatcheesListState)


        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Beige),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(15.dp))

            Row {

                Text(text = "All Watches",
                    fontSize = 30.sp,
                    fontFamily = FontFamily.Serif,
                    color = Color.Black,)

                FloatingActionButton(onClick = {},
                    containerColor = Color.Black,
                    modifier = Modifier
                        .height(30.dp)
                        .width(100.dp)
                        .fillMaxWidth()
                        .padding(start = 47.dp, top = 7.dp),
                    ) {
                    Text(text = "Add ",
                        color = Color.White,
                        fontFamily = FontFamily.Serif,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier
                            .clickable {navController.navigate(WATCHaddSCREEN_URL)},

                        )
                }

            }


            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(){
                items(watchees){
                    WatchesItem(
                        name = it.name,
                        category = it.category,
                        description = it.description,
                        price = it.price,
                        id = it.id,
                        navController = navController,
                        watchesRepository = watchesRepository,
                        productImage = it.imageUrl
                    )
                }
            }
        }
    }
}


@Composable
fun WatchesItem(name:String, category:String, description:String, price:String, id:String,
                  navController:NavHostController,
                watchesRepository:WatchesViewModel, productImage:String) {

    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        var  mContext = LocalContext.current

        Card (modifier= Modifier
            .height(320.dp)
            .width(300.dp)
            .padding(bottom = 10.dp)){

            Image(
                painter = rememberAsyncImagePainter(productImage),
                contentDescription = null,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
            )

            Text(text = name,
                fontSize = 15.sp,
                fontFamily = FontFamily.Serif,
                color = Color.Black
            )
            Text(text = category,
                fontSize = 10.sp,
                fontFamily = FontFamily.Serif,
                color = Color.Black)

            Text(text = description,
                fontSize = 10.sp,
                fontFamily = FontFamily.Serif,
                color = Color.Black)


            Text(text = price,
                fontSize = 10.sp,
                fontFamily = FontFamily.Serif,
                color = Color.Black
            )

            Row (){

                Button(onClick = {
                    watchesRepository.deletewatch(id)                },
                    shape = RoundedCornerShape(topStart = 10.dp),
                    colors = ButtonDefaults.buttonColors(Almond),
                ) {
                    Text(text = "Delete",
                        color = Color.Black,
                        fontFamily = FontFamily.Serif,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
                Button(onClick = {
                    navController.navigate(FURNITUREaddSCREEN_URL+"")
                },
                    shape = RoundedCornerShape(topEnd = 10.dp),
                    colors = ButtonDefaults.buttonColors(Beige),
                ) {
                    Text(text = "Update",
                        color = Color.Black,
                        fontFamily = FontFamily.Serif,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }

                Icon(imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Buy",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 40.dp, top = 7.dp)
                        .clickable {
                            val simToolKitLaunchIntent =
                                mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
                            simToolKitLaunchIntent?.let { mContext.startActivity(it) }
                        },)
            }

        }

    }
}

@Composable
@Preview(showBackground = true)
fun FurnitureViewProductsPreview(){
    WazitoECommerceTheme {
        WatchViewProducts(navController = rememberNavController())
    }
}