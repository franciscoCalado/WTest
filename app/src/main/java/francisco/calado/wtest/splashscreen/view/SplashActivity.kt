package francisco.calado.wtest.splashscreen.view

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import francisco.calado.wtest.R
import francisco.calado.wtest.download.DownloadHelper
import francisco.calado.wtest.home.view.HomeActivity
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

class SplashActivity : AppCompatActivity(),
    SplashView {

    private lateinit var presenter: SplashPresenter
    private lateinit var downloadEndedSubject: PublishSubject<Boolean>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        downloadEndedSubject = PublishSubject.create()
        presenter = SplashPresenter(
            this,
            DownloadHelper(getSystemService(DOWNLOAD_SERVICE) as DownloadManager),
            CompositeDisposable()
        )
    }

    override fun onStart() {
        super.onStart()
        val successIntentFilter = IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
        presenter.handleStartUp()
        presenter.handleDownloadEnd()

        registerReceiver(successBroadcastReceiver, successIntentFilter)
    }


    override fun onStop() {
        super.onStop()
        unregisterReceiver(successBroadcastReceiver)
        presenter.clear()
    }

    override fun setUpEnded() {
        val i = Intent(this, HomeActivity::class.java)
        startActivity(i)
    }

    override fun downloadEnded(): Observable<Boolean> {
        return downloadEndedSubject
    }

    private val successBroadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            downloadEndedSubject.onNext(true)
        }
    }


}
