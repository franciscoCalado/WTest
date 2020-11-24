package francisco.calado.wtest.home.model

class LoadingHomeItem : NewsListItem {
    override fun getType(): NewsListItem.NewsListType {
        return NewsListItem.NewsListType.LOADING
    }
}