import android.os.Build
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.wells.aeiou.AeiouApp
import org.wells.aeiou.database.AeiouDatabase

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual fun createDriver(): SqlDriver {
    return AndroidSqliteDriver(AeiouDatabase.Schema, AeiouApp.context, "aeiou.db")
}