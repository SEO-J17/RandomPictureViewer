package project.seo.pictureviewer.dev

import project.seo.pictureviewer.MyApplication
import timber.log.Timber

class DevApplication : MyApplication() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}