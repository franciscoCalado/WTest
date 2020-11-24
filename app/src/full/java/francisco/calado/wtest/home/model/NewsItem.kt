package francisco.calado.wtest.home.model

class NewsItem(
    val id: Int,
    val title: String,
    val published: String,
    val hero: String,
    val author: String,
    val avatar: String,
    val summary: String,
    val body: String
) : NewsListItem {
    override fun getType(): NewsListItem.NewsListType {
        return NewsListItem.NewsListType.ITEM
    }
}