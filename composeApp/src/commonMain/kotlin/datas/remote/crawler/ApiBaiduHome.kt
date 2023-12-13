package datas.remote.crawler

import com.mohamedrejeb.ksoup.html.parser.KsoupHtmlHandler
import com.mohamedrejeb.ksoup.html.parser.KsoupHtmlParser
import datas.httpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.prepareGet
import kotlinx.coroutines.runBlocking

/**
 * @desc
 *
 * @author weihl
 * @date 2023/12/13
 */
class ApiBaiduHome {


    private val homeUrl = "https://flagicons.lipis.dev/"

    suspend fun fetch() {
        val content = httpClient.get(homeUrl).body<String>()

        println(content)

        println("------------")
        KsoupHtmlParser(handler = object : KsoupHtmlHandler {

            override fun onText(text: String) {
                super.onText(text)
                println("Text : $text")
            }

            override fun onAttribute(name: String, value: String, quote: String?) {
                super.onAttribute(name, value, quote)
                println("Attribute : $name ,$value , $quote")
            }

            override fun onOpenTag(
                name: String,
                attributes: Map<String, String>,
                isImplied: Boolean
            ) {
                super.onOpenTag(name, attributes, isImplied)
                println("Open tag: $name ,$attributes , $isImplied")
            }
        }).also {
            it.write(content)
        }.also {
            it.onEnd()
        }

    }


}

fun main() {

    runBlocking {

        ApiBaiduHome().fetch()

    }
}