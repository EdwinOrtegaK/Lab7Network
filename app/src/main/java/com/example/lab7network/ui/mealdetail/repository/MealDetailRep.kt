package com.example.lab7network.ui.mealdetail.repository

import com.example.lab7network.navigation.response.MealDetailResponse
import com.example.lab7network.networking.MealsWebService
import java.io.IOException
import com.google.gson.JsonParseException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MealDetailRepository(private val webService: MealsWebService = MealsWebService()) {

    suspend fun getMealDetail(mealId: String): MealDetailResponse? {
        println("Attempting to fetch meal detail with mealId: $mealId")
        println("URL: https://www.themealdb.com/api/json/v1/1/lookup.php?i=$mealId")

        return withContext(Dispatchers.IO) {
            try {
                val response = webService.getMealDetail(mealId)
                println("Response: $response")

                if (response != null && response.meals?.isNotEmpty() == true) {
                    println("Successful response with data")
                    response
                } else {
                    println("Empty or invalid response")
                    null
                }
            } catch (e: IOException) {
                println("Network error: ${e.localizedMessage}")
                null
            } catch (e: JsonParseException) {
                println("JSON parsing error: ${e.localizedMessage}")
                null
            } catch (e: Exception) {
                println("Unknown error: ${e.localizedMessage}")
                null
            }
        }
    }
}
