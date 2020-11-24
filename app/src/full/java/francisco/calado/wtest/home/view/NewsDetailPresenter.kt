package francisco.calado.wtest.home.view

import francisco.calado.wtest.home.NewsDetailManager
import francisco.calado.wtest.home.model.Comment
import francisco.calado.wtest.home.model.NewsItem
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import java.util.function.BiFunction

class NewsDetailPresenter(
    private val view: NewsDetailView,
    private val newsDetailManager: NewsDetailManager,
    private val subscriptions: CompositeDisposable,
    private val mainScheduler: Scheduler
) {

    fun populateView(id: Int) {
        subscriptions.add(Single.zip(
            newsDetailManager.getArticle(id),
            newsDetailManager.getFreshComments(id),
            object : BiFunction<NewsItem, List<Comment>, Pair<NewsItem, List<Comment>>>,
                io.reactivex.functions.BiFunction<NewsItem, List<Comment>, Pair<NewsItem, List<Comment>>> {
                override fun apply(
                    p0: NewsItem,
                    p1: List<Comment>
                ): Pair<NewsItem, List<Comment>> {
                    return Pair(p0, p1)
                }

            })
            .observeOn(mainScheduler)
            .doOnSuccess { result ->
                view.populateContent(result.first)
                view.addComments(result.second)
            }
            .subscribe({}, { it.printStackTrace() })
        )

    }

    fun handleLoadMore(id: Int) {
        subscriptions.add(
            newsDetailManager.getMoreComments(id)
                .observeOn(mainScheduler)
                .doOnSuccess { result ->
                    view.removeLoadMore()
                    view.addComments(result)
                }
                .subscribe({}, { it.printStackTrace() })
        )
    }
}