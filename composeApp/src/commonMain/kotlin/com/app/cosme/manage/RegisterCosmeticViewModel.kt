package com.app.cosme.manage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.cosme.shared.usecase.RegisterCosmeticUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class RegisterCosmeticUiState(
    val brandName: String = "",
    val productName: String = "",
    val categoryName: String = "",
    val memo: String = "",
    val imagePath: String? = null,
    val isRegistered: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

class RegisterCosmeticViewModel(
    private val registerCosmeticUseCase: RegisterCosmeticUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterCosmeticUiState())
    val uiState: StateFlow<RegisterCosmeticUiState> = _uiState.asStateFlow()

    fun onBrandNameChanged(name: String) {
        _uiState.value = _uiState.value.copy(brandName = name)
    }

    fun onProductNameChanged(name: String) {
        _uiState.value = _uiState.value.copy(productName = name)
    }

    fun onCategoryNameChanged(name: String) {
        _uiState.value = _uiState.value.copy(categoryName = name)
    }

    fun onMemoChanged(memo: String) {
        _uiState.value = _uiState.value.copy(memo = memo)
    }
    
    fun onImagePathChanged(path: String?) {
        _uiState.value = _uiState.value.copy(imagePath = path)
    }

    fun registerCosmetic() {
        if (_uiState.value.productName.isBlank()) {
            _uiState.value = _uiState.value.copy(errorMessage = "商品名は必須です")
            return
        }

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                registerCosmeticUseCase(
                    brandName = _uiState.value.brandName.takeIf { it.isNotBlank() },
                    productName = _uiState.value.productName,
                    categoryName = _uiState.value.categoryName.takeIf { it.isNotBlank() },
                    memo = _uiState.value.memo.takeIf { it.isNotBlank() },
                    imagePath = _uiState.value.imagePath
                )
                _uiState.value = _uiState.value.copy(isRegistered = true, isLoading = false)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    errorMessage = "登録に失敗しました: ${e.message}",
                    isLoading = false
                )
            }
        }
    }
}