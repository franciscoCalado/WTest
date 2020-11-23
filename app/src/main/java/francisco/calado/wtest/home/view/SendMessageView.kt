package francisco.calado.wtest.home.view

import francisco.calado.wtest.home.model.SendMessageErrorState
import francisco.calado.wtest.home.model.SendMessageViewState
import io.reactivex.Observable

interface SendMessageView {

    fun submitClicked(): Observable<SendMessageViewState>

    fun setErrorState(viewState: SendMessageErrorState)
}