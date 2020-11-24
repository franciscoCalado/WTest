package francisco.calado.wtest.home

import francisco.calado.wtest.home.model.Comment
import francisco.calado.wtest.home.model.NewsItem
import io.reactivex.Single

class NewsDetailManager(private val repository: HomeRepository) {

    private var page = 1

    fun getArticle(id: Int): Single<NewsItem> {
        return repository.getArticle(id)
    }

    fun getFreshComments(id: Int): Single<List<Comment>> {
        return repository.getFreshComments(id)
    }

    fun getMoreComments(id: Int): Single<List<Comment>> {
        page++
        return repository.getMoreComments(id, page)
    }
}