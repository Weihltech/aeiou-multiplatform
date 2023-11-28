package datas.remote

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json

/**
 * @desc 请求相关
 *
 * @author weihl
 * @date 2023/11/28
 */

val httpClient = HttpClient {
    // 请求内容包含富文本，用 json 解析
    install(ContentNegotiation) {
        json()
    }
}