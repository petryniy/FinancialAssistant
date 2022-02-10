package selitskiyapp.hometasks.financialassistant.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import selitskiyapp.hometasks.financialassistant.data.storage.Storage
import selitskiyapp.hometasks.financialassistant.domain.models.Operations
import selitskiyapp.hometasks.financialassistant.domain.repository.Repository

class RepositoryImpl(private val storage: Storage) :
    Repository {

    override suspend fun getOperations(): List<Operations> {
        return withContext(Dispatchers.IO) {
            storage.getOperations().map { operationsEntity ->
                operationsEntity.toOperations()
            }
        }
    }

    override suspend fun addOperations(operations: Operations) {
        withContext(Dispatchers.IO) {
            storage.addOperations(operations.toOperationsEntity())
        }
    }

    override suspend fun deleteOperations(id: Int?) {
        withContext(Dispatchers.IO) {
            storage.deleteOperations()
        }
    }
}