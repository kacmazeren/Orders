package com.eren.orders

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.eren.orders.ui.theme.OrdersTheme

@Composable
fun OrderScreen(navController: NavHostController) {
    val orders: List<String> = remember {
        val route = navController.currentBackStackEntry?.arguments?.getString("orders") ?: ""
        route.split(",")
    }

    LazyColumn {
        items (count = orders.size) {
            for (i in orders.indices) {
                Column(modifier = Modifier.fillMaxSize().background(color = Color.Blue)) {
                Text(text = orders[i])
                }
            }
        }
    }
}
@Preview
@Composable
fun OrderScreenPreview() {
    OrdersTheme {
        OrderScreen(navController = rememberNavController())
    }
}