package francisco.calado.wtest.home.view

import android.view.View
import francisco.calado.wtest.home.model.NewsItem
import francisco.calado.wtest.home.model.NewsListItem
import kotlinx.android.synthetic.full.home_list_item.view.*

class HomeListItem(itemView: View) : HomeItem(itemView) {

    override fun bind(newsItem: NewsListItem) {
        if (newsItem is NewsItem) {
            itemView.title.text = newsItem.title
            itemView.author.text = newsItem.author
            itemView.summary.text = newsItem.summary
            itemView.date.text = newsItem.published
        }
    }
}