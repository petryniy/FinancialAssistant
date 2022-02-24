package selitskiyapp.hometasks.financialassistant.data.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import selitskiyapp.hometasks.financialassistant.data.storage.models.OperationEntity
import selitskiyapp.hometasks.financialassistant.data.storage.models.OperationWithMoneyHolderEntity

@Dao
interface OperationsDAO {

    @Query("SELECT * FROM moneyHolder " +
            "JOIN operations as emb_ ON moneyHolder.moneyId = emb_.moneyHolderId")
    fun getOperations(): Flow<List<OperationWithMoneyHolderEntity>>

    @Query("SELECT*FROM operations WHERE id = :id")
    suspend fun getOperationById(id: Int): OperationEntity

    @Insert
    suspend fun addOperations(operationEntity: OperationEntity)

    @Update
    suspend fun updateOperation(operationEntity: OperationEntity)

    @Query("DELETE FROM operations WHERE id = :id")
    suspend fun deleteOperations(id: Int)

    @Query("SELECT SUM(value) FROM operations")
    fun getOperationsSumValue(): Flow<Long>

}