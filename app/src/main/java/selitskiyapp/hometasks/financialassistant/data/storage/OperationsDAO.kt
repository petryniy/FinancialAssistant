package selitskiyapp.hometasks.financialassistant.data.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import selitskiyapp.hometasks.financialassistant.data.storage.models.OperationEntity

@Dao
interface OperationsDAO {
    @Query("SELECT*FROM operations")
    fun getOperations(): List<OperationEntity>

    @Query("SELECT*FROM operations WHERE id = :id")
    fun getOperationById(id: Int): OperationEntity

    @Insert
    fun addOperations(operationEntity: OperationEntity)

    @Query("DELETE FROM operations WHERE id = :id")
    fun deleteOperations(id: Int)
}