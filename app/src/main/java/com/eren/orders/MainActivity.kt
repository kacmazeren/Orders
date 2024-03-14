package com.eren.orders

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.eren.orders.ui.theme.OrdersTheme
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OrdersTheme {
                // A surface container using the 'background' color from the theme

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PagePasses()
                }
            }
        }
    }
}

@Composable
fun PagePasses(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "menu_app"){
        composable(route = "menu_app"){
            MenuApp(navController)
        }
        composable(route = "order_screen"){
            OrderScreen(navController)
        }
    }
}
@Preview
@Composable
fun AppPreview(){
    PagePasses()
}