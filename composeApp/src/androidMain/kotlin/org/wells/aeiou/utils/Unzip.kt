package org.wells.aeiou.utils

/**
 * @desc
 *
 * @author weihl
 * @date 2023/12/12
 */
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.util.zip.ZipFile

class Unzip {

    fun start(zipFilePath: String, destDirectory: String) {
        val destDir = File(destDirectory)
        if (!destDir.exists()) {
            destDir.mkdir()
        }
        val zipFile = ZipFile(zipFilePath)
        zipFile.entries().asSequence().forEach { entry ->
            val filePath = destDirectory + File.separator + entry.name
            if (!entry.isDirectory) {
                extractFile(zipFile.getInputStream(entry), filePath)
            } else {
                val dir = File(filePath)
                dir.mkdir()
            }
        }
    }

    fun extractFile(inputStream: InputStream, destFilePath: String) {
        val bos = FileOutputStream(destFilePath)
        val bytesIn = ByteArray(4096)
        var read: Int
        while (inputStream.read(bytesIn).also { read = it } != -1) {
            bos.write(bytesIn, 0, read)
        }
        bos.close()
    }

}
