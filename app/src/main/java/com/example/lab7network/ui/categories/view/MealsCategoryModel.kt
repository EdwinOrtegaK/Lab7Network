package com.example.lab7network.ui.categories.view

import androidx.lifecycle.ViewModel
import com.example.lab7network.navigation.response.CategoriesResponse
import com.example.lab7network.ui.categories.repository.CategoryRepository

class MealsCategoryModel(private val repository: CategoryRepository = CategoryRepository()): ViewModel() {
    fun getMealsCategoryModel(
        categoryId: String,
        successCallback: (response: CategoriesResponse?) -> Unit,
        errorCallback: (error: Throwable) -> Unit
    ) {
        println("ViewModel getMealsCategory Invoked with categoryId: $categoryId")

        repository.getMealsCategory(categoryId,
            successCallback = { response ->
                successCallback(response)
            },
            errorCallback = { error ->
                errorCallback(error)
            }
        )
    }
}

