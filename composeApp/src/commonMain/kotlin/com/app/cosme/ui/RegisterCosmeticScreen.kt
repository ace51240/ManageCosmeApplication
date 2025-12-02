package com.app.cosme.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.app.cosme.manage.RegisterCosmeticUiState
import com.app.cosme.manage.RegisterCosmeticViewModel
import managecosmeapplication.composeapp.generated.resources.Res
import managecosmeapplication.composeapp.generated.resources.arrow_back_24px
import managecosmeapplication.composeapp.generated.resources.favorite_24px
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun RegisterCosmeticScreen(
    navController: NavController? = null,
    viewModel: RegisterCosmeticViewModel = koinViewModel<RegisterCosmeticViewModel>()
) {
    val uiState by viewModel.uiState.collectAsState()

    RegisterCosmeticContent(
        uiState = uiState,
        onBrandNameChanged = viewModel::onBrandNameChanged,
        onProductNameChanged = viewModel::onProductNameChanged,
        onCategoryNameChanged = viewModel::onCategoryNameChanged,
        onMemoChanged = viewModel::onMemoChanged,
        onRegisterClick = viewModel::registerCosmetic,
        onNavigateBack = {
            navController?.popBackStack()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterCosmeticContent(
    uiState: RegisterCosmeticUiState,
    onBrandNameChanged: (String) -> Unit,
    onProductNameChanged: (String) -> Unit,
    onCategoryNameChanged: (String) -> Unit,
    onMemoChanged: (String) -> Unit,
    onRegisterClick: () -> Unit,
    onNavigateBack: () -> Unit
) {
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.isRegistered) {
        if (uiState.isRegistered) {
            snackBarHostState.showSnackbar("コスメを登録しました！")
            onNavigateBack()
        }
    }

    LaunchedEffect(uiState.errorMessage) {
        uiState.errorMessage?.let {
            snackBarHostState.showSnackbar(it)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("コスメ登録") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            painter = painterResource(Res.drawable.arrow_back_24px),
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackBarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = uiState.brandName,
                onValueChange = onBrandNameChanged,
                label = { Text("ブランド名") }
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = uiState.productName,
                onValueChange = onProductNameChanged,
                label = { Text("商品名（必須）") },
                isError = uiState.productName.isBlank()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = uiState.categoryName,
                onValueChange = onCategoryNameChanged,
                label = { Text("カテゴリ") }
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = uiState.memo,
                onValueChange = onMemoChanged,
                label = { Text("メモ") }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onRegisterClick,
                enabled = !uiState.isLoading
            ) {
                if (uiState.isLoading) {
                    CircularProgressIndicator()
                } else {
                    Text("登録する")
                }
            }
        }
    }
}

@Preview
@Composable
fun RegisterCosmeticScreenPreview() {
    val uiState = RegisterCosmeticUiState(
        brandName = "CEZANNE",
        productName = "超細芯アイブロウ",
        categoryName = "アイブロウ",
        memo = "03 ナチュラルブラウン"
    )
    RegisterCosmeticContent(
        uiState = uiState,
        onBrandNameChanged = {},
        onProductNameChanged = {},
        onCategoryNameChanged = {},
        onMemoChanged = {},
        onRegisterClick = {},
        onNavigateBack = {}
    )
}