package francisco.calado.wtest.home.view

import francisco.calado.wtest.home.model.Comment
import francisco.calado.wtest.home.model.NewsItem

interface NewsDetailView {

    fun populateContent(newsItem: NewsItem)

    fun addComments(comments: List<Comment>)

    fun removeLoadMore()
}