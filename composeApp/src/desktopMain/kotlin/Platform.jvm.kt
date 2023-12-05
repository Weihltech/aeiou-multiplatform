import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.ConnectionManager
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import org.wells.aeiou.database.AeiouDatabase
import java.sql.DriverManager

class JVMPlatform : Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
}

actual fun getPlatform(): Platform = JVMPlatform()

actual fun createDriver(): SqlDriver {
    // "jdbc:sqlite:test.db" ，持久保存
    // JdbcSqliteDriver.IN_MEMORY ,在缓存中有效
    val driver: SqlDriver = JdbcSqliteDriver("jdbc:sqlite:aeiou.db")
    //DriverManager.getConnection("jdbc:sqlite:aeiou.db")
    runCatching {
        AeiouDatabase.Schema.create(driver)
    }
    return driver
}