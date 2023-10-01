package com.example.lab7network.ui.categories.view

import com.example.lab7network.navigation.NavigationState
import com.example.lab7network.navigation.response.CategoryResponse
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.AnnotatedString
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.lab7network.navigation.response.CategoriesResponse

@Composable
fun MealsCategoryScreen(categoryId: String, navController: NavController) {
    val viewModel: MealsCategoryModel = viewModel()

    // Utilizamos un MutableState para almacenar y actualizar la lista de comidas en la categoría
    val mealsState = remember { mutableStateOf<List<CategoryResponse>>(emptyList()) }

    println("MealsInCategoryScreen Invoked with categoryId: $categoryId")

    // Llamamos al método del ViewModel para obtener las comidas en la categoría
    viewModel.getMealsCategoryModel(
        categoryId = categoryId,
        successCallback = { response: CategoriesResponse? -> // Especifica el tipo de response
            println("Response in Screen: $response")

            response?.categories?.let { categories ->
                mealsState.value = categories
                println("Updated mealsState: ${mealsState.value}")
            }
        },
        errorCallback = { error: Throwable -> // Especifica el tipo de error
            // Manejar el error aquí, por ejemplo, mostrar un mensaje de error en la interfaz de usuario
        }
    )

    // Mostramos las comidas en un LazyColumn
    LazyColumn {
        items(mealsState.value) { meal ->
            ClickableText(
                text = AnnotatedString(text = meal.name),
                onClick = { offset ->
                    navController.navigate("${NavigationState.Detail.route}/${meal.idmeal}")
                }
            )
        }
    }
}

