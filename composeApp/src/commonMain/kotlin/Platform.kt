import app.cash.sqldelight.db.SqlDriver
import org.wells.aeiou.Database

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform


expect class DriverFactory {
    fun createDriver(): SqlDriver
}

fun createDatabase(driverFactory: DriverFactory): Database {
    val driver = driverFactory.createDriver()
    return Database(driver)
}