package pages.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import datas.entitys.BirdInfo
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import theme.AppTheme

/**
 * @desc
 *
 * @author weihl
 * @date 2023/11/24
 */

@OptIn(ExperimentalResourceApi::class)
@Composable
fun HomeContent() {
    AppTheme {

        val viewModel = getViewModel("", viewModelFactory { HomeViewModel() })
        val uiState by viewModel.uiState.collectAsState()

        Column(modifier = Modifier.fillMaxSize()) {

            val tabIndex = remember { mutableStateOf(0) }

            Box(
                modifier = Modifier.weight(1.0f, true).fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                when (tabIndex.value) {
                    3 -> me(uiState)
                    2 -> chats(uiState)
                    1 -> starts(uiState)
                    else -> news(uiState)
                }
            }

            BottomNavigation(modifier = Modifier.fillMaxWidth().height(56.dp)) {
                uiState.navBarItem.onEachIndexed { index, item ->
                    BottomNavigationItem(
                        icon = {
                            Icon(painterResource(item.icon), null)
                        },
                        label = { Text(text = item.name) },
                        selected = index == tabIndex.value,
                        onClick = { tabIndex.value = index }
                    )
                }
            }

        }
    }
}


@Composable
@Stable
fun Contents(modifier: Modifier, uiState: HomeUiState) {

}

@Composable
fun me(homeUiState: HomeUiState) {
    Text("me")
}

@Composable
fun chats(homeUiState: HomeUiState) {
    Text("chats")
}

@Composable
fun starts(homeUiState: HomeUiState) {
    AnimatedVisibility(true) {
        KamelImage(
            asyncPainterResource(homeUiState.downloadJPG),
            null
        )
    }
}

@Composable
fun news(homeUiState: HomeUiState) {
    LazyVerticalGrid(
        GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize().padding(5.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(homeUiState.birdInfos) {
            BirdImageCell(it)
        }
    }
}

@Composable
fun BirdImageCell(it: BirdInfo) {
    KamelImage(
        asyncPainterResource("https://raw.githubusercontent.com/SebastianAigner/demo-image-api/main/${it.path}"),
        modifier = Modifier.background(Color.LightGray).fillMaxWidth().aspectRatio(1.0f),
        contentScale = ContentScale.Crop,
        contentDescription = ""
    )

    //    AnimatedVisibility(showImage) {
    //        KamelImage(
    //            asyncPainterResource("https://img1.baidu.com/it/u=2292210371,42019104&fm=253&fmt=auto&app=138&f=JPEG?w=380&h=242"),
    //            //"http://img.yun.cnhubei.com/a/10001/201903/be92fabfb22d9279f3061a9db5a1b474.jpeg"),
    //            null
    //        )
    //    }

}
