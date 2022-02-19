package selitskiyapp.hometasks.financialassistant.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import selitskiyapp.hometasks.financialassistant.data.storage.models.MoneyHolderEntity
import selitskiyapp.hometasks.financialassistant.data.storage.models.OperationEntity

@Database(
    entities = [
        OperationEntity::class,
        MoneyHolderEntity::class
    ],
    exportSchema = false,
    version = AppDatabase.VERSION
)

abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val VERSION = 1
    }

    abstract fun getOperationsDAO(): OperationsDAO

    abstract fun getMoneyHolderDAO(): MoneyHolderDao
}