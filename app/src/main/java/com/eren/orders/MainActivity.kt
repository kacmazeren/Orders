package com.eren.orders

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
                    Menu()
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Menu(){
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

    Box(modifier = Modifier){
        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(5.dp)) {
            Text(text = "MENU")

    LazyColumn{
        items(count = menu.count(),
            itemContent = {
             food = menu[it]

                Card(modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth()
                ) {

                    Row {

                        Row(verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(5.dp)) {
                            Text(text = "$it $food", modifier = Modifier.padding(5.dp))
                        }
                    }
                }
        })

    }
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row() {
                Text(text = "ORDER", modifier = Modifier.padding(16.dp))
            }
            Row() {
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") }
                )
            }
            Row() {
                TextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = { Text("Phone") }
                )
            }

            Row() {
                TextField(
                    value = order,
                    onValueChange = { order = it
                        numberList = it.split(",").map { str -> str.trim().toIntOrNull() ?: 0}
                        numberList = numberList.filter { T -> T<menu.size }},
                    label = { Text("Order like: 1,4,2,3") }
                )
            }
            val context = LocalContext.current
            Button(
                onClick = {
                    for (number in numberList) {
                        val orderString = menu[number]
                        orders.add(orderString)
                    }

                    Toast.makeText(context, "Hello, $name!", Toast.LENGTH_SHORT).show()

                },

                modifier = Modifier
            ) {
                Text(text = "Order", modifier = Modifier)
            }
            Text(text = "Name: $name \nPhone: $phone  \nOrder: ${orders.joinToString(" -- ")}",
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth())
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrderPreview() {
    OrdersTheme {
        Menu()
    }
}