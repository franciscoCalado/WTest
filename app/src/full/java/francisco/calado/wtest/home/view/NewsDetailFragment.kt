package francisco.calado.wtest.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import francisco.calado.wtest.R
import francisco.calado.wtest.home.HomeManager
import francisco.calado.wtest.home.NewsDetailManager
import francisco.calado.wtest.home.model.Comment
import francisco.calado.wtest.home.model.CommentLoadingItem
import francisco.calado.wtest.home.model.NewsItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.full.fragment_news_detail.*

class NewsDetailFragment : Fragment(), NewsDetailView {
    private lateinit var presenter: NewsDetailPresenter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var commentList: RecyclerView
    private lateinit var scrollListener: RecyclerView.OnScrollListener
    private var articleId: Int = -1
    private val adapter: NewsDetailAdapter by lazy {
        NewsDetailAdapter(
            ArrayList(),
            CommentLoadingItem()
        )
    }


    companion object {
        fun newInstance() = NewsDetailFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news_detail, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = NewsDetailPresenter(
            this,
            NewsDetailManager((activity as HomeActivity).getNewsRepository()),
            CompositeDisposable(), AndroidSchedulers.mainThread()
        )

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        articleId = requireArguments().getInt(HomeManager.NEWS_DETAIL_ID, -1)
        linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        commentList = comment_list
        commentList.layoutManager = linearLayoutManager
        commentList.adapter = adapter
        setRecyclerViewScrollListener()
    }

    override fun onResume() {
        super.onResume()

        presenter.populateView(articleId)
    }

    override fun populateContent(newsItem: NewsItem) {
        Glide.with(this)
            .load(newsItem.hero)
            .into(app_graphic)
        Glide.with(this)
            .load(newsItem.avatar)
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_foreground)
            .circleCrop()
            .into(detail_avatar)
        detail_author.text = newsItem.author
        detail_date.text = newsItem.published
        detail_text.text = newsItem.body
        toolbar_title.text = newsItem.title
    }

    override fun addComments(comments: List<Comment>) {
        adapter.addComments(comments)
    }

    override fun removeLoadMore() {
        adapter.removeLoadMore()
    }

    private fun setRecyclerViewScrollListener() {
        var loading = true
        scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                val visibleItemCount = linearLayoutManager.childCount
                val totalItemCount = linearLayoutManager.itemCount
                val pastVisibleItems = linearLayoutManager.findFirstVisibleItemPosition()

                if (loading) {
                    if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                        loading = false
                        adapter.addLoadMore()
                        presenter.handleLoadMore(articleId)
                        loading = true
                    }
                }
            }
        }
        commentList.addOnScrollListener(scrollListener)
    }

}