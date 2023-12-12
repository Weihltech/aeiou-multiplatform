import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import io.kamel.core.utils.File
import io.ktor.client.plugins.cache.storage.CacheStorage
import io.ktor.client.plugins.cache.storage.CachedResponseData
import io.ktor.http.Url
import okio.FileSystem
import org.wells.aeiou.database.AeiouDatabase
import platform.Foundation.NSCachesDirectory
import platform.Foundation.NSSearchPathForDirectoriesInDomains
import platform.Foundation.NSUserDomainMask
import platform.UIKit.UIDevice

class IOSPlatform : Platform {
    override val name: String =
        UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
    override val datas: Datas
        get() = object : Datas {
            override val sqlDriver: SqlDriver
                get() = createSqlDriver()
            override val cacheStorage: CacheStorage
                get() = createCacheStorage()
            override val downloadStorage: DownloadStorage
                get() = createDownloadStorage()

        }

    override fun kamelFile(path: String): File {
        return File(path)
    }

    private fun createDownloadStorage(): DownloadStorage {
        return object : DownloadStorage {
            override val dir: String
                get() = NSSearchPathForDirectoriesInDomains(
                    NSCachesDirectory,
                    NSUserDomainMask,
                    true,
                ).first() as String

            override val fileSystem: FileSystem
                get() = FileSystem.SYSTEM
        }
    }

    private fun createSqlDriver(): SqlDriver {
        return NativeSqliteDriver(AeiouDatabase.Schema, "aeiou.db")
    }

    private fun createCacheStorage(): CacheStorage {
        return object : CacheStorage {
            override suspend fun find(
                url: Url,
                varyKeys: Map<String, String>
            ): CachedResponseData? {
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
}

actual fun getPlatform(): Platform = IOSPlatform()
