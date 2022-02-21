package selitskiyapp.hometasks.financialassistant.domain.repository

import selitskiyapp.hometasks.financialassistant.domain.models.MoneyHolder

interface MoneyHoldersRepository {

    suspend fun getMoneyHolders(): List<MoneyHolder>

    suspend fun getMoneyHolderById(id: Int): MoneyHolder

    suspend fun addMoneyHolder(moneyHolder: MoneyHolder)

    suspend fun updateMoneyHolder(moneyHolder: MoneyHolder)

    suspend fun deleteMoneyHolder(id: Int)
}