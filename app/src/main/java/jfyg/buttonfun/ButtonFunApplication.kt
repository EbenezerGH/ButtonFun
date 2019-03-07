package jfyg.buttonfun

import android.app.Application
import jfyg.buttonfun.di.applicationModule
import jfyg.buttonfun.di.localDataSourceModule
import org.koin.android.ext.android.startKoin

class ButtonFunApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin(
            this,
            listOf(applicationModule, localDataSourceModule)
        )
    }

}
