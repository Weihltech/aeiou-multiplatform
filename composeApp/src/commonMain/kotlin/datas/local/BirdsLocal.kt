package datas.local

import datas.IBirdsRepository
import datas.aeiouSqlDriver
import datas.entitys.BirdInfo
import kotlinx.serialization.json.Json
import org.wells.aeiou.database.BirdsQueries

/**
 * @desc
 *
 * @author weihl
 * @date 2023/12/4
 */
class BirdsLocal : IBirdsRepository {
    override suspend fun fetchAllBirds(): List<BirdInfo> {
        return BirdsQueries(aeiouSqlDriver).queryAll().executeAsList().map {
            Json.decodeFromString(it.infos)
        }
    }
}