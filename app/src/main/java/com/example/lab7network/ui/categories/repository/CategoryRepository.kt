package com.example.lab7network.ui.categories.repository

import android.accessibilityservice.TouchInteractionController
import com.example.lab7network.networking.MealsWebService
import com.example.lab7network.navigation.response.CategoriesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryRepository(private val webService: MealsWebService = MealsWebService()) {

    fun getMealsCategory(
        categoryId: String,
        successCallback: (response: CategoriesResponse?) -> Unit,
        errorCallback: (error: Throwable) -> Unit
    ) {
        println("Repository getMealsCategory Invoked with categoryId: $categoryId")

        webService.getMealsCategory(categoryId).enqueue(object: Callback<CategoriesResponse> {
            override fun onResponse(call: Call<CategoriesResponse>, response: Response<CategoriesResponse>) {
                println("API Call successful. Response: ${response.body()}, Error: ${response.errorBody()}")

                if (response.isSuccessful) {
                    successCallback(response.body())
                } else {
                    errorCallback(Throwable("Request failed with code ${response.code()}"))
                }
            }

            override fun onFailure(call: Call<CategoriesResponse>, t: Throwable) {
                println("API Call failed. Error: $t")
                errorCallback(t)
            }
        })
    }
}
