package francisco.calado.wtest.home.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import francisco.calado.wtest.R
import francisco.calado.wtest.home.model.Comment
import francisco.calado.wtest.home.model.CommentListItem
import francisco.calado.wtest.home.model.CommentLoadingItem

class NewsDetailAdapter(
    private val dataList: ArrayList<CommentListItem>,
    private val loadingListItem: CommentLoadingItem
) :
    RecyclerView.Adapter<NewsDetailItem>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsDetailItem {
        return if (viewType == VIEW_TYPE_ITEM) NewsDetailListItem(
            LayoutInflater.from(parent.context).inflate(R.layout.comment_list_item, parent, false)
        )
        else
            NewsDetailLoadingItem(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.home_loading_item, parent, false)
            )
    }

    override fun getItemViewType(position: Int): Int {
        return if (dataList[position] is Comment) VIEW_TYPE_ITEM else VIEW_TYPE_LOADING
    }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: NewsDetailItem, position: Int) {
        holder.bind(dataList[position])
    }

    fun addComments(news: List<Comment>) {
        dataList.addAll(news)
        notifyDataSetChanged()
    }

    fun addLoadMore() {
        if (!dataList.contains(loadingListItem)) {
            dataList.add(loadingListItem)
            notifyItemInserted(dataList.size - 1)
        }
    }

    fun removeLoadMore() {
        dataList.remove(loadingListItem)
    }


    companion object {
        const val VIEW_TYPE_LOADING = 0
        const val VIEW_TYPE_ITEM = 1
    }

}