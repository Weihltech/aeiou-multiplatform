import java.io.File

/**
 * @desc
 *
 * @author weihl
 * @date 2023/12/12
 */

// data cache
val rootDir = File("./src/desktopMain/datas").apply {
    if (!exists()) {
        mkdirs()
    }
}

val dbPath by lazy {
    "$rootDir/aeiou.db"
}

val cacheDir by lazy {
    File("$rootDir/cache").apply {
        if (!exists()) {
            mkdirs()
        }
    }
}