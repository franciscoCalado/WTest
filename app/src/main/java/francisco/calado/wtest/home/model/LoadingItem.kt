package francisco.calado.wtest.home.model

class LoadingItem: NewsListItem {
    override fun getType(): NewsListItem.NewsListType {
        return NewsListItem.NewsListType.LOADING
    }
}