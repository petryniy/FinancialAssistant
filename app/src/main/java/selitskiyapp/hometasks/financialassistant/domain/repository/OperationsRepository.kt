package selitskiyapp.hometasks.financialassistant.domain.repository

import kotlinx.coroutines.flow.Flow
import selitskiyapp.hometasks.financialassistant.domain.models.Operation
import selitskiyapp.hometasks.financialassistant.domain.models.OperationWithMoneyHolder

interface OperationsRepository {

    fun getAllOperations(): Flow<List<OperationWithMoneyHolder>>

    suspend fun getOperationById(id: Int): Operation

    suspend fun addOperation(operation: Operation)

    suspend fun updateOperation(operation: Operation)

    suspend fun deleteOperation(id: Int)

    fun getOperationsSumValue(): Flow<Long?>

}