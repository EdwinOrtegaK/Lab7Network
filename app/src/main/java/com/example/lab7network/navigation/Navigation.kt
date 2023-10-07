package com.example.lab7network.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lab7network.ui.categories.view.MealsCategoryScreen
import com.example.lab7network.ui.mealdetail.view.MealDetailScreen
import com.example.lab7network.ui.meals.view.MealsCategoriesScreen

@Composable
fun Navigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = NavigationState.Home.route,
        modifier = modifier) {
        composable(route = NavigationState.Home.route) {
            MealsCategoriesScreen(navController)
        }
        composable(route = "${NavigationState.Category.route}/{categoryName}") { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getString("categoryId")
            if (categoryId != null) {
                // Navega a la pantalla de categorías y pasa el categoryId como argumento
                //navController.navigate(NavigationState.Home.route + "/$categoryId")
                MealsCategoryScreen(categoryId, navController)
            }
        }
        composable(route = "${NavigationState.Detail.route}/{categoryName}") { backStackEntry ->
            val mealId = backStackEntry.arguments?.getString("categoryName")
            if (mealId != null) {
                MealDetailScreen(mealId)
            }
        }
    }
}