package com.example.lab7network.ui.meals.view

import androidx.lifecycle.ViewModel
import com.example.lab7network.navigation.response.MealsCategoriesResponse
import com.example.lab7network.ui.meals.repository.MealsRepository

class MealsCategoriesViewModel(private val repository: MealsRepository = MealsRepository()) : ViewModel() {

    fun getMeals(
        successCallback: (response: MealsCategoriesResponse?) -> Unit,
        errorCallback: (error: Throwable) -> Unit
    ) {
        repository.getMeals(
            successCallback = { response ->
                successCallback(response)
            },
            errorCallback = { error ->
                errorCallback(error)
            }
        )
    }
}
