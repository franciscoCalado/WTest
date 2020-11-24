package francisco.calado.wtest.home.model

interface CommentListItem {

    fun getType(): CommentListType

    enum class CommentListType { ITEM, LOADING }
}