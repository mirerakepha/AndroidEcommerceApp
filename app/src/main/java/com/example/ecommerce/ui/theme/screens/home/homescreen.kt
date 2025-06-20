package com.example.ecommerce.ui.theme.screens.home
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
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
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ecommerce.navigation.CART_URL
import com.example.ecommerce.navigation.NOTIFICATIONS_URL
import com.example.ecommerce.navigation.SETTINGS_URL


data class Screen(val title: String, val icon: Int)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController:NavHostController){

    var selected by remember { mutableIntStateOf(0) }
    Scaffold(





        bottomBar = {
            NavigationBar {
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
                                            Text(text = bottomNavItem.badges.toString())
                                        }
                                    } else if (bottomNavItem.hasNews) {
                                        Badge()
                                    }
                                }
                            ) {
                                Icon(imageVector =
                                    if (index == selected)
                                        bottomNavItem.selectedIcon
                                    else
                                        bottomNavItem.unselectedIcon,
                                    contentDescription = bottomNavItem.title)
                            }

                        },
                        label = {
                            Text(text = bottomNavItem.title)
                        })
                }
            }
        },


        floatingActionButton = {
            FloatingActionButton(onClick = {
            }) {
                IconButton(onClick = {
                    navController.navigate(CART_URL)

                }) {
                    Icon(imageVector = Icons.Default.Add,
                        contentDescription = "menu")
                }
            }
        },
        topBar = {
            TopAppBar(title = { Text(text = "")},
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
        content = @Composable{
            Column (
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){


                Row {
                    Image(painter = painterResource(id = R.drawable.img) ,
                        contentDescription = "",
                        modifier = Modifier.size(100.dp))

                    Text(
                        text = "rit",
                        fontSize = 60.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Magenta,
                        fontFamily = FontFamily.Cursive

                    )
                }

                Text(
                    text = "Your hostel companion",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif

                )


                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Let's manage your Hostel Things",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif

                )


                Text(
                    text = "Quick Access",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif

                )


                Row {

                    Card (
                        modifier = Modifier
                            .clickable {
                                navController.navigate(ADDCOMPLAINT_URL)
                            }
                            .height(300.dp)
                            .width(150.dp)){
                        Spacer(modifier = Modifier.height(30.dp))

                        Column {
                            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
                                Image(painter = painterResource(id = R.drawable.img_3) ,
                                    contentDescription = "",
                                    modifier = Modifier.size(100.dp))

                            }
                            Spacer(modifier = Modifier.height(30.dp))

                            Text(
                                text = "Post a",
                                fontSize = 15.sp,
                                fontFamily = FontFamily.SansSerif,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Center,

                                )
                            Text(
                                text = "Complaint",
                                fontSize = 20.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Center,

                                )

                        }




                    }
                    Spacer(modifier = Modifier.width(30.dp))
                    Spacer(modifier = Modifier.height(30.dp))


                    Column {



                        Card (
                            modifier = Modifier
                                .clickable {
                                    navController.navigate(VIEWCOMPLAINT_URL)
                                }
                                .height(120.dp)
                                .width(150.dp),
                            colors = CardDefaults.cardColors(Pink)){
                            Spacer(modifier = Modifier.height(30.dp))

                            Row {
                                Spacer(modifier = Modifier.width(5.dp))

                                Image(painter = painterResource(id = R.drawable.img_5) ,
                                    contentDescription = "",
                                    modifier = Modifier.size(80.dp))

                                Spacer(modifier = Modifier.width(5.dp))

                                Column {
                                    Text(
                                        text = "History",
                                        fontSize = 15.sp,
                                        fontFamily = FontFamily.SansSerif,
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        textAlign = TextAlign.Center,

                                        )
                                    Text(
                                        text = "complaints",
                                        fontSize = 11.sp,
                                        fontFamily = FontFamily.SansSerif,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        textAlign = TextAlign.Center,

                                        )
                                }

                            }




                        }


                        Spacer(modifier = Modifier.height(30.dp))



                        Card (
                            modifier = Modifier

                                .height(120.dp)
                                .width(150.dp),
                            colors = CardDefaults.cardColors(Color.Yellow)){
                            Spacer(modifier = Modifier.height(30.dp))


                            Row {
                                Spacer(modifier = Modifier.width(5.dp))

                                Image(painter = painterResource(id = R.drawable.contact) ,
                                    contentDescription = "",
                                    modifier = Modifier.size(80.dp))

                                Spacer(modifier = Modifier.width(5.dp))

                                Column {
                                    Text(
                                        text = "Contact",
                                        fontSize = 15.sp,
                                        fontFamily = FontFamily.SansSerif,
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        textAlign = TextAlign.Center,

                                        )
                                    Text(
                                        text = "Admin",
                                        fontSize = 19.sp,
                                        fontFamily = FontFamily.SansSerif,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        textAlign = TextAlign.Center,

                                        )
                                }

                            }




                        }


                    }




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
        title = "Add Complain",
        route="add_complaint",
        selectedIcon=Icons.Filled.Add,
        unselectedIcon=Icons.Outlined.Add,
        hasNews = true,
        badges=0
    ),

    BottomNavItem(
        title = "Admin",
        route="login",
        selectedIcon= Icons.Filled.Person,
        unselectedIcon=Icons.Outlined.Person,
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



@Composable
@Preview(showBackground = true)
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

























