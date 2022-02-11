package selitskiyapp.hometasks.financialassistant.domain.repository

import androidx.room.Insert
import androidx.room.Query
import selitskiyapp.hometasks.financialassistant.data.storage.models.MoneyHolderEntity
import selitskiyapp.hometasks.financialassistant.domain.models.MoneyHolder
import selitskiyapp.hometasks.financialassistant.domain.models.Operations

interface Repository {
    suspend fun getOperations(): List<Operations>

    suspend fun addOperations(operations: Operations)

    suspend fun deleteOperations(id: Int?)

    suspend fun getMoneyHolders(): List<MoneyHolder>

    suspend fun addMoneyHolder(moneyHolder: MoneyHolder)

    suspend fun deleteMoneyHolder(id: Int?)
}