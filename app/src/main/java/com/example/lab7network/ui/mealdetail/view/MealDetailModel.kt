package com.example.lab7network.ui.mealdetail.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab7network.navigation.response.MealDetailResponse
import com.example.lab7network.ui.mealdetail.repository.MealDetailRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException

class MealDetailViewModel(
    private val repository: MealDetailRepository = MealDetailRepository()
) : ViewModel() {

    private val _mealDetail = MutableStateFlow<MealDetailResponse?>(null)
    val mealDetail: StateFlow<MealDetailResponse?> get() = _mealDetail

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _isError = MutableStateFlow(false)
    val isError: StateFlow<Boolean> get() = _isError

    fun getMealDetail(mealId: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val response = repository.getMealDetail(mealId)
                _mealDetail.value = response
            } catch (e: Exception) {
                _isError.value = true
            } finally {
                _isLoading.value = false
            }
        }
    }

}
