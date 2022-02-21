package selitskiyapp.hometasks.financialassistant.data

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import selitskiyapp.hometasks.financialassistant.data.storage.MoneyHolderDao
import selitskiyapp.hometasks.financialassistant.data.storage.OperationsDAO
import selitskiyapp.hometasks.financialassistant.data.storage.models.OperationWithMoneyHolderEntity
import selitskiyapp.hometasks.financialassistant.domain.models.MoneyHolder
import selitskiyapp.hometasks.financialassistant.domain.models.Operation
import selitskiyapp.hometasks.financialassistant.domain.repository.OperationsRepository
import selitskiyapp.hometasks.financialassistant.domain.repository.MoneyHoldersRepository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val operationsDAO: OperationsDAO,
    private val moneyHolderDao: MoneyHolderDao
) :
    MoneyHoldersRepository, OperationsRepository {

//    override fun getOperations(): Flow<List<Operation>> {
//        return operationsDAO.getOperations().map {
//                it.map { operationsEntity ->
//                    operationsEntity.toOperation()
//                }
//        }
//    }
    override fun getOperations(): Flow<List<OperationWithMoneyHolderEntity>> {
        return operationsDAO.getOperations()
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
        Log.d("operationsDAO.addOperations", " Complete")
    }

    override suspend fun updateOperation(operation: Operation) {
        withContext(Dispatchers.IO) {
            operationsDAO.updateOperation(operation.toOperationEntity())
        }
    }

    override suspend fun deleteOperation(id: Int) {
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

    override suspend fun updateMoneyHolder(moneyHolder: MoneyHolder) {
        withContext(Dispatchers.IO) {
            moneyHolderDao.updateMoneyHolder(moneyHolder.toMoneyHolderEntity())
            Log.d("updateMoneyHolder", "Complete")
        }
    }

    override suspend fun deleteMoneyHolder(id: Int) {
        withContext(Dispatchers.IO) {
            moneyHolderDao.deleteMoneyHolder(id)
        }
    }
}