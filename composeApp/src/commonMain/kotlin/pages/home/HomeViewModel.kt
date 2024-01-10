package pages.home

import androidx.compose.runtime.MutableState
import datas.BirdsRepository
import datas.entitys.BirdInfo
import datas.streams.downloads.Downloader
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.kamel.core.utils.File
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import platform

/**
 * @desc
 *
 * @author weihl
 * @date 2023/11/28
 */

data class HomeUiState(
    val birdInfos: List<BirdInfo> = emptyList(),
    val downloadJPG: File = platform.utils.toKamelFile(""),
    val navBarItem: List<NavBarItem> = listOf(
        NavBarItem("News", "icbar/ic_news.xml"),
        NavBarItem("Collects", "icbar/ic_collects.xml"),
        NavBarItem("Chats", "icbar/ic_chats.xml"),
        NavBarItem("Me", "icbar/ic_me.xml")
    ),
)

data class NavBarItem(
    val name: String,
    val icon: String
)

class HomeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        updateBirdImages()
        downloadPNG()
    }

    private fun downloadPNG() {
        viewModelScope.launch {
            val bingPng =
                "https://cdn.pixabay.com/photo/2023/11/25/15/45/mountains-8411954_1280.jpg"
            Downloader().fetch(bingPng)
        }
    }

    private fun updateBirdImages() {
        viewModelScope.launch {
            val datas = fetchBirdImages()
            _uiState.update {
                it.copy(birdInfos = datas)
            }
            _uiState.update {
                it.copy(downloadJPG = platform.utils.toKamelFile("${platform.datas.downloadStorage.dir}/mountains-8411954_1280.jpg"))
            }
        }
    }

    private suspend fun fetchBirdImages(): List<BirdInfo> {
        return BirdsRepository().fetchAllBirds()
    }


}