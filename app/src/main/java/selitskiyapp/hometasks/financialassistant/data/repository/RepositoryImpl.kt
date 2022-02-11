package selitskiyapp.hometasks.financialassistant.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import selitskiyapp.hometasks.financialassistant.data.storage.MoneyHolderDAO
import selitskiyapp.hometasks.financialassistant.data.storage.OperationsDAO
import selitskiyapp.hometasks.financialassistant.domain.models.MoneyHolder
import selitskiyapp.hometasks.financialassistant.domain.models.Operations
import selitskiyapp.hometasks.financialassistant.domain.repository.Repository

class RepositoryImpl(
    private val operationsDAO: OperationsDAO,
    private val moneyHolderDAO: MoneyHolderDAO
) :
    Repository {

    override suspend fun getOperations(): List<Operations> {
        return withContext(Dispatchers.IO) {
            operationsDAO.getOperations().map { operationsEntity ->
                operationsEntity.toOperations()
            }
        }
    }

    override suspend fun addOperations(operations: Operations) {
        withContext(Dispatchers.IO) {
            operationsDAO.addOperations(operations.toOperationsEntity())
        }
    }

    override suspend fun deleteOperations(id: Int?) {
        withContext(Dispatchers.IO) {
            operationsDAO.deleteOperations()
        }
    }

    override suspend fun getMoneyHolders(): List<MoneyHolder> {
        return withContext(Dispatchers.IO) {
            moneyHolderDAO.getMoneyHolders().map { moneyHolderEntity ->
                moneyHolderEntity.toMoneyHolder()
            }
        }
    }

    override suspend fun addMoneyHolder(moneyHolder: MoneyHolder) {
        withContext(Dispatchers.IO) {
            moneyHolderDAO.addMoneyHolder(moneyHolder.toMoneyHolderEntity())
        }
    }

    override suspend fun deleteMoneyHolder(id: Int?) {
        withContext(Dispatchers.IO) {
            moneyHolderDAO.deleteMoneyHolder()
        }
    }
}