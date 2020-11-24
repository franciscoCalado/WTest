package francisco.calado.wtest.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import francisco.calado.wtest.R
import francisco.calado.wtest.home.HomeManager
import francisco.calado.wtest.home.NewsDetailManager
import francisco.calado.wtest.home.model.NewsItem
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.lite.fragment_news_detail.*

class NewsDetailFragment : Fragment(), NewsDetailView {
    private lateinit var presenter: NewsDetailPresenter


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
            CompositeDisposable()
        )

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = requireArguments().getInt(HomeManager.NEWS_DETAIL_ID, -1)
        presenter.getArticle(id)
    }

    override fun populateContent(newsItem: NewsItem) {
        Glide.with(this).load(newsItem.hero).into(app_graphic)
        detail_author.text = newsItem.author
        detail_date.text = newsItem.published
        detail_text.text = newsItem.body
        toolbar_title.text = newsItem.title
    }
}