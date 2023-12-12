import app.cash.sqldelight.db.SqlDriver
import io.ktor.client.plugins.cache.storage.CacheStorage

interface Platform {
    val name: String
}

// common
expect fun getPlatform(): Platform

// db sql
expect fun createSqlDriver():SqlDriver

expect fun createCacheStorage(): CacheStorage

