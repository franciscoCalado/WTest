package francisco.calado.wtest.home.service

import com.squareup.moshi.Json

data class GetNewsResponse(val items: List<ResponseItem>)

data class ResponseItem(
    val id: Int,
    val title: String,
    @field:Json(name = "published-at") val published: String,
    val hero: String,
    val author: String,
    val avatar: String,
    val summary: String,
    val body: String
)