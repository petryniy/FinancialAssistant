package selitskiyapp.hometasks.financialassistant.data

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import selitskiyapp.hometasks.financialassistant.data.storage.MoneyHolderDao
import selitskiyapp.hometasks.financialassistant.data.storage.OperationsDAO
import selitskiyapp.hometasks.financialassistant.domain.models.MoneyHolder
import selitskiyapp.hometasks.financialassistant.domain.models.Operations
import selitskiyapp.hometasks.financialassistant.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val operationsDAO: OperationsDAO,
    private val moneyHolderDao: MoneyHolderDao
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
            moneyHolderDao.getMoneyHolders().map { moneyHolderEntity ->
                moneyHolderEntity.toMoneyHolder()
            }
        }
    }

    override suspend fun addMoneyHolder(moneyHolder: MoneyHolder) {
        withContext(Dispatchers.IO) {
            moneyHolderDao.addMoneyHolder(moneyHolder.toMoneyHolderEntity())
            Log.d("addMoneyHolder", "Complete")
        }
    }

    override suspend fun deleteMoneyHolder(id: Int?) {
        withContext(Dispatchers.IO) {
            moneyHolderDao.deleteMoneyHolder()
        }
    }
}