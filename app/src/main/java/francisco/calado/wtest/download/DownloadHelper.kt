package francisco.calado.wtest.download

import android.app.DownloadManager
import android.net.Uri
import android.os.Environment

class DownloadHelper(private val downloadManager: DownloadManager) {

    public fun downloadFile(url: String) {
        val uri: Uri =
            Uri.parse("https://raw.githubusercontent.com/centraldedados/codigos_postais/master/data/codigos_postais.csv")
        val request = DownloadManager.Request(uri)
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN)
        request.setDestinationInExternalPublicDir(
            Environment.DIRECTORY_DOWNLOADS,
            uri.lastPathSegment
        )
        downloadManager.enqueue(request)
    }

}