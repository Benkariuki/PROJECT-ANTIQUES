package com.example.TAntiques.ui.theme.screens.products


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
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
import com.example.TAntiques.data.ProductViewModel
import com.example.TAntiques.models.Product
import com.example.TAntiques.navigation.ADD_PRODUCTS_URL
import com.example.TAntiques.ui.theme.WazitoECommerceTheme

@Composable
fun ViewProductsScreen(navController:NavHostController) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        var context = LocalContext.current
        var productRepository = ProductViewModel(navController, context)


        val emptyProductState = remember { mutableStateOf(Product("","","","","","",)) }
        var emptyProductsListState = remember { mutableStateListOf<Product>() }

        var products = productRepository.allProducts(emptyProductState, emptyProductsListState)


        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "All Clothes ",
                fontSize = 30.sp,
                fontFamily = FontFamily.Cursive,
                color = Color.Red)
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
                        .clickable {navController.navigate(ADD_PRODUCTS_URL)},

                    )
            }

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(){
                items(products){
                    ProductItem(
                        name = it.name,
                        category = it.category,
                        description = it.description,
                        price = it.price,
                        id = it.id,
                        navController = navController,
                        productRepository = productRepository,
                        productImage = it.imageUrl
                    )
                }
            }
        }
    }
}


@Composable
fun ProductItem(name:String, category:String,  description:String,price:String, id:String,
                navController:NavHostController,
                productRepository:ProductViewModel, productImage:String) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = name)
        Text(text = category)
        Text(text = description)
        Text(text = price)
        Image(
            painter = rememberAsyncImagePainter(productImage),
            contentDescription = null,
            modifier = Modifier.size(250.dp)
        )
        Button(onClick = {
            productRepository.deleteProduct(id)
        }) {
            Text(text = "Delete")
        }
        Button(onClick = {
            //navController.navigate(ROUTE_UPDATE_PRODUCTS+"/$id")
        }) {
            Text(text = "Update")
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ViewProductsScreenPreview(){
    WazitoECommerceTheme {
        ViewProductsScreen(navController = rememberNavController())
    }
}