package com.eren.orders.data

import com.eren.orders.R
import com.eren.orders.model.Food

object MenuProvider {

    val defaultFood = getFoodData()[0]

    fun getFoodData(): List<Food> {
        return listOf(
            Food(
                id = 1,
                foodName = R.string.food1,
                price = R.string.food1Price
                ),
            Food(
                id = 2,
                foodName = R.string.food2,
                price = R.string.food2Price
            ),
            Food(
                id = 3,
                foodName = R.string.food3,
                price = R.string.food3Price
            ),
            Food(
                id = 4,
                foodName = R.string.food4,
                price = R.string.food4Price
            )
        )
    }
}
