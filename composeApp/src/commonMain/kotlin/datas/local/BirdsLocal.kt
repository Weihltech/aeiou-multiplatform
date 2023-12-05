package datas.local

import datas.IBirdsRepository
import datas.aeiouDatabase
import datas.entitys.BirdInfo
import kotlinx.serialization.json.Json

/**
 * @desc
 *
 * @author weihl
 * @date 2023/12/4
 */
class BirdsLocal : IBirdsRepository {
    override suspend fun fetchAllBirds(): List<BirdInfo> {

        aeiouDatabase.birdsQueries.queryAll().executeAsList().lastOrNull()?.let {
            runCatching {
                return Json.decodeFromString(it.infos)
            }
        }

        return emptyList()
    }
}