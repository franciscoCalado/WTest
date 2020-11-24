package francisco.calado.wtest.home.view

import francisco.calado.wtest.home.NewsDetailManager
import io.reactivex.disposables.CompositeDisposable

class NewsDetailPresenter(
    private val view: NewsDetailView,
    private val newsDetailManager: NewsDetailManager,
    private val subscriptions: CompositeDisposable
) {

    fun getArticle(id: Int) {
        subscriptions.add(newsDetailManager
            .getArticle(id)
            .doOnSuccess { result -> view.populateContent(result) }
            .subscribe({}, { it.printStackTrace() })
        )
    }
}