package datas

import createDriver
import datas.entitys.BirdInfo

/**
 * @desc
 *
 * @author weihl
 * @date 2023/12/4
 */

val aeiouSqlDriver by lazy { createDriver() }

interface IBirdsRepository {
    suspend fun fetchAllBirds(): List<BirdInfo>
}