package widgets

import androidx.compose.foundation.Image
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
fun MainBottomBar() {
    var selectedItem by remember { mutableStateOf(0) }
    BottomNavigation {
        items.onEachIndexed { index, item ->
            BottomNavigationItem(
                icon = {
                    Icon(painterResource(item.icon), null)
                },
                label = { Text(text = item.name) },
                selected = selectedItem == index,
                onClick = { selectedItem = index }
            )
        }
    }
}

