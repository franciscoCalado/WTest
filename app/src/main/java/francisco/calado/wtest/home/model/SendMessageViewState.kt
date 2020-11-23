package francisco.calado.wtest.home.model

data class SendMessageViewState(
    val message: String,
    val email: String,
    val number: String,
    val code: String,
    val date: String,
    val rating: String
)