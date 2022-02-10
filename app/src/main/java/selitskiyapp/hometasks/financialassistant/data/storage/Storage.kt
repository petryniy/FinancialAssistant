package selitskiyapp.hometasks.financialassistant.data.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import selitskiyapp.hometasks.financialassistant.data.storage.models.OperationsEntity

@Dao
interface Storage {
    @Query("SELECT*FROM operations")
    fun getOperations(): List<OperationsEntity>

    @Insert
    fun addOperations(operationsData: OperationsEntity)

    @Query("DELETE FROM operations")
//    WHERE id
    fun deleteOperations()
}