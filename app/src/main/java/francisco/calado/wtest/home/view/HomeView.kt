package francisco.calado.wtest.home.view

import francisco.calado.wtest.home.model.HomeNews
import io.reactivex.subjects.PublishSubject

interface HomeView {

    fun addNews(homeNews: HomeNews)

    fun hideLoading()

    fun removeLoadMore()

    fun itemClicked() : PublishSubject<Int>
}