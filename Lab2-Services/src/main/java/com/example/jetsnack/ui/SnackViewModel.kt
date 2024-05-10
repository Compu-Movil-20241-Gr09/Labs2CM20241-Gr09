package com.example.jetsnack.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.jetsnack.SnackApplication
import com.example.jetsnack.data.SnackRepository
import com.example.jetsnack.model.Snack
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface SnackUiState {
    data class Success(val snacks: List<Snack>): SnackUiState
    object Error : SnackUiState
    object Loading : SnackUiState
}

class SnackViewModel(private val snackRepository: SnackRepository): ViewModel() {
    var snackUiState: SnackUiState by mutableStateOf(SnackUiState.Loading)
        private set

    init {
        getSnacks()
    }

    fun getSnacks(){
        viewModelScope.launch {
            snackUiState = SnackUiState.Loading
            snackUiState = try {
                SnackUiState.Success(snackRepository.getSnacks())
            } catch (e: IOException) {
                SnackUiState.Error
            } catch (e: HttpException) {
                SnackUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as SnackApplication)
                val snackRepository = application.container.snackRepository
                SnackViewModel(snackRepository = snackRepository)
            }
        }
    }
}