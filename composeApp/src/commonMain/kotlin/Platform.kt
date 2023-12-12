import app.cash.sqldelight.db.SqlDriver
import io.ktor.client.plugins.cache.storage.CacheStorage

interface Platform {
    val name: String
    val datas:Datas
}

// App 数据层
interface Datas{
    val sqlDriver:SqlDriver
    val cacheStorage:CacheStorage
}

// common
expect fun getPlatform(): Platform

val platform by lazy { getPlatform() }

