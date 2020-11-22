package francisco.calado.wtest.home

import android.os.Bundle
import francisco.calado.wtest.home.model.HomeNews
import francisco.calado.wtest.home.view.NewsDetailFragment
import io.reactivex.Single

class HomeManager(private val newsRepository: NewsRepository, private val homeNavigator: HomeNavigator) {

    private var page = 1

    fun getFreshNews() : Single<HomeNews> {
        return newsRepository.getFreshNews()
    }

    fun getMoreNews() : Single<HomeNews> {
        page++
        return newsRepository.getMoreNews(page)
    }

    fun navigateToArticle(id: Int){
        val frag = NewsDetailFragment.newInstance()
        val args = Bundle()
        args.putInt(NEWS_DETAIL_ID, id)
        frag.arguments = args
        homeNavigator.navigateToFragment(frag)
    }

    companion object{
        const val NEWS_DETAIL_ID = "news_detail_id"
    }
}