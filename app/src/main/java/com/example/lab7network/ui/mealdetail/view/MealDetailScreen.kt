package com.example.lab7network.ui.mealdetail.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MealDetailScreen(mealId: String) {
    val viewModel: MealDetailViewModel = viewModel()

    LaunchedEffect(mealId) {
        viewModel.getMealDetail(mealId)
    }

    val mealDetail by viewModel.mealDetail.collectAsState(null)
    val isLoading by viewModel.isLoading.collectAsState(false)
    val isError by viewModel.isError.collectAsState(false)

    val currentMealId by rememberUpdatedState(mealId)

    Column {
        if (isLoading) {
            Text("Cargando detalles de la comida...")
        } else if (isError) {
            Text("Se produjo un error al cargar los detalles de la comida")
        } else if (mealDetail != null) {
            Text(text = mealDetail!!.meals?.first()?.strMeal ?: "Nombre de la comida no disponible")
            Text(text = mealDetail!!.meals?.first()?.strInstructions ?: "Instrucciones no disponibles")
        }
    }
}
