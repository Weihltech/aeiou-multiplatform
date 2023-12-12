import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import io.ktor.client.plugins.cache.storage.CacheStorage
import io.ktor.client.plugins.cache.storage.CachedResponseData
import io.ktor.http.Url
import org.wells.aeiou.database.AeiouDatabase
import platform.UIKit.UIDevice

class IOSPlatform : Platform {
    override val name: String =
        UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

actual fun createSqlDriver(): SqlDriver {
    return NativeSqliteDriver(AeiouDatabase.Schema, "aeiou.db")
}

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