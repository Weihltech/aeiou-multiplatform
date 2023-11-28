package pages.home

import Greeting
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import datas.remote.BirdImage
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import theme.AppTheme

/**
 * @desc
 *
 * @author weihl
 * @date 2023/11/24
 */

@Composable
fun HomeContent() {
    AppTheme {

        val viewModel = getViewModel("", viewModelFactory { HomeViewModel() })
        val uiState by viewModel.uiState.collectAsState()

        Column(modifier = Modifier.fillMaxSize()) {
            val tabSelectIndex = remember { mutableStateOf(0) }
            Box(modifier = Modifier.weight(1.0f, true)) {
                Contents(tabSelectIndex, uiState)
            }
            HomeBottomBar(tabSelectIndex)
        }
    }
}

@Composable
fun Contents(tabSelectIndex: MutableState<Int>, homeUiState: HomeUiState) {
    Box(modifier = Modifier.fillMaxSize()) {


        LazyVerticalGrid(
            GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            items(homeUiState.birdImages) {
                BirdImageCell(it)
            }
        }


        //var info = remember { mutableStateOf("${tabSelectIndex.value}") }
        //Text(text = info.value, modifier = Modifier.align(Alignment.TopStart))
        //
        //
        //var greetingText by remember { mutableStateOf("Hello World!") }
        //var showImage by remember { mutableStateOf(false) }
        //
        //Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        //    Button(onClick = {
        //        greetingText = "Compose: ${Greeting().greet()}"
        //        showImage = !showImage
        //    }) {
        //        Text(greetingText)
        //    }
        //    AnimatedVisibility(showImage) {
        //        KamelImage(
        //            asyncPainterResource("https://img1.baidu.com/it/u=2292210371,42019104&fm=253&fmt=auto&app=138&f=JPEG?w=380&h=242"),
        //            //"http://img.yun.cnhubei.com/a/10001/201903/be92fabfb22d9279f3061a9db5a1b474.jpeg"),
        //            null
        //        )
        //    }
        //}
    }
}

@Composable
fun BirdImageCell(it: BirdImage) {
    KamelImage(
        asyncPainterResource("https://p0.pipi.cn/basicdata/25bfd6d7537c69e7aa71f711b81d429d59d64.jpg?imageMogr2/thumbnail/2500x2500%3E"),
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize(),
        contentDescription = ""
    )
}
