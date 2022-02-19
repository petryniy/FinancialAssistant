package selitskiyapp.hometasks.financialassistant.data

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import selitskiyapp.hometasks.financialassistant.data.storage.MoneyHolderDao
import selitskiyapp.hometasks.financialassistant.data.storage.OperationsDAO
import selitskiyapp.hometasks.financialassistant.domain.models.MoneyHolder
import selitskiyapp.hometasks.financialassistant.domain.models.Operation
import selitskiyapp.hometasks.financialassistant.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val operationsDAO: OperationsDAO,
    private val moneyHolderDao: MoneyHolderDao
) :
    Repository {

    override suspend fun getOperations(): List<Operation> {
        return withContext(Dispatchers.IO) {
            operationsDAO.getOperations().map { operationsEntity ->
                operationsEntity.toOperation()
            }
        }
    }

    override suspend fun getOperationById(id: Int): Operation {
        return withContext(Dispatchers.IO) {
            operationsDAO.getOperationById(id).toOperation()
        }
    }

    override suspend fun addOperation(operation: Operation) {
        withContext(Dispatchers.IO) {
            operationsDAO.addOperations(operation.toOperationEntity())
        }
    }

    override suspend fun deleteOperations(id: Int) {
        withContext(Dispatchers.IO) {
            operationsDAO.deleteOperations(id)
        }
    }

    override suspend fun getMoneyHolders(): List<MoneyHolder> {
        return withContext(Dispatchers.IO) {
            moneyHolderDao.getMoneyHolders().map { moneyHolderEntity ->
                moneyHolderEntity.toMoneyHolder()
            }
        }
    }

    override suspend fun getMoneyHolderById(id: Int): MoneyHolder {
        return withContext(Dispatchers.IO) {
            moneyHolderDao.getMoneyHolderById(id).toMoneyHolder()
        }
    }

    override suspend fun addMoneyHolder(moneyHolder: MoneyHolder) {
        withContext(Dispatchers.IO) {
            moneyHolderDao.addMoneyHolder(moneyHolder.toMoneyHolderEntity())
            Log.d("addMoneyHolder", "Complete")
        }
    }

    override suspend fun deleteMoneyHolder(id: Int) {
        withContext(Dispatchers.IO) {
            moneyHolderDao.deleteMoneyHolder(id)
        }
    }
}