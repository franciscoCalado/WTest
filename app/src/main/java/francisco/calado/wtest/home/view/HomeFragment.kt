package francisco.calado.wtest.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import francisco.calado.wtest.R
import francisco.calado.wtest.home.HomeManager
import francisco.calado.wtest.home.model.HomeNews
import francisco.calado.wtest.home.model.LoadingItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment(), HomeView {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var presenter: HomePresenter
    private val clickSubject = PublishSubject.create<Int>()
    private val homeAdapter: HomeListAdapter by lazy {
        HomeListAdapter(
            ArrayList(),
            LoadingItem(), clickSubject)
    }
    private lateinit var scrollListener: RecyclerView.OnScrollListener
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var newsList: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val homeManager =
            HomeManager(
                (activity as HomeActivity).getNewsRepository(),
                (activity as HomeActivity).getNavigator()
            )
        presenter =
            HomePresenter(this, homeManager, CompositeDisposable(), AndroidSchedulers.mainThread())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        newsList = view.news_list
        progressBar = view.progress_bar
        newsList.layoutManager = linearLayoutManager
        newsList.adapter = homeAdapter
        setRecyclerViewScrollListener()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dispose()
    }

    override fun onStop() {
        super.onStop()
        presenter.clear()
    }

    override fun onResume() {
        super.onResume()
        presenter.populateHome()
        presenter.handleClick()
    }

    override fun addNews(homeNews: HomeNews) {
        homeAdapter.addNews(homeNews.newsList)
        newsList.addOnScrollListener(scrollListener)
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
        newsList.visibility = View.VISIBLE
    }

    override fun removeLoadMore() {
        homeAdapter.removeLoadMore()
    }

    override fun itemClicked(): PublishSubject<Int> {
        return clickSubject
    }

    private fun setRecyclerViewScrollListener() {
        var loading = true;
        scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                val visibleItemCount = linearLayoutManager.childCount
                val totalItemCount = linearLayoutManager.itemCount
                val pastVisibleItems = linearLayoutManager.findFirstVisibleItemPosition()

                if (loading) {
                    if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                        loading = false
                        homeAdapter.addLoadMore()
                        presenter.handleLoadMore()
                        loading = true
                    }
                }
            }
        }
        newsList.addOnScrollListener(scrollListener)
    }
}