package project.seo.pictureviewer

import timber.log.Timber

class DevApplication : MyApplication() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}