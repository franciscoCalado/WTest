package francisco.calado.wtest.splashscreen.view

import francisco.calado.wtest.splashscreen.SplashManager
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SplashPresenter(
    private val view: SplashView,
    private val splashManager: SplashManager,
    private val subscriptions: CompositeDisposable
) {

    fun handleStartDownload() {
        subscriptions.add(
            Completable.fromAction { splashManager.downloadFile("https://raw.githubusercontent.com/centraldedados/codigos_postais/master/data/codigos_postais.csv") }
                .subscribeOn(Schedulers.io())
                .subscribe({}, { it.printStackTrace() })
        )
    }

    fun handleDownloadEnd() {
        subscriptions.add(view.downloadEnded()
            .doOnNext { view.setUpEnded() }
            .subscribe({}, { it.printStackTrace() })
        )
    }

    fun clear() {
        subscriptions.clear()
    }
}