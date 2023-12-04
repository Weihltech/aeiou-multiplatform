package pages.home

import datas.BirdsRepository
import datas.remote.api.ApiBirds
import datas.entitys.BirdInfo
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * @desc
 *
 * @author weihl
 * @date 2023/11/28
 */

data class HomeUiState(
    val birdInfos: List<BirdInfo> = emptyList()
)

class HomeViewModel : ViewModel() {


    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        updateBirdImages()
    }

    fun updateBirdImages() {
        viewModelScope.launch {
            val datas = fetchBirdImages()
            _uiState.update {
                it.copy(birdInfos = datas)
            }
        }
    }

    private suspend fun fetchBirdImages(): List<BirdInfo> {
        return BirdsRepository().fetchAllBirds()
    }


}