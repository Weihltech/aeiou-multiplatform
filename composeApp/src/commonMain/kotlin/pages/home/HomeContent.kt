package pages.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
        Text(text = "${tabSelectIndex.value}", modifier = Modifier.align(Alignment.TopStart))
    }
}
