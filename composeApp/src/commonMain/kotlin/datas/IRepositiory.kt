package datas

import createSqlDriver
import datas.entitys.BirdInfo
import org.wells.aeiou.database.AeiouDatabase

/**
 * @desc
 *
 * @author weihl
 * @date 2023/12/4
 */

val aeiouSqlDriver by lazy { createSqlDriver() }
val aeiouDatabase by lazy { AeiouDatabase(aeiouSqlDriver) }

interface IBirdsRepository {
    suspend fun fetchAllBirds(): List<BirdInfo>
}