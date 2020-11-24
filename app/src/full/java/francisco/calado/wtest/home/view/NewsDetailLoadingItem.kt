package francisco.calado.wtest.home.view

import android.view.View
import francisco.calado.wtest.home.model.CommentListItem

class NewsDetailLoadingItem(itemView: View) : NewsDetailItem(itemView) {
    override fun bind(comment: CommentListItem) {
        //Loading view holder, nothing to bind
    }
}