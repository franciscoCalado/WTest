package francisco.calado.wtest.home.model

class CommentLoadingItem() : CommentListItem {
    override fun getType(): CommentListItem.CommentListType {
        return CommentListItem.CommentListType.LOADING
    }
}