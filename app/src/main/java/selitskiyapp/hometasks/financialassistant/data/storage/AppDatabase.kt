package selitskiyapp.hometasks.financialassistant.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import selitskiyapp.hometasks.financialassistant.data.storage.models.NegativeAssetsEntity
import selitskiyapp.hometasks.financialassistant.data.storage.models.PositiveAssetsEntity

@Database(
    entities = [
        NegativeAssetsEntity::class,
        PositiveAssetsEntity::class
    ],
    exportSchema = false,
    version = AppDatabase.VERSION
)

abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val VERSION = 1
        const val POSITIVE_BALANCE = "positiveBalance"
        const val NEGATIVE_BALANCE = "negativeBalance"
    }

    abstract fun getDebitCreditLendStorage(): DebitCreditLendStorage
}