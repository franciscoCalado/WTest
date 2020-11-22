package francisco.calado.wtest.home.model

interface NewsListItem {

    fun getType() : NewsListType

    enum class NewsListType{ITEM, LOADING}
}