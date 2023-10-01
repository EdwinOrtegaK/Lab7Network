package com.example.lab7network.ui.meals.view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.AnnotatedString
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.lab7network.navigation.response.MealResponse
import com.example.lab7network.navigation.NavigationState

@Composable
fun MealsCategoriesScreen(navController: NavController) {
    // Obtén el categoryId de los argumentos de la ruta
    val categoryId = remember { navController.currentBackStackEntry?.arguments?.getString("categoryId") }

    val viewModel: MealsCategoriesViewModel = viewModel()

    // Utilizamos un MutableState para almacenar y actualizar la lista de comidas
    val mealsState = remember { mutableStateOf<List<MealResponse>>(emptyList()) }

    // Llamamos al método del ViewModel para obtener las comidas
    viewModel.getMeals(
        successCallback = { response ->
            response?.categories?.let { meals ->
                // Filtrar las comidas por idCategory si está presente
                val filteredMeals = if (!categoryId.isNullOrBlank()) {
                    meals.filter { it.idCategory == categoryId }
                } else {
                    meals
                }
                mealsState.value = filteredMeals
            }
        },
        errorCallback = { error ->
            // Manejar el error aquí, por ejemplo, mostrar un mensaje de error en la interfaz de usuario
        }
    )

    // Mostramos las comidas en un LazyColumn
    LazyColumn {
        items(mealsState.value) { meal ->
            ClickableText(
                text = AnnotatedString(text = meal.name),
                onClick = { offset ->
                    // Navega a la pantalla de detalle pasando el id del meal
                    navController.navigate("${NavigationState.Detail.route}/${meal.idCategory}")
                }
            )
        }
    }
}



