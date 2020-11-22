package francisco.calado.wtest.home

import francisco.calado.wtest.home.model.HomeNews
import francisco.calado.wtest.home.model.NewsItem
import francisco.calado.wtest.home.service.GetNewsResponse
import francisco.calado.wtest.home.service.NewsService
import francisco.calado.wtest.home.service.ResponseItem
import io.reactivex.Single
import java.text.SimpleDateFormat

class NewsRepository(private val newsService: NewsService) {
    private val limit = 10
    private var cache = HomeNews(ArrayList())

    fun getFreshNews(): Single<HomeNews> {
        return if (cache.newsList.isEmpty()) newsService.getNews(1, limit)
            .map { result -> mapToHomeNews(result) }
            .doOnSuccess { result -> cache = result!! }
        else Single.just(cache)
    }

    fun getMoreNews(page: Int): Single<HomeNews> {
        return newsService.getNews(page, limit).map { result -> mapToHomeNews(result) }
    }

    fun getArticle(id: Int): Single<NewsItem> {
        for (newsItem in cache.newsList) {
            if (newsItem.id == id)
                return Single.just(newsItem)
        }
        return newsService.getArticle(id).map { result -> mapToNewsItem(result) }

    }

    private fun mapToHomeNews(getNewsResponse: GetNewsResponse): HomeNews {
        val newsList = ArrayList<NewsItem>()

        for (responseItem in getNewsResponse.items) {

            newsList.add(
                mapToNewsItem(responseItem)
            )
        }
        return HomeNews(newsList)
    }

    private fun mapToNewsItem(responseItem: ResponseItem): NewsItem {
        return NewsItem(
            responseItem.id,
            responseItem.title,
            formatDate(responseItem.published),
            responseItem.hero,
            responseItem.author,
            responseItem.summary,
            responseItem.body
        )
    }
    private fun formatDate(toFormat: String): String {
        val inPattern = "yyyy-MM-dd"
        val outPattern = "dd MMMM, yyyy"
        val inFormat = SimpleDateFormat(inPattern)
        val outFormat = SimpleDateFormat(outPattern)
        val serverDate = toFormat.split("T")[0]
        val date = inFormat.parse(serverDate)

        return outFormat.format(date)
    }
}