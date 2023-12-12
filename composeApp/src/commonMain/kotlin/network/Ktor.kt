package network

import createCacheStorage
import io.ktor.client.HttpClient
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json

/**
 * @desc 请求相关
 *
 * @author weihl
 * @date 2023/11/28
 */

val httpClient by lazy {
    HttpClient {
        // 请求内容包含富文本，用 json 解析
        install(ContentNegotiation) {
            json()
        }
        install(HttpCache) {
            publicStorage(createCacheStorage())
        }
    }
}