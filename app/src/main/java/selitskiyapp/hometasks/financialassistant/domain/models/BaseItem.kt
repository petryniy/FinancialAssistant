package selitskiyapp.hometasks.financialassistant.domain.models

import selitskiyapp.hometasks.financialassistant.data.storage.models.MoneyHolderEntity
import selitskiyapp.hometasks.financialassistant.data.storage.models.OperationEntity

sealed class BaseItem

data class HeadItem(
    val date: String
): BaseItem()

data class OperationWithMoneyHolder(
    val operationEntity: OperationEntity,
    val moneyHolderEntity: MoneyHolderEntity
): BaseItem()