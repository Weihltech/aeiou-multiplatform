import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import io.ktor.client.plugins.cache.storage.CacheStorage
import io.ktor.client.plugins.cache.storage.FileStorage
import okio.FileSystem
import org.wells.aeiou.database.AeiouDatabase
import org.wells.aeiou.utils.Unzip

class JVMPlatform : Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
    override val datas: Datas
        get() = object : Datas {
            override val sqlDriver: SqlDriver
                get() = createSqlDriver()
            override val cacheStorage: CacheStorage
                get() = createCacheStorage()
            override val downloadStorage: DownloadStorage
                get() = createDownloadStorage()

        }
    override val utils: Utils
        get() = object : Utils {
            override fun unzip(zipFilePath: String, destDirectory: String) {
                Unzip().start(zipFilePath, destDirectory)
            }

            override fun toKamelFile(path: String): io.kamel.core.utils.File {
                return io.kamel.core.utils.File(path)
            }

        }

    private fun createSqlDriver(): SqlDriver {
        // "jdbc:sqlite:test.db" ，持久保存
        // JdbcSqliteDriver.IN_MEMORY ,在缓存中有效
        val driver: SqlDriver = JdbcSqliteDriver("jdbc:sqlite:${dbPath}")
        //DriverManager.getConnection("jdbc:sqlite:aeiou.db")
        runCatching {
            AeiouDatabase.Schema.create(driver)
        }
        return driver
    }

    private fun createCacheStorage(): CacheStorage {
        return FileStorage(cacheDir)
    }

    private fun createDownloadStorage(): DownloadStorage {
        return object : DownloadStorage {
            override val dir: String
                get() = downloadDir
            override val fileSystem: FileSystem
                get() = FileSystem.SYSTEM
        }
    }
}

actual fun getPlatform(): Platform = JVMPlatform()
