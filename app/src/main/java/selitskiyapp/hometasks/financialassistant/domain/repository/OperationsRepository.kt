package selitskiyapp.hometasks.financialassistant.domain.repository

import kotlinx.coroutines.flow.Flow
import selitskiyapp.hometasks.financialassistant.data.storage.models.OperationWithMoneyHolderEntity
import selitskiyapp.hometasks.financialassistant.domain.models.Operation

interface OperationsRepository {
    fun getOperations(): Flow<List<OperationWithMoneyHolderEntity>>

    suspend fun getOperationById(id: Int): Operation

    suspend fun addOperation(operation: Operation)

    suspend fun updateOperation(operation: Operation)

    suspend fun deleteOperation(id: Int)
}