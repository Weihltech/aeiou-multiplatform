package datas.entitys

import kotlinx.serialization.Serializable

/**
 * @desc
 *
 * @author weihl
 * @date 2023/12/4
 */
@Serializable
data class BirdInfo(
    val author: String,
    val category: String,
    val path: String
)