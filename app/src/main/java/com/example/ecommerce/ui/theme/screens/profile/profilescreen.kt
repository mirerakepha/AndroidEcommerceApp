package com.example.ecommerce.ui.theme.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ecommerce.R
import com.example.ecommerce.ui.theme.Orange3

@Composable
fun ProfileScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        //title
        Text(text = "My Orders",
            fontSize = 20.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row (

        ){
            //to pay
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //icon
                IconButton(onClick = {/*to the other page*/}) {
                    Image(painter = painterResource(R.drawable.cont),
                        contentDescription = "icon",
                        modifier = Modifier.size(20.dp)

                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
                //text
                Text(text = "To pay",

                    )
            }
            Spacer(modifier = Modifier.width(10.dp))




            //to ship
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //icon
                IconButton(onClick = {/*to the other page*/}) {
                    Image(painter = painterResource(R.drawable.container),
                        contentDescription = "icon",
                        modifier = Modifier.size(20.dp)

                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
                //text
                Text(text = "To ship",

                    )
            }
            Spacer(modifier = Modifier.width(10.dp))




            //shipped
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //icon
                IconButton(onClick = {/*to the other page*/}) {
                    Image(painter = painterResource(R.drawable.truck),
                        contentDescription = "icon",
                        modifier = Modifier.size(20.dp)

                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
                //text
                Text(text = "Shipped",

                    )
            }
            Spacer(modifier = Modifier.width(10.dp))






            //to review
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //icon
                IconButton(onClick = {/*to the other page*/}) {
                    Image(painter = painterResource(R.drawable.review),
                        contentDescription = "icon",
                        modifier = Modifier.size(20.dp)

                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
                //text
                Text(text = "To Review",

                    )
            }
            Spacer(modifier = Modifier.width(10.dp))





            //returns
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //icon
                IconButton(onClick = {/*to the other page*/}) {
                    Image(painter = painterResource(R.drawable.ret),
                        contentDescription = "icon",
                        modifier = Modifier.size(20.dp)

                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
                //text
                Text(text = "Returns",

                    )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))



        //END OF ROW 1

        //START OF ROW 2

        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
            //history
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //icon
                IconButton(onClick = {/*to the other page*/}) {
                    Image(painter = painterResource(R.drawable.clock),
                        contentDescription = "icon",
                        modifier = Modifier.size(20.dp)

                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
                //text
                Text(text = "History",

                    )
            }
            Spacer(modifier = Modifier.width(10.dp))



            //wishlist
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //icon
                IconButton(onClick = {/*to the other page*/}) {
                    Image(painter = painterResource(R.drawable.heart),
                        contentDescription = "icon",
                        modifier = Modifier.size(20.dp)

                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
                //text
                Text(text = "Wishlist",

                    )
            }
            Spacer(modifier = Modifier.width(10.dp))

            //coupons
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //icon
                IconButton(onClick = {/*to the other page*/}) {
                    Image(painter = painterResource(R.drawable.coupon),
                        contentDescription = "icon",
                        modifier = Modifier.size(20.dp)

                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
                //text
                Text(text = "Coupons",

                    )
            }
            Spacer(modifier = Modifier.width(10.dp))



            //store
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //icon
                IconButton(onClick = {/*to the other page*/}) {
                    Image(painter = painterResource(R.drawable.store),
                        contentDescription = "icon",
                        modifier = Modifier.size(20.dp)

                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
                //text
                Text(text = "Stores",

                    )
            }

        }

        Spacer(modifier = Modifier.height(10.dp))
        //END OF ROW 2




        //ROW 3
        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
            //help canter
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //icon
                IconButton(onClick = {/*to the other page*/}) {
                    Image(painter = painterResource(R.drawable.support),
                        contentDescription = "icon",
                        modifier = Modifier.size(20.dp)

                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
                //text
                Text(text = "Help Center",

                    )
            }
            Spacer(modifier = Modifier.width(10.dp))







            //payment
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //icon
                IconButton(onClick = {/*to the other page*/}) {
                    Image(painter = painterResource(R.drawable.wallet),
                        contentDescription = "icon",
                        modifier = Modifier.size(20.dp)

                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
                //text
                Text(text = "Payment",

                    )
            }
            Spacer(modifier = Modifier.width(10.dp))


            //bonus
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //icon
                IconButton(onClick = {/*to the other page*/}) {
                    Image(painter = painterResource(R.drawable.bonus),
                        contentDescription = "icon",
                        modifier = Modifier.size(20.dp)

                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
                //text
                Text(text = "Bonus",

                    )
            }
            Spacer(modifier = Modifier.width(10.dp))



            // shopping credits
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //icon
                IconButton(onClick = {/*to the other page*/}) {
                    Image(painter = painterResource(R.drawable.cred),
                        contentDescription = "icon",
                        modifier = Modifier.size(20.dp)

                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
                //text
                Text(text = "Shopping Credits",

                    )
            }
            Spacer(modifier = Modifier.width(10.dp))




            //suggestions
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //icon
                IconButton(onClick = {/*to the other page*/}) {
                    Image(painter = painterResource(R.drawable.suggestion),
                        contentDescription = "icon",
                        modifier = Modifier.size(20.dp)

                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
                //text
                Text(text = "Suggestion",

                    )
            }

        }

        //you may like
        //ROW 1
        //ROW 2
        //ROW 3






    }






}



@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(navController = rememberNavController())
}