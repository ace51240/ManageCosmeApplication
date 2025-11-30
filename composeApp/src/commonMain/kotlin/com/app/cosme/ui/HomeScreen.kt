package com.app.cosme.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import managecosmeapplication.composeapp.generated.resources.Res
import managecosmeapplication.composeapp.generated.resources.add_24px
import managecosmeapplication.composeapp.generated.resources.edit_24px
import managecosmeapplication.composeapp.generated.resources.favorite_24px
import managecosmeapplication.composeapp.generated.resources.photo_24px
import managecosmeapplication.composeapp.generated.resources.psychology_24px
import managecosmeapplication.composeapp.generated.resources.search_24px
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

// 仮のデータ構造
data class CosmeticItem(
    val id: Int,
    val name: String,
    val brand: String,
    val isFavorite: Boolean
)

// ダミーデータ
private val dummyCosmetics = List(20) {
    CosmeticItem(
        id = it,
        name = "超細芯アイブロウ $it",
        brand = "CEZANNE",
        isFavorite = it % 3 == 0
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("コスメ管理") },
                actions = {
                    IconButton(onClick = { /* TODO: 検索処理 */ }) {
                        Icon(painterResource(Res.drawable.search_24px), contentDescription = "Search")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showBottomSheet = true }) {
                Icon(
                    painter = painterResource(Res.drawable.add_24px),
                    contentDescription = "Add Cosmetic",
                )
            }
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            // タブの実装
            var selectedTabIndex by remember { mutableStateOf(0) }
            val tabs = listOf("すべて", "ジャンル別", "お気に入り")
            TabRow(selectedTabIndex = selectedTabIndex) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = { Text(title, maxLines = 1, overflow = TextOverflow.Ellipsis) }
                    )
                }
            }

            // TODO: タブに応じて表示するコンテンツを切り替える
            CosmeticList(cosmetics = dummyCosmetics)
        }

        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false },
                sheetState = sheetState
            ) {
                Column(modifier = Modifier.padding(bottom = 32.dp)) {
                    ListItem(
                        headlineContent = { Text("手動登録") },
                        leadingContent = {
                            Icon(
                                painter = painterResource(Res.drawable.edit_24px),
                                contentDescription = "Manual Registration"
                            )
                        },
                        modifier = Modifier.clickable { /* TODO: 手動登録画面へ */ }
                    )
                    ListItem(
                        headlineContent = { Text("写真登録") },
                        leadingContent = {
                            Icon(
                                painter = painterResource(Res.drawable.photo_24px),
                                contentDescription = "Photo Registration"
                            )
                        },
                        modifier = Modifier.clickable { /* TODO: 写真登録画面へ */ }
                    )
                    ListItem(
                        headlineContent = { Text("AI登録（将来拡張）") },
                        leadingContent = {
                            Icon(
                                painter = painterResource(Res.drawable.psychology_24px),
                                contentDescription = "AI Registration"
                            )
                        },
                        modifier = Modifier.clickable { /* TODO: AI登録画面へ */ }
                    )
                }
            }
        }
    }
}

@Composable
fun CosmeticList(cosmetics: List<CosmeticItem>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(cosmetics) { cosmetic ->
            CosmeticCard(cosmetic)
        }
    }
}

@Composable
fun CosmeticCard(cosmetic: CosmeticItem) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // TODO: 画像を表示する領域
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = cosmetic.name, style = MaterialTheme.typography.titleMedium)
                Text(text = cosmetic.brand, style = MaterialTheme.typography.bodySmall)
            }
            if (cosmetic.isFavorite) {
                Icon(
                    painter = painterResource(Res.drawable.favorite_24px),
                    contentDescription = "Favorite",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}

@Preview
@Composable
fun CosmeticCardPreview() {
    CosmeticCard(
        CosmeticItem(
            id = 1,
            name = "超細芯アイブロウ",
            brand = "CEZANNE",
            isFavorite = true
        )
    )
}