package francisco.calado.wtest.home.model

data class Comment(
    val id: Int,
    val commentId: Int,
    val published: String,
    val name: String,
    val avatar: String,
    val body: String
) : CommentListItem {
    override fun getType(): CommentListItem.CommentListType {
        return CommentListItem.CommentListType.ITEM
    }
}