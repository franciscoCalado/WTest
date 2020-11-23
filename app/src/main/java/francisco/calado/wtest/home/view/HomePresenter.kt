package francisco.calado.wtest.home.view

import francisco.calado.wtest.home.HomeManager
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

class HomePresenter(
    private val view: HomeView,
    private val homeManager: HomeManager,
    private val subscriptions: CompositeDisposable,
    private val mainScheduler: Scheduler
) {

    fun populateHome() {
        subscriptions.add(
            homeManager.getFreshNews()
                .observeOn(mainScheduler)
                .doOnSuccess { homeNews ->
                    view.hideLoading()
                    view.addNews(homeNews)
                }
                .subscribe({}, { it.printStackTrace() })
        )
    }

    fun handleLoadMore() {
        subscriptions.add(homeManager.getMoreNews()
            .observeOn(mainScheduler)
            .doOnSuccess { news ->
                view.removeLoadMore()
                view.addNews(news)
            }
            .subscribe({}, { it.printStackTrace() })
        )
    }

    fun handleItemClick() {
        subscriptions.add(view.itemClicked()
            .doOnNext { id -> homeManager.navigateToArticle(id) }
            .subscribe({}, { it.printStackTrace() })
        )
    }

    fun handleFabClick() {
        subscriptions.add(view.fabClicked()
            .doOnNext { homeManager.navigateToSendMessage() }
            .subscribe({}, { it.printStackTrace() })
        )
    }

    fun clear() {
        subscriptions.clear()
    }

    fun dispose() {
        subscriptions.dispose()
    }
}