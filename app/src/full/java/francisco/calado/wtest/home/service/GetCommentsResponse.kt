package francisco.calado.wtest.home.service

import com.squareup.moshi.Json

data class GetCommentsResponse(
    val id: Int,
    val articleId: Int,
    @field:Json(name = "published-at") val published: String,
    val name: String,
    val avatar: String,
    val body: String
)