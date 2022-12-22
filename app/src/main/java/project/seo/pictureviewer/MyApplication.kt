package project.seo.pictureviewer

import android.app.Application
import project.seo.pictureviewer.database.LocalService

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        LocalService.create(applicationContext)
    }
}