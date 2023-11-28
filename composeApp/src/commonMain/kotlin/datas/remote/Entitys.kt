package datas.remote

import kotlinx.serialization.Serializable

/**
 * @desc
 *
 * @author weihl
 * @date 2023/11/28
 */

@Serializable
data class BirdImage(
    val author: String,
    val category: String,
    val path: String
)

