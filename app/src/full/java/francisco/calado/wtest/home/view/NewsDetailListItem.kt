package francisco.calado.wtest.home.view

import android.view.View
import com.bumptech.glide.Glide
import francisco.calado.wtest.R
import francisco.calado.wtest.home.model.Comment
import francisco.calado.wtest.home.model.CommentListItem
import kotlinx.android.synthetic.full.comment_list_item.view.*

class NewsDetailListItem(itemView: View) : NewsDetailItem(itemView) {

    override fun bind(comment: CommentListItem) {
        if (comment is Comment) {
            Glide.with(itemView)
                .load(comment.avatar)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .circleCrop()
                .into(itemView.comment_avatar)
            itemView.comment_author.text = comment.name
            itemView.comment_date.text = comment.published
            itemView.comment_body.text = comment.body
        }
    }

}