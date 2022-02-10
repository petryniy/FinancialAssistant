package selitskiyapp.hometasks.financialassistant.domain.repository

import selitskiyapp.hometasks.financialassistant.domain.models.Operations

interface Repository {
    suspend fun getOperations(): List<Operations>

    suspend fun addOperations(operations: Operations)

    suspend fun deleteOperations(id: Int?)

}