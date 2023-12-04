package datas

import datas.entitys.BirdInfo

/**
 * @desc 鸟的数据存储库
 *
 * @author weihl
 * @date 2023/12/4
 */

interface IBirdsRepository{
    fun fetchBirds():List<BirdInfo>
}

class BirdsRepository:IBirdsRepository {
    override fun fetchBirds(): List<BirdInfo> {
        TODO("Not yet implemented")
    }

}

