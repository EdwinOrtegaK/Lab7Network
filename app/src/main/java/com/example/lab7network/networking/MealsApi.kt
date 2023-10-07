package com.example.lab7network.networking

import com.example.lab7network.navigation.response.CategoriesResponse
import com.example.lab7network.navigation.response.MealDetailResponse
import com.example.lab7network.navigation.response.MealsCategoriesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealsApi {
    @GET("categories.php")
    fun getMeals(): Call<MealsCategoriesResponse>

    @GET("filter.php")
    fun getMealsCategory(@Query("c") categoryId: String): Call<CategoriesResponse>

    @GET("lookup.php")
    suspend fun getMealDetail(@Query("i") mealId: String): MealDetailResponse?
}

//https://www.themealdb.com/api/json/v1/1/categories.php