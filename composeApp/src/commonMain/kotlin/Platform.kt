import app.cash.sqldelight.db.SqlDriver
import io.kamel.core.utils.File
import io.ktor.client.plugins.cache.storage.CacheStorage
import okio.FileSystem

interface Platform {
    val name: String
    val datas: Datas
    fun kamelFile(path: String): File
}

// App 数据层
interface Datas {
    // sqlite ,数据库存储
    val sqlDriver: SqlDriver

    // httpCache ,网络请求缓存插件
    val cacheStorage: CacheStorage

    val downloadStorage: DownloadStorage

}

interface DownloadStorage {
    val dir: String
    val fileSystem: FileSystem
}

// common
expect fun getPlatform(): Platform

val platform by lazy { getPlatform() }

