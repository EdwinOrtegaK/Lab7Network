package com.example.lab7network.ui.mealdetail.view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lab7network.navigation.response.MealDetailResponse

@Composable
fun MealDetailScreen(mealId: String) {
    val viewModel: MealDetailViewModel = viewModel()

    LaunchedEffect(mealId) {
        viewModel.getMealDetail(mealId)
    }

    val mealDetail by viewModel.mealDetail.collectAsState(null)

    RenderDishInfo(mealDetail)
}

@Composable
fun RenderDishInfo(dishDetail: MealDetailResponse?) {
    LazyColumn(content = {
        item {
            when (dishDetail) {
                null -> ShowLoadingState()
                else -> ShowDishData(dishDetail)
            }
        }
    })
}

@Composable
fun ShowDishData(dishData: MealDetailResponse) {
    val dish = dishData.meals?.first()
    Text(text = dish?.strMeal ?: "Esperando nombre del platillo...")
    Text(text = dish?.strInstructions ?: "Instrucciones no disponibles")
}

@Composable
fun ShowLoadingState() {
    Text("Recopilando información del platillo...")
}


