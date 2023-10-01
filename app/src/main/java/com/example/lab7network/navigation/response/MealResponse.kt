package com.example.lab7network.navigation.response

import com.google.gson.annotations.SerializedName

data class MealsCategoriesResponse(val categories: List<MealResponse>)

data class MealResponse(
    @SerializedName("idCategory") val idCategory: String,
    @SerializedName("stringCategory") val name: String,
    @SerializedName("stringCategoryDescription") val description: String,
    @SerializedName("stringCategoryThumb") val imageUrl: String
)