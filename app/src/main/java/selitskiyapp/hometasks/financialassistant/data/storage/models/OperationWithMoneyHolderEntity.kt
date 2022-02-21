package selitskiyapp.hometasks.financialassistant.data.storage.models

import androidx.room.Embedded

data class OperationWithMoneyHolderEntity(
    @Embedded
    val operationEntity: OperationEntity,
    @Embedded
    val moneyHolderEntity: MoneyHolderEntity
)
