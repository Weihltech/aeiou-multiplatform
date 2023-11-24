package pages.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

/**
 * @desc 首页底部栏
 *
 * @author weihl
 * @date 2023/11/24
 */

private val items = listOf(
    MainBottomBarItem("News", "icbar/ic_news.xml"),
    MainBottomBarItem("Starts", "icbar/ic_starts.xml"),
    MainBottomBarItem("Me", "icbar/ic_me.xml")
)

data class MainBottomBarItem(
    val name: String,
    val icon: String
)

@OptIn(ExperimentalResourceApi::class)
@Composable
fun HomeBottomBar(tabIndex: MutableState<Int>) {
    BottomNavigation(modifier = Modifier.fillMaxWidth().height(56.dp)) {
        items.onEachIndexed { index, item ->
            BottomNavigationItem(
                icon = {
                    Icon(painterResource(item.icon), null)
                },
                label = { Text(text = item.name) },
                selected = index == tabIndex.value,
                onClick = {tabIndex.value = index}
            )
        }
    }
}

