package datas.streams.downloads

import datas.aeiouFileSystem
import datas.httpClient
import io.ktor.client.call.body
import io.ktor.client.request.prepareGet
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.request
import io.ktor.util.date.GMTDate
import okio.Path
import okio.Path.Companion.toPath
import okio.buffer
import okio.use
import platform

/**
 * @desc 下载器器
 *
 * @author weihl
 * @date 2023/12/12
 */

// 常规下载 :
// https://github.com/ktorio/ktor-documentation/blob/2.3.7/codeSnippets/snippets/client-download-file

// 断点续传
// https://github.com/ktorio/ktor-documentation/tree/2.3.7/codeSnippets/snippets/client-download-file-range

// 网页流操作
// https://github.com/ktorio/ktor-documentation/tree/2.3.7/codeSnippets/snippets/client-download-streaming

class Downloader {

    private val downloadStorage by lazy { platform.datas.downloadStorage }

    suspend fun fetch(url: String) {
        httpClient.prepareGet(url).execute { httpResponse ->
            val path = newPath(findFileName(httpResponse))
            aeiouFileSystem.sink(path).use { fileSink ->
                fileSink.buffer().use { bufferedSink ->
                    val channel: ByteArray = httpResponse.body()
                    bufferedSink.write(channel)
                }
            }

            println("A file saved to $path")

            //val destPath = "${downloadStorage.dir}/unzip"
            //val zipPath  = "${downloadStorage.dir}/abc.zip"
            //platform.utils.unzip(zipPath,destPath)
            //
            //println("A file unzip")

        }
    }

    private fun newPath(fileName: String): Path {
        return "${downloadStorage.dir}/$fileName".toPath()
    }

    private fun findFileName(httpResponse: HttpResponse): String {
        var tempName = httpResponse.headers["Content-Disposition"]
            ?.split(';')?.get(1)
            ?.split('=')?.get(1)

        if (tempName.isNullOrBlank()) {
            val url = httpResponse.request.url.toString()
            tempName = url.substring(url.lastIndexOf("/"), url.length)
        }

        if (tempName.isNullOrBlank()) {
            tempName = GMTDate().timestamp.toString()
        }

        return tempName
    }

}

