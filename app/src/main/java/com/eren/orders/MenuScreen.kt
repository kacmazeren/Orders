package com.eren.orders

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.graphics.Color
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
    val orders = remember { mutableStateListOf<String>() }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(5.dp)
        ) {
            Text(text = "MENU")

            LazyColumn {
                items(count = MenuProvider.getFoodData().size) { index ->
                    val food = MenuProvider.getFoodData()[index]

                    MenuItemView(
                        food = food,
                        onItemClick = {
                            if (orders.contains(food.id.toString())) {
                                orders.remove(food.id.toString())
                            } else {
                                orders.add(food.id.toString())
                            }
                        },
                        isSelected = orders.contains(food.id.toString())
                    )
                }
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
fun MenuItemView(
    food: Food,
    onItemClick: () -> Unit,
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(),
        modifier = modifier,
        onClick = onItemClick
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .padding(5.dp)
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(food.foodName),
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Column(
                modifier = Modifier
                    .padding(5.dp)
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(food.price),
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Checkbox(
                checked = isSelected,
                onCheckedChange = null // Checkbox burada zaten tıklamayla değişmeyecek
            )
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
        isSelected = false, onItemClick = {})
    }
}