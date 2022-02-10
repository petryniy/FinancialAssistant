package selitskiyapp.hometasks.financialassistant.data.di

import androidx.room.Room
import org.koin.dsl.module
import selitskiyapp.hometasks.financialassistant.data.storage.AppDatabase

val dataModule = module {
    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "USERDB").build()
    }

    single {
        get<AppDatabase>().getDataBase()
    }
}