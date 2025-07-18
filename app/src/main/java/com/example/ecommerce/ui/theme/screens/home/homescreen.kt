package com.example.ecommerce.ui.theme.screens.home
import android.R.attr.enabled
import android.annotation.SuppressLint
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

import com.example.ecommerce.navigation.CART_URL
import com.example.ecommerce.navigation.NOTIFICATIONS_URL
import com.example.ecommerce.navigation.PRODUCTDETAILS_URL
import com.example.ecommerce.navigation.SETTINGS_URL
import com.example.ecommerce.ui.theme.Orange3
import com.example.ecommerce.R


data class Screen(val title: String, val icon: Int)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController:NavHostController){

    var selected by remember { mutableIntStateOf(0) }
    Scaffold(
//bottom bar
        bottomBar = {
            Box(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color.White)
                    .shadow(8.dp, RoundedCornerShape(24.dp))
            ) {
                NavigationBar(
                    modifier = Modifier
                        .height(64.dp)
                        .background(Color.White),
                    tonalElevation = 0.dp,
                    containerColor = Color.White
                ) {
                    bottomNavItems.forEachIndexed { index, bottomNavItem ->
                        NavigationBarItem(
                            selected = index == selected,
                            onClick = {
                                selected = index
                                navController.navigate(bottomNavItem.route)
                            },
                            icon = {
                                BadgedBox(
                                    badge = {
                                        if (bottomNavItem.badges != 0) {
                                            Badge {
                                                Text(
                                                    text = bottomNavItem.badges.toString(),
                                                    fontSize = 8.sp
                                                )
                                            }
                                        } else if (bottomNavItem.hasNews) {
                                            Badge()
                                        }
                                    }
                                ) {
                                    Icon(
                                        imageVector = if (index == selected)
                                            bottomNavItem.selectedIcon
                                        else
                                            bottomNavItem.unselectedIcon,
                                        contentDescription = bottomNavItem.title,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            },
                            label = {
                                Text(
                                    text = bottomNavItem.title,
                                    fontSize = 10.sp,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            },
                            alwaysShowLabel = true
                        )
                    }
                }
            }
        },


                floatingActionButton = {
            FloatingActionButton(onClick = {CART_URL}) {
                IconButton(onClick = {
                    navController.navigate(CART_URL)

                }) {
                    Icon(imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "cart")
                }
            }
        },
        topBar = {
            TopAppBar(title = { Text(text = "Las Noches")},
                navigationIcon = {
                    IconButton(onClick = { SETTINGS_URL }) {
                        Icon(imageVector = Icons.Default.Settings,
                            contentDescription = "settings")
                    }
                },
                actions = {
                    IconButton(onClick = { NOTIFICATIONS_URL }) {
                        Icon(imageVector = Icons.Default.Notifications,
                            contentDescription = "notification icon")
                    }
                })
        },

        content =
            {innerPadding ->
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 5.dp)
                    .padding(innerPadding),

                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                val mContext = LocalContext.current
                var search by remember { mutableStateOf("") }

     //S E A R C H       B A R
                OutlinedTextField(value = search,
                    onValueChange = { search = it },
                    placeholder = { Text(text = "find products")},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "search"
                        )
                    },
                    shape = RoundedCornerShape(30.dp)
                    )
                Spacer(modifier = Modifier.height(10.dp))
        //E N D   O F   S E A R C H   B A R

        //T O P   R O W WITH CLICKABLE TEXT




        //E N D   O F   T O P   R O W
              Spacer(modifier = Modifier.height(10.dp))



            //full page with cards
            Column (modifier = Modifier.verticalScroll(rememberScrollState())){
                //R O W   1
                Row(modifier = Modifier.padding(start = 20.dp)
                    .horizontalScroll(rememberScrollState())) {


                    //C A R D   1
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
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = Orange3,
                                modifier = Modifier.padding(horizontal = 10.dp)
                            )
                            Text(text = "20 $",
                                textAlign = TextAlign.Center,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = Orange3,
                                modifier = Modifier.padding(10.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(10.dp))

                    //C A R D   2
                    Card {
                        Column {
                            Box(modifier = Modifier
                                .height(150.dp)
                                .width(180.dp),
                                contentAlignment = Alignment.Center){
                                Image(
                                    painter = painterResource(id = R.drawable.gamechair),
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
                            Text(text = "Gaming Chair",
                                textAlign = TextAlign.Center,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = Orange3,
                                modifier = Modifier.padding(horizontal = 10.dp)
                            )
                            Text(text = "20 $",
                                textAlign = TextAlign.Center,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = Orange3,
                                modifier = Modifier.padding(10.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(10.dp))

                    //C A R D   4
                    Card {
                        Column {
                            Box(modifier = Modifier
                                .height(150.dp)
                                .width(180.dp),
                                contentAlignment = Alignment.Center){
                                Image(
                                    painter = painterResource(id = R.drawable.headphones),
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
                            Text(text = "Gaming Headphones",
                                textAlign = TextAlign.Center,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = Orange3,
                                modifier = Modifier.padding(horizontal = 10.dp)
                            )
                            Text(text = "20 $",
                                textAlign = TextAlign.Center,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = Orange3,
                                modifier = Modifier.padding(10.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    //C A R D   5
                    Card {
                        Column {
                            Box(modifier = Modifier
                                .height(150.dp)
                                .width(180.dp),
                                contentAlignment = Alignment.Center){
                                Image(
                                    painter = painterResource(id = R.drawable.mouse),
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
                            Text(text = "Gaming Mouse",
                                textAlign = TextAlign.Center,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = Orange3,
                                modifier = Modifier.padding(horizontal = 10.dp)
                            )
                            Text(text = "20 $",
                                textAlign = TextAlign.Center,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = Orange3,
                                modifier = Modifier.padding(10.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    //C A R D   6
                    Card {
                        Column {
                            Box(modifier = Modifier
                                .height(150.dp)
                                .width(180.dp),
                                contentAlignment = Alignment.Center){
                                Image(
                                    painter = painterResource(id = R.drawable.nintendo),
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
                            Text(text = "Nintendo Switch",
                                textAlign = TextAlign.Center,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = Orange3,
                                modifier = Modifier.padding(horizontal = 10.dp)
                            )
                            Text(text = "20 $",
                                textAlign = TextAlign.Center,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = Orange3,
                                modifier = Modifier.padding(10.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    //C A R D   7
                    Card {
                        Column {
                            Box(modifier = Modifier
                                .height(150.dp)
                                .width(180.dp),
                                contentAlignment = Alignment.Center){
                                Image(
                                    painter = painterResource(id = R.drawable.screen),
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
                            Text(text = "Gaming Monitor",
                                textAlign = TextAlign.Center,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = Orange3,
                                modifier = Modifier.padding(horizontal = 10.dp)
                            )
                            Text(text = "20 $",
                                textAlign = TextAlign.Center,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = Orange3,
                                modifier = Modifier.padding(10.dp)
                            )
                        }
                    }





                }

                Spacer(modifier = Modifier.height(20.dp))

                //ROW 2
                Row(modifier = Modifier.padding(start = 20.dp)
                    .horizontalScroll(rememberScrollState())) {


                    //C A R D   1
                    Card {
                        Column {
                            Box(modifier = Modifier
                                .height(150.dp)
                                .width(180.dp),
                                contentAlignment = Alignment.Center){
                                Image(
                                    painter = painterResource(id = R.drawable.itachih),
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
                            Text(text = "Hoodie",
                                textAlign = TextAlign.Center,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = Orange3,
                                modifier = Modifier.padding(horizontal = 10.dp)
                            )
                            Text(text = "20 $",
                                textAlign = TextAlign.Center,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = Orange3,
                                modifier = Modifier.padding(10.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(10.dp))

                    //C A R D   2
                    Card {
                        Column {
                            Box(modifier = Modifier
                                .height(150.dp)
                                .width(180.dp),
                                contentAlignment = Alignment.Center){
                                Image(
                                    painter = painterResource(id = R.drawable.naruto),
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
                            Text(text = "T-Shirt",
                                textAlign = TextAlign.Center,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = Orange3,
                                modifier = Modifier.padding(horizontal = 10.dp)
                            )
                            Text(text = "20 $",
                                textAlign = TextAlign.Center,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = Orange3,
                                modifier = Modifier.padding(10.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(10.dp))

                    //C A R D   4
                    Card {
                        Column {
                            Box(modifier = Modifier
                                .height(150.dp)
                                .width(180.dp),
                                contentAlignment = Alignment.Center){
                                Image(
                                    painter = painterResource(id = R.drawable.sweatpants),
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
                            Text(text = "Sweat Pants",
                                textAlign = TextAlign.Center,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = Orange3,
                                modifier = Modifier.padding(horizontal = 10.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    //C A R D   5
                    Card {
                        Column {
                            Box(modifier = Modifier
                                .height(150.dp)
                                .width(180.dp),
                                contentAlignment = Alignment.Center){
                                Image(
                                    painter = painterResource(id = R.drawable.sweatshirt),
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
                            Text(text = "Sweat Shirt",
                                textAlign = TextAlign.Center,
                                fontSize = 30.sp,
                                fontWeight = FontWeight.Bold,
                                color = Orange3,
                                modifier = Modifier.padding(horizontal = 10.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    //C A R D   6
                    Card {
                        Column {
                            Box(modifier = Modifier
                                .height(150.dp)
                                .width(180.dp),
                                contentAlignment = Alignment.Center){
                                Image(
                                    painter = painterResource(id = R.drawable.trench),
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
                            Text(text = "Trench Coat",
                                textAlign = TextAlign.Center,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = Orange3,
                                modifier = Modifier.padding(horizontal = 10.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    //C A R D   7
                    Card {
                        Column {
                            Box(modifier = Modifier
                                .height(150.dp)
                                .width(180.dp),
                                contentAlignment = Alignment.Center){
                                Image(
                                    painter = painterResource(id = R.drawable.shirt),
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
                            Text(text = "Official Shirt",
                                textAlign = TextAlign.Center,
                                fontSize = 30.sp,
                                fontWeight = FontWeight.Bold,
                                color = Orange3,
                                modifier = Modifier.padding(horizontal = 10.dp)
                            )
                        }
                    }





                }

                Spacer(modifier = Modifier.height(20.dp))
                //ROW 3
                //ROW 4
                //ROW 5
            }




















            }







        }


    )


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
        title = "Messages",
        route="add_complaint",
        selectedIcon=Icons.Filled.MailOutline,
        unselectedIcon=Icons.Outlined.MailOutline,
        hasNews = true,
        badges=0
    ),

    BottomNavItem(
        title = "Messages",
        route="add_complaint",
        selectedIcon=Icons.Filled.MailOutline,
        unselectedIcon=Icons.Outlined.MailOutline,
        hasNews = false,
        badges=0
    ),

    BottomNavItem(
        title = "Account",
        route="account",
        selectedIcon= Icons.Filled.AccountCircle,
        unselectedIcon=Icons.Outlined.AccountCircle,
        hasNews = true,
        badges=1
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




@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
        HomeScreen(navController = rememberNavController())

}


    //T O P    A P P   B A R

    /*


    TopAppBar(title = {Text(text = "Las Noches")},
        navigationIcon = {
            IconButton(onClick = {CART_URL}) {
                Icon(painter = painterResource(id = R.drawable.cart), contentDescription = "cart icon")
            }
        }, colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Orange3,
            titleContentColor = Orange3
        ), actions = {

            //N O T I F I C A T I O N   I C O N
            IconButton(onClick = {NOTIFICATIONS_URL}) {
                Icon(imageVector = Icons.Filled.FavoriteBorder ,
                     contentDescription = "notification_icon",
                     tint = Orange3)
            }


            // S E T T I N G S    I C O N
            IconButton(onClick = { SETTINGS_URL }) {
                Icon(imageVector = Icons.Filled.Settings ,
                    contentDescription = "settings_icon",
                    tint = Orange3)
            }
        }

        )*/


    //B   O   D   Y

























