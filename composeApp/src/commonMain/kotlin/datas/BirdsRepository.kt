package datas

import datas.entitys.BirdInfo
import datas.local.BirdsLocal
import datas.remote.BirdsRemote
import datas.remote.api.ApiDownload
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
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

        val downTest = "https://cdn.pixabay.com/photo/2023/11/25/15/45/mountains-8411954_1280.jpg"
        ApiDownload().fetch(downTest)

        val local = BirdsLocal().fetchAllBirds()
        if (local.isNotEmpty()) {

            println("fetchAllBirds . Local ")
            return local
        }

        val remote = BirdsRemote().fetchAllBirds()
        if (remote.isNotEmpty()) {
            withContext(Dispatchers.IO) {
                aeiouDatabase.birdsQueries.insert(
                    id = null,
                    infos = Json.encodeToString(remote)
                )
            }
            println("fetchAllBirds . Remote ")
            return remote
        }

        return emptyList()
    }

}

