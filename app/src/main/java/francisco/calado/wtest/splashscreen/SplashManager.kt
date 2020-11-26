package francisco.calado.wtest.splashscreen

import francisco.calado.wtest.download.DownloadHelper


class SplashManager(private val downloadHelper: DownloadHelper) {

    //This takes much longer than I expected;.
    //Would need to be done async in the background after splash screen or the user will be locked out for too long.
    //I would accomplish this with either a foreground service or a
    //WorkManager worker (most likely the latter but would have to study execution times).
    //fun parseCsvIntoDatabase(): Completable {
    //    return Completable.fromAction {
    //        try {
    //            val reader = CSVReader(FileReader(filePath))
    //            var nextLine: Array<String>
    //            while (reader.readNext().also { nextLine = it } != null) {
    //                (database as AppDatabase).postalCodeDao()
    //                    .insertPostalCode(
    //                        PostalCode(
    //                            nextLine[16],
    //                            String.format("$nextLine[14]-$nextLine[15]")
    //                        )
    //                    )
    //                Log.d("Inserting entry", reader.linesRead.toString())
    //            }
    //        } catch (e: IOException) {
    //            e.printStackTrace()
    //        }
    //    }.subscribeOn(Schedulers.io())
    //}

    fun downloadFile(path: String) {
        downloadHelper.downloadFile(path)
    }
}