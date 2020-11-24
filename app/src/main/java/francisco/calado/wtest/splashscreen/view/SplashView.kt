package francisco.calado.wtest.splashscreen.view

import io.reactivex.Observable

interface SplashView {

    fun setUpEnded()

    fun downloadEnded(): Observable<Boolean>

}