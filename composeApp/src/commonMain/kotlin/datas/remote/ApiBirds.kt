package datas.remote

import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.serialization.json.Json

/**
 * @desc
 * https://github.com/SebastianAigner/demo-image-api/blob/main/pictures.json
 * @author weihl
 * @date 2023/11/28
 */

class ApiBirds {
    private val url =
        "https://raw.githubusercontent.com/SebastianAigner/demo-image-api/main/pictures.json"

    suspend fun fetchBirdImages(): List<BirdImage> {
        return Json.decodeFromString(httpClient.get(url).body())
    }
}

// [{
//    "category": "PIGEON",
//    "path": "pigeon/vladislav-nikonov-yVYaUSwkTOs-unsplash.jpg",
//    "author": "Vladislav"
// }]