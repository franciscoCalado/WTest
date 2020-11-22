package francisco.calado.wtest.home.view

import android.view.View
import francisco.calado.wtest.home.model.NewsListItem

class HomeLoadingItem(itemView: View) : HomeItem(itemView) {
    override fun bind(newsItem: NewsListItem) {
        //Loading view holder, no binding required
    }
}