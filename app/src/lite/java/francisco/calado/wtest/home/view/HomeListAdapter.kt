package francisco.calado.wtest.home.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import francisco.calado.wtest.R
import francisco.calado.wtest.home.model.LoadingHomeItem
import francisco.calado.wtest.home.model.NewsItem
import francisco.calado.wtest.home.model.NewsListItem
import io.reactivex.subjects.PublishSubject

class HomeListAdapter(
    private val dataList: ArrayList<NewsListItem>,
    private val loadingListItem: LoadingHomeItem,
    private val clickSubject: PublishSubject<Int>
) :
    RecyclerView.Adapter<HomeItem>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeItem {
        return if (viewType == VIEW_TYPE_ITEM) HomeListItem(
            LayoutInflater.from(parent.context).inflate(R.layout.home_list_item, parent, false)
        )
        else
            HomeLoadingItem(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.home_loading_item, parent, false)
            )
    }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: HomeItem, position: Int) {
        holder.bind(dataList[position])
        holder.itemView.setOnClickListener { clickSubject.onNext((dataList[position] as NewsItem).id) }
    }

    override fun getItemViewType(position: Int): Int {
        return if (dataList[position] is NewsItem) VIEW_TYPE_ITEM else VIEW_TYPE_LOADING
    }

    fun addNews(news: List<NewsListItem>) {
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