package com.example.TAntiques.ui.theme.screens.Clothes


import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.TAntiques.R
import com.example.TAntiques.data.ClothesViewModel
import com.example.TAntiques.ui.theme.Almond
import com.example.TAntiques.ui.theme.Beige
import com.example.TAntiques.ui.theme.WazitoECommerceTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClothesAddProducts(navController:NavHostController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.pillarbox),
                contentScale = ContentScale.FillBounds
            )
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {


        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Add new Clothing",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif,
            textAlign = TextAlign.Center,
            color = Color.White
        )



        var clothName by remember { mutableStateOf("") }
        var clothCategory by remember { mutableStateOf("") }
        var clothDescription by remember { mutableStateOf("") }
        var clothPrice by remember { mutableStateOf("") }
        val context = LocalContext.current

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = clothName,
            onValueChange = { clothName = it },
            placeholder = { Text(text = "Clothing name :ie Zari ",
                color = Color.Black,
                fontFamily = FontFamily.Serif,
                fontSize = 15.sp,
                fontWeight = FontWeight.ExtraBold
            ) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text,),
            colors = TextFieldDefaults.textFieldColors(Color.Black),
            modifier= Modifier
                .padding(start = 20.dp),
            shape = RoundedCornerShape(30.dp),

            )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = clothCategory,
            onValueChange = { clothCategory = it },
            placeholder = { Text(text = "Clothing category :ie coat",
                color = Color.Black,
                fontFamily = FontFamily.Serif,
                fontSize = 15.sp,
                fontWeight = FontWeight.ExtraBold
            ) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            colors = TextFieldDefaults.textFieldColors(Color.Black),
            modifier= Modifier
                .padding(start = 20.dp),
            shape = RoundedCornerShape(30.dp),


            )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = clothDescription,
            onValueChange = { clothDescription = it },
            placeholder =  { Text(text = "Clothing description :ie mahogany ",
                color = Color.Black,
                fontFamily = FontFamily.Serif,
                fontSize = 15.sp,
                fontWeight = FontWeight.ExtraBold
            )},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            colors = TextFieldDefaults.textFieldColors(Color.Black),
            modifier= Modifier
                .padding(start = 20.dp),
            shape = RoundedCornerShape(30.dp),

            )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = clothPrice,
            onValueChange = { clothPrice = it },
            placeholder = { Text(text = "Clothing price :ie ksh 30000",
                color = Color.Black,
                fontFamily = FontFamily.Serif,
                fontSize = 15.sp,
                fontWeight = FontWeight.ExtraBold
            ) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = TextFieldDefaults.textFieldColors(Color.Black),
            modifier= Modifier
                .padding(start = 30.dp),
            shape = RoundedCornerShape(30.dp),

            )

        Spacer(modifier = Modifier.height(20.dp))



        //---------------------IMAGE PICKER START-----------------------------------//

        var modifier = Modifier
        ImagePicker(modifier,context, navController, clothName.trim(), clothCategory.trim(), clothDescription.trim(),clothPrice.trim())

        //---------------------IMAGE PICKER END-----------------------------------//




    }
}

@Composable
fun ImagePicker(modifier: Modifier = Modifier, context: Context,navController: NavHostController, name:String, cartegory:String,description:String, price:String) {
    var hasImage by remember { mutableStateOf(false) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            hasImage = uri != null
            imageUri = uri
        }
    )

    Column(modifier = modifier,) {
        if (hasImage && imageUri != null) {
            val bitmap = MediaStore.Images.Media.
            getBitmap(context.contentResolver,imageUri)
            Image(bitmap = bitmap.asImageBitmap(), contentDescription = "Selected image")
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 40.dp),
            horizontalArrangement = Arrangement.Center){
            Button(
                onClick = {
                    imagePicker.launch("image/*")
                },
                shape = RoundedCornerShape(topStart = 10.dp),
                colors = ButtonDefaults.buttonColors(Almond),
            ) {
                Text(
                    text = "Select Image",
                    color = Color.Black,
                    fontFamily = FontFamily.Serif,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.ExtraBold

                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {
                //-----------WRITE THE UPLOAD LOGIC HERE---------------//
                var clothRepository = ClothesViewModel(navController,context)
                clothRepository.uploadCloth(name,cartegory,description, price,imageUri!!)


            },
                shape = RoundedCornerShape(topEnd = 10.dp),
                colors = ButtonDefaults.buttonColors(Beige),


                ) {
                Text(text = "Upload",
                    color = Color.Black,
                    fontFamily = FontFamily.Serif,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ClothesAddProductsPreview(){
    WazitoECommerceTheme {
        ClothesAddProducts(navController = rememberNavController())
    }
}