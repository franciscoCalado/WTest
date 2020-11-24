package francisco.calado.wtest.splashscreen.view

import francisco.calado.wtest.download.DownloadHelper
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SplashPresenter(
    private val view: SplashView,
    private val downloadHelper: DownloadHelper,
    private val subscriptions: CompositeDisposable
) {

    fun handleStartUp() {
        subscriptions.add(
            Completable.fromAction { downloadHelper.downloadFile("https://raw.githubusercontent.com/centraldedados/codigos_postais/master/data/codigos_postais.csv") }
                .subscribeOn(Schedulers.io())
                .subscribe({}, { it.printStackTrace() })
        )
    }

    fun handleDownloadEnd() {
        subscriptions.add(view.downloadEnded()
            .doOnNext { mapFileToRoom() }
            .subscribe({}, { it.printStackTrace() })
        )
    }

    private fun mapFileToRoom() {
        view.setUpEnded()
    }

    fun clear() {
        subscriptions.clear()
    }
}