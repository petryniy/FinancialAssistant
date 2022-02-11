package selitskiyapp.hometasks.financialassistant.data.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import selitskiyapp.hometasks.financialassistant.data.storage.models.MoneyHolderEntity

@Dao
interface MoneyHolderDAO {
    @Query("SELECT*FROM moneyHolder")
    fun getMoneyHolders(): List<MoneyHolderEntity>

    @Insert
    fun addMoneyHolder(moneyHolderEntity: MoneyHolderEntity)

    @Query("DELETE FROM moneyHolder")
    fun deleteMoneyHolder()
}