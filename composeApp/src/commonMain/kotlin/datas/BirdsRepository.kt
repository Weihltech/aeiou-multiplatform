package datas

import datas.entitys.BirdInfo
import datas.local.BirdsLocal
import datas.remote.BirdsRemote
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.wells.aeiou.database.BirdsQueries

/**
 * @desc 鸟的数据存储库
 *
 * @author weihl
 * @date 2023/12/4
 */
class BirdsRepository : IBirdsRepository {
    override suspend fun fetchAllBirds(): List<BirdInfo> {

        val local = BirdsLocal().fetchAllBirds()
        if (local.isNotEmpty()) {
            return local
        }

        val remote = BirdsRemote().fetchAllBirds()
        if (remote.isNotEmpty()) {
            BirdsQueries(aeiouSqlDriver).insert(
                id = null,
                infos = Json.encodeToString(remote)
            )
            return remote
        }

        return emptyList()
    }

}

