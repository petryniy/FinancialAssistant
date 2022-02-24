package selitskiyapp.hometasks.financialassistant.domain.models

import selitskiyapp.hometasks.financialassistant.data.storage.models.MoneyHolderEntity
import selitskiyapp.hometasks.financialassistant.data.storage.models.OperationEntity

data class OperationWithMoneyHolder(
    val operationEntity: OperationEntity,
    val moneyHolderEntity: MoneyHolderEntity
)
