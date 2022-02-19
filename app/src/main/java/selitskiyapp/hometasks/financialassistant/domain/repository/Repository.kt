package selitskiyapp.hometasks.financialassistant.domain.repository

import selitskiyapp.hometasks.financialassistant.domain.models.MoneyHolder
import selitskiyapp.hometasks.financialassistant.domain.models.Operation

interface Repository {
    suspend fun getOperations(): List<Operation>

    suspend fun getOperationById(id: Int): Operation

    suspend fun addOperation(operation: Operation)

    suspend fun deleteOperations(id: Int)

    suspend fun getMoneyHolders(): List<MoneyHolder>

    suspend fun getMoneyHolderById(id: Int): MoneyHolder

    suspend fun addMoneyHolder(moneyHolder: MoneyHolder)

    suspend fun deleteMoneyHolder(id: Int)
}