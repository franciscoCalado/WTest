package francisco.calado.wtest.home.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import francisco.calado.wtest.R
import francisco.calado.wtest.WTestApplication
import francisco.calado.wtest.home.FragmentNavigator
import francisco.calado.wtest.home.HomeRepository
import francisco.calado.wtest.home.service.NewsService

class HomeActivity : AppCompatActivity() {

    private val homeNavigator: FragmentNavigator by lazy {
        FragmentNavigator(supportFragmentManager, R.id.fragment_container)
    }
    private lateinit var homeRepository: HomeRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        homeRepository =
            HomeRepository(NewsService((application as WTestApplication).getNewsRetrofit()))
        homeNavigator.navigateToInitialFragment(HomeFragment.newInstance())
    }

    fun getNavigator() = homeNavigator

    fun getNewsRepository() = homeRepository
}