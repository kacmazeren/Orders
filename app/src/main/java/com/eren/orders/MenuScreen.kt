package com.eren.orders

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eren.orders.ui.theme.OrdersTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuApp() {
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var order by remember { mutableStateOf("") }
    var food = ""
    var numberList by remember { mutableStateOf<List<Int>>(emptyList()) }
    val menu = remember {
        mutableStateListOf("Pizza $10", "Pasta $12", "Hamburger $14,90", "Soup $7,50")
    }
    val orders = remember {
        mutableStateListOf("")
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(5.dp)
        ) {
            Text(text = "MENU")

            LazyColumn {
                items(count = menu.count(),
                    itemContent = {
                        food = menu[it]
                        Card(
                            modifier = Modifier
                                .padding(5.dp)
                                .fillMaxWidth()
                        ) {

                            Row {

                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(5.dp)
                                ) {
                                    Text(text = "$it $food", modifier = Modifier.padding(5.dp))
                                }
                            }
                        }
                    })

            }
        }
    }

}
@Composable
fun MenuItemView(){

}
@Preview(showBackground = true)
@Composable
fun MenuPreview() {
    OrdersTheme {
        MenuApp()
    }
}