package francisco.calado.wtest.home.view

import android.util.Patterns
import androidx.core.text.isDigitsOnly
import francisco.calado.wtest.home.model.SendMessageErrorState
import francisco.calado.wtest.home.model.SendMessageViewState
import io.reactivex.disposables.CompositeDisposable
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

class SendMessagePresenter(
    private val view: SendMessageView,
    private val subscriptions: CompositeDisposable
) {

    fun validateInputs() {
        subscriptions.add(view.submitClicked()
            .map { result -> validateResult(result) }
            .doOnNext { result -> view.setErrorState(result) }
            .subscribe({}, { it.printStackTrace() })
        )
    }

    private fun validateResult(result: SendMessageViewState): SendMessageErrorState {
        var message = ""
        var email = ""
        var number = ""
        var code = ""
        var rating = ""

        if (result.message.isEmpty()) message = "empty"
        if (result.email.isEmpty()) email = "empty"
        else if (!isValidEmail(result.email)) email = "invalid"
        if (result.number.isEmpty()) number = "empty"
        else if (!result.number.isDigitsOnly()) number = "invalid"
        if (result.code.isEmpty()) code = "empty"
        else if (!isValidCode(result.code)) code = "invalid"
        if (result.rating == "Rating") rating = "empty"

        return SendMessageErrorState(message, email, number, code, isValidDate(result.date), rating)
    }

    private fun isValidCode(code: String): Boolean {
        val pattern = Pattern.compile("^[A-Za-z\\-A-Za-z]{3,7}$")
        return pattern.matcher(code).matches()
    }

    private fun isValidDate(date: String): String {
        return if (date.isNotEmpty()) {
            val weekDay = SimpleDateFormat("EEEE", Locale.ENGLISH).parse(date)
            when {
                weekDay!!.toString()
                    .toLowerCase(Locale.ENGLISH).contains("mon") || isFutureDate(date) -> "invalid"
                date.isEmpty() -> "empty"
                else -> ""
            }
        } else "empty"
    }

    private fun isFutureDate(date: String): Boolean {
        val dateResult = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.ENGLISH).parse(date)

        val now = Calendar.getInstance()
        val dateCalc = Calendar.getInstance()
        dateCalc.timeInMillis = dateResult!!.time
        return now.before(dateCalc)
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}