package com.example.lab7network.ui.meals.repository

import com.example.lab7network.networking.MealsWebService
import com.example.lab7network.navigation.response.MealsCategoriesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealsRepository(private val webService: MealsWebService = MealsWebService()) {

    fun getMeals(
        successCallback: (response: MealsCategoriesResponse?) -> Unit,
        errorCallback: (error: Throwable) -> Unit
    ) {
        webService.getMeals().enqueue(object : Callback<MealsCategoriesResponse> {
            override fun onResponse(
                call: Call<MealsCategoriesResponse>,
                response: Response<MealsCategoriesResponse>
            ) {
                if (response.isSuccessful) {
                    successCallback(response.body())
                } else {
                    errorCallback(Throwable("Request failed with code ${response.code()}"))
                }
            }
            override fun onFailure(call: Call<MealsCategoriesResponse>, t: Throwable) {
                errorCallback(t)
            }
        })
    }
}
