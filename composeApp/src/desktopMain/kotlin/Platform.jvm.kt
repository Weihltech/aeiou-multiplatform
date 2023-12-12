import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import io.ktor.client.plugins.cache.storage.CacheStorage
import io.ktor.client.plugins.cache.storage.FileStorage
import org.wells.aeiou.database.AeiouDatabase
import java.io.File
import kotlin.io.path.Path
import kotlin.io.path.createDirectory

class JVMPlatform : Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
}

actual fun getPlatform(): Platform = JVMPlatform()

actual fun createSqlDriver(): SqlDriver {
    // "jdbc:sqlite:test.db" ，持久保存
    // JdbcSqliteDriver.IN_MEMORY ,在缓存中有效
    val driver: SqlDriver = JdbcSqliteDriver("jdbc:sqlite:${dbPath}")
    //DriverManager.getConnection("jdbc:sqlite:aeiou.db")
    runCatching {
        AeiouDatabase.Schema.create(driver)
    }
    return driver
}

actual fun createCacheStorage(): CacheStorage {
    return FileStorage(cacheDir)
}