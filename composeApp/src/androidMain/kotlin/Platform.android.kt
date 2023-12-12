import android.os.Build
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import io.ktor.client.plugins.cache.storage.CacheStorage
import io.ktor.client.plugins.cache.storage.CachedResponseData
import io.ktor.http.Url
import org.wells.aeiou.AeiouApp
import org.wells.aeiou.database.AeiouDatabase

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual fun createSqlDriver(): SqlDriver {
    return AndroidSqliteDriver(AeiouDatabase.Schema, AeiouApp.context, "aeiou.db")
}

// 下载模块
actual fun createCacheStorage(): CacheStorage {
    return object : CacheStorage {
        override suspend fun find(url: Url, varyKeys: Map<String, String>): CachedResponseData? {
            TODO("Not yet implemented")
        }

        override suspend fun findAll(url: Url): Set<CachedResponseData> {
            TODO("Not yet implemented")
        }

        override suspend fun store(url: Url, data: CachedResponseData) {
            TODO("Not yet implemented")
        }
    }
}
