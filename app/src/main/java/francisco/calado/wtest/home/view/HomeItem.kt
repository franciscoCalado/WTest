package francisco.calado.wtest.home.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import francisco.calado.wtest.home.model.NewsListItem

abstract class HomeItem(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind(newsItem: NewsListItem)
}