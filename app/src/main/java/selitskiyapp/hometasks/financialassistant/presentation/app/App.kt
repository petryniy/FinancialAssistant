package selitskiyapp.hometasks.financialassistant.presentation.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import selitskiyapp.hometasks.financialassistant.BuildConfig
import selitskiyapp.hometasks.financialassistant.data.di.dataModule
import selitskiyapp.hometasks.financialassistant.domain.di.domainModule
import selitskiyapp.hometasks.financialassistant.presentation.di.viewModelModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            modules(
                dataModule,
                domainModule,
                viewModelModule
            )
        }
    }
}