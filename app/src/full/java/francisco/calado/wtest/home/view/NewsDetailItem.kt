package francisco.calado.wtest.home.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import francisco.calado.wtest.home.model.Comment
import francisco.calado.wtest.home.model.CommentListItem
import io.reactivex.subjects.PublishSubject

abstract class NewsDetailItem(view: View) : RecyclerView.ViewHolder(view) {

    abstract fun bind(comment: CommentListItem)
}