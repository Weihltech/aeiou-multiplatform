package datas.remote

import datas.IBirdsRepository
import datas.entitys.BirdInfo
import datas.remote.api.ApiBirds

/**
 * @desc
 *
 * @author weihl
 * @date 2023/12/4
 */
class BirdsRemote : IBirdsRepository {
    override suspend fun fetchAllBirds(): List<BirdInfo> {
        runCatching {
            return ApiBirds().fetchBirdImages()
        }
        return emptyList()
    }
}