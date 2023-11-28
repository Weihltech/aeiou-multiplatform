package pages.home

import Greeting
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import datas.remote.ApiBirds
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

@Composable
fun HomeContent() {
    AppTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            val tabSelectIndex = remember { mutableStateOf(0) }
            Box(modifier = Modifier.weight(1.0f, true)) {
                Contents(tabSelectIndex)
            }
            HomeBottomBar(tabSelectIndex)
        }
    }
}

@Composable
fun Contents(tabSelectIndex: MutableState<Int>) {
    Box(modifier = Modifier.fillMaxSize()) {

        var info = remember { mutableStateOf("${tabSelectIndex.value}") }

        LaunchedEffect(Unit){
            info.value = ApiBirds().fetchBirdImages().toString()
        }

        Text(text = info.value, modifier = Modifier.align(Alignment.TopStart))


        var greetingText by remember { mutableStateOf("Hello World!") }
        var showImage by remember { mutableStateOf(false) }

        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = {
                greetingText = "Compose: ${Greeting().greet()}"
                showImage = !showImage
            }) {
                Text(greetingText)
            }
            AnimatedVisibility(showImage) {
                KamelImage(
                    asyncPainterResource("https://img1.baidu.com/it/u=2292210371,42019104&fm=253&fmt=auto&app=138&f=JPEG?w=380&h=242"),
                    //"http://img.yun.cnhubei.com/a/10001/201903/be92fabfb22d9279f3061a9db5a1b474.jpeg"),
                    null
                )
            }
        }
    }
}
