package com.app.cosme.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import managecosmeapplication.composeapp.generated.resources.Res
import managecosmeapplication.composeapp.generated.resources.arrow_back_24px
import managecosmeapplication.composeapp.generated.resources.delete_24px
import managecosmeapplication.composeapp.generated.resources.edit_24px
import managecosmeapplication.composeapp.generated.resources.favorite_24px
import managecosmeapplication.composeapp.generated.resources.favorite_full_24px
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CosmeticDetailScreen(
    navController: NavController? = null,
    cosmeticId: Int
) {
    // 本来はViewModelから取得しますが、一旦ダミーデータ
    var isFavorite by remember { mutableStateOf(cosmeticId % 3 == 0) }
    val uriHandler = LocalUriHandler.current

    // ダミーの検索用URL生成
    val brand = "CEZANNE"
    val name = "超細芯アイブロウ"
    val rakutenUrl = "https://search.rakuten.co.jp/search/mall/$brand+$name/"
    val amazonUrl = "https://www.amazon.co.jp/s?k=$brand+$name"

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("コスメ詳細") },
                navigationIcon = {
                    IconButton(onClick = { navController?.popBackStack() }) {
                        Icon(painterResource(Res.drawable.arrow_back_24px), contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { /* TODO: 編集 */ }) {
                        Icon(painterResource(Res.drawable.edit_24px), contentDescription = "Edit")
                    }
                    IconButton(onClick = { /* TODO: 削除 */ }) {
                        Icon(painterResource(Res.drawable.delete_24px), contentDescription = "Delete")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            // 画像エリア
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                Text("ここに画像が表示されます", color = MaterialTheme.colorScheme.onSurfaceVariant)
            }

            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = brand, style = MaterialTheme.typography.labelLarge)
                        Text(text = name, style = MaterialTheme.typography.headlineMedium)
                    }
                    IconButton(onClick = { isFavorite = !isFavorite }) {
                        Icon(
                            painter = if (isFavorite) painterResource(Res.drawable.favorite_full_24px) else painterResource(Res.drawable.favorite_24px),
                            contentDescription = "Favorite",
                            tint = if (isFavorite) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        DetailItem(label = "カテゴリ", value = "アイブロウ")
                        Spacer(modifier = Modifier.height(8.dp))
                        DetailItem(label = "メモ", value = "03 ナチュラルブラウン。とても描きやすくてリピート確定アイテム。")
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(text = "ショッピングサイトで探す", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Button(
                        onClick = { uriHandler.openUri(rakutenUrl) },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                    ) {
                        Text("楽天で見る")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = { uriHandler.openUri(amazonUrl) },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary)
                    ) {
                        Text("Amazonで見る")
                    }
                }
            }
        }
    }
}

@Composable
fun DetailItem(label: String, value: String) {
    Column {
        Text(text = label, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
        Text(text = value, style = MaterialTheme.typography.bodyLarge)
    }
}

@Preview
@Composable
fun CosmeticDetailScreenPreview() {
    CosmeticDetailScreen(cosmeticId = 1)
}
