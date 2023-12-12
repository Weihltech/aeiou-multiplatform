package datas

import datas.entitys.BirdInfo
import org.wells.aeiou.database.AeiouDatabase
import platform

/**
 * @desc
 *
 * @author weihl
 * @date 2023/12/4
 */

val aeiouSqlDriver by lazy { platform.datas.sqlDriver }
val aeiouDatabase by lazy { AeiouDatabase(aeiouSqlDriver) }

interface IBirdsRepository {
    suspend fun fetchAllBirds(): List<BirdInfo>
}