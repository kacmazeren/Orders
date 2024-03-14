package com.eren.orders

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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.eren.orders.data.MenuProvider
import com.eren.orders.model.Food
import com.eren.orders.ui.theme.OrdersTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuApp(navController: NavHostController) {
    var food :Food
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
                items(count = MenuProvider.getFoodData().size,
                    itemContent = {
                        food = MenuProvider.getFoodData()[it]

                        MenuItemView(
                            food = food,
                            onItemClick = {orders.add(food.toString())}
                        )
                    })
            }
            Button(onClick = {
                navController.navigate(route = "order_screen")
            }) {
                Text(text = "Continue")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuItemView(food: Food,
                 onItemClick: (Food) -> Unit,
                 modifier: Modifier = Modifier,

) {
    var checked by remember { mutableStateOf(false) }
    Card(
        elevation = CardDefaults.cardElevation(),
        modifier = modifier,
        onClick =  {   }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
                Column(
                modifier = Modifier
                    .padding(5.dp)
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(food.foodName),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier,
                )
                }
            Column(
                modifier = Modifier
                    .padding(5.dp)
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(food.price),
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                )
            }
            Checkbox(checked = checked, onCheckedChange ={isChecked ->
                checked = isChecked} )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun MenuPreview() {
    OrdersTheme {
        PagePasses()
    }
}
@Preview(showBackground = true)
@Composable
fun MenuItemViewPreview() {
    OrdersTheme {
        MenuItemView(food= MenuProvider.getFoodData().component1(),
        onItemClick = {})
    }
}