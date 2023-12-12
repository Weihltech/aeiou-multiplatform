package datas

import io.ktor.client.HttpClient
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

        //install(HttpCache) {
        //    // 如果您使用配置的 Cache-Control 标头对资源发出两个后续请求，
        //    // 则客户端仅执行第一个请求并跳过第二个请求，因为数据已保存在缓存中。
        //    // KeyWords -> the caching-headers
        //    // TODO 过滤不需要缓存的参数
        //    publicStorage(createCacheStorage())
        //}
    }
}