package selitskiyapp.hometasks.financialassistant.data.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import selitskiyapp.hometasks.financialassistant.data.storage.models.MoneyHolderEntity

@Dao
interface MoneyHolderDao {
    @Query("SELECT*FROM moneyHolder")
    suspend fun getMoneyHolders(): List<MoneyHolderEntity>

    @Query("SELECT*FROM moneyHolder WHERE id = :id")
    suspend fun getMoneyHolderById(id: Int): MoneyHolderEntity

    @Insert
    suspend fun addMoneyHolder(moneyHolderEntity: MoneyHolderEntity)

    @Update
    suspend fun updateMoneyHolder(moneyHolderEntity: MoneyHolderEntity)

    @Query("DELETE FROM moneyHolder WHERE id = :id")
    suspend fun deleteMoneyHolder(id: Int)
}