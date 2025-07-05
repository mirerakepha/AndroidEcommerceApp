package com.example.ecommerce.ui.theme.screens.cart

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors


import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ecommerce.R
import com.example.ecommerce.navigation.PRODUCTDETAILS_URL
import com.example.ecommerce.ui.theme.Orange3
import com.example.ecommerce.ui.theme.Purple40


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(navController: NavController) {


    val context = LocalContext.current

    TopAppBar(title = {Text(text = "Cart")},
        navigationIcon = {
            IconButton(onClick = {Toast.makeText(context, "cart", Toast.LENGTH_SHORT).show()} ) {
                Icon(painter = painterResource(id = R.drawable.cart), contentDescription = "cart")
            }
        }, colors = topAppBarColors(
            containerColor = Purple40,
            titleContentColor = Orange3
        )


    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .horizontalScroll(rememberScrollState())
            .background(color = Orange3),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Row 1
        Card {
            Box(
                modifier = Modifier.height(15.dp)
            ){
                Row (modifier = Modifier.fillMaxWidth(),){
                    //image left
                    Image( painter = painterResource(id = R.drawable.mouse),
                        contentDescription = "mouse",
                        modifier = Modifier.size(64.dp)
                    )

                    Spacer(modifier = Modifier.width(10.dp))


                    //text right
                    Column {
                        Text(text = "Wireless SVJ lenovo gamepad")

                        Spacer(modifier = Modifier.height(5.dp))

                        Text(text = "$ 15")
                    }


                }

            }
        }


        //ROW 2
        Card {
            Box(
                modifier = Modifier.height(15.dp)
            ){
                Row (modifier = Modifier.fillMaxWidth(),){
                    //image left
                    Image( painter = painterResource(id = R.drawable.mouse),
                        contentDescription = "mouse",
                        modifier = Modifier.size(64.dp)
                    )

                    Spacer(modifier = Modifier.width(10.dp))


                    //text right
                    Column {
                        Text(text = "Wireless SVJ lenovo gamepad")

                        Spacer(modifier = Modifier.height(5.dp))

                        Text(text = "$ 15")
                    }


                }

            }
        }


        //ROW 3
        Card {
            Box(
                modifier = Modifier.height(15.dp)
            ){
                Row (modifier = Modifier.fillMaxWidth(),){
                    //image left
                    Image( painter = painterResource(id = R.drawable.mouse),
                        contentDescription = "mouse",
                        modifier = Modifier.size(64.dp)
                    )

                    Spacer(modifier = Modifier.width(10.dp))


                    //text right
                    Column {
                        Text(text = "Wireless SVJ lenovo gamepad")

                        Spacer(modifier = Modifier.height(5.dp))

                        Text(text = "$ 15")
                    }


                }

            }
        }


        //ROW 4
        Card {
            Box(
                modifier = Modifier.height(15.dp)
            ){
                Row (modifier = Modifier.fillMaxWidth(),){
                    //image left
                    Image( painter = painterResource(id = R.drawable.mouse),
                        contentDescription = "mouse",
                        modifier = Modifier.size(64.dp)
                    )

                    Spacer(modifier = Modifier.width(10.dp))


                    //text right
                    Column {
                        Text(text = "Wireless SVJ lenovo gamepad")

                        Spacer(modifier = Modifier.height(5.dp))

                        Text(text = "$ 15")
                    }


                }

            }
        }

        //YOU MAY ALSO LIKE at the bottom

        Row(
            modifier = Modifier.padding(5.dp)
        ){
            Card {
                Column {
                    Box(modifier = Modifier
                        .height(150.dp)
                        .width(180.dp),
                        contentAlignment = Alignment.Center){
                        Image(
                            painter = painterResource(id = R.drawable.cockpit),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth()
                                .size(400.dp)
                                .clickable {
                                    navController.navigate(PRODUCTDETAILS_URL)
                                }
                        )

                    }
                    Text(text = "Cockpit",
                        textAlign = TextAlign.Center,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Orange3
                    )
                }
            }
            Spacer(modifier = Modifier.width(10.dp))


            Card (
                shape = RoundedCornerShape(12.dp)
            ){
                Column {
                    Box(modifier = Modifier
                        .height(150.dp)
                        .width(180.dp),
                        contentAlignment = Alignment.Center){
                        Image(
                            painter = painterResource(id = R.drawable.cockpit),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth()
                                .size(400.dp)
                                .clickable {
                                    navController.navigate(PRODUCTDETAILS_URL)
                                }
                        )

                    }
                    Text(text = "Cockpit",
                        textAlign = TextAlign.Center,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Orange3
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically

                    ) {
                        repeat(5) { index ->
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = "Star",
                                tint = Color(0xFFFFC107),
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.width(10.dp))
        }


























    }






}



@Preview(showBackground = true)
@Composable
fun CartScreenPreview() {
    CartScreen(navController = rememberNavController())
}