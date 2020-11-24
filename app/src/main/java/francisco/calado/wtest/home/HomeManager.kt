package francisco.calado.wtest.home

import android.os.Bundle
import francisco.calado.wtest.home.model.HomeNews
import francisco.calado.wtest.home.view.NewsDetailFragment
import francisco.calado.wtest.home.view.SendMessageFragment
import io.reactivex.Single

class HomeManager(
    private val homeRepository: HomeRepository,
    private val homeNavigator: FragmentNavigator
) {

    private var page = 1

    fun getFreshNews(): Single<HomeNews> {
        return homeRepository.getFreshNews()
    }

    fun getMoreNews(): Single<HomeNews> {
        page++
        return homeRepository.getMoreNews(page)
    }

    fun navigateToArticle(id: Int) {
        val frag =
            NewsDetailFragment.newInstance()
        val args = Bundle()
        args.putInt(NEWS_DETAIL_ID, id)
        frag.arguments = args
        homeNavigator.navigateToFragment(frag)
    }

    fun navigateToSendMessage() {
        homeNavigator.navigateToFragment(SendMessageFragment.newInstance())
    }

    companion object {
        const val NEWS_DETAIL_ID = "news_detail_id"
    }
}