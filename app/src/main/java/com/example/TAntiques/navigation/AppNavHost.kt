package com.example.TAntiques.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.TAntiques.ui.theme.screens.Clothes.ClothesAddProducts
import com.example.TAntiques.ui.theme.screens.Clothes.ClothingViewProducts
import com.example.TAntiques.ui.theme.screens.Furniture.FurnitureAddProducts
import com.example.TAntiques.ui.theme.screens.Furniture.FurnitureViewProducts
import com.example.TAntiques.ui.theme.screens.Splashscreen.Splashscreen
import com.example.TAntiques.ui.theme.screens.Vehicles.VehicleAddProducts
import com.example.TAntiques.ui.theme.screens.Vehicles.VehicleViewProducts
import com.example.TAntiques.ui.theme.screens.Watches.WatchViewProducts
import com.example.TAntiques.ui.theme.screens.Watches.WatchesAddProducts
import com.example.TAntiques.ui.theme.screens.home.HomeScreen
import com.example.TAntiques.ui.theme.screens.login.LoginScreen
import com.example.TAntiques.ui.theme.screens.products.AddProductsScreen
import com.example.TAntiques.ui.theme.screens.signup.SignupScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController:NavHostController = rememberNavController(),
    startDestination:String = SPLASHSCREEN_URL
){
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier){
        composable(LOGIN_URL){
            LoginScreen(navController = navController)
        }

        composable(SPLASHSCREEN_URL){
            Splashscreen(navController = navController)
        }


        composable(SIGNUP_URL){
            SignupScreen(navController = navController)
        }
        composable(HOME_URL){
            HomeScreen(navController = navController)
        }
        composable(ADD_PRODUCTS_URL){
            AddProductsScreen(navController = navController)
        }
        composable(VEHICLEaddSCREEN_URL){
            VehicleAddProducts(navController = navController)
        }

        composable(VEHICLEviewSCREEN_URL){
            VehicleViewProducts(navController = navController)
        }

        composable(FURNITUREaddSCREEN_URL){
            FurnitureAddProducts(navController = navController)
        }
        composable(FURNITUREviewSCREEN_URL){
            FurnitureViewProducts(navController = navController)
        }


        composable(WATCHaddSCREEN_URL){
            WatchesAddProducts(navController = navController)
        }

        composable(WATCHviewSCREEN_URL){
            WatchViewProducts(navController = navController)
        }

        composable(CLOTHINGaddSCREEN_URL){
            ClothesAddProducts(navController = navController)
        }
        composable(CLOTHINGviewSCREEN_URL){
            ClothingViewProducts(navController = navController)
        }



    }
}

