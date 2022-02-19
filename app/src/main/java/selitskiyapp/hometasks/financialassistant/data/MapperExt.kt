package selitskiyapp.hometasks.financialassistant.data

import selitskiyapp.hometasks.financialassistant.data.storage.models.MoneyHolderEntity
import selitskiyapp.hometasks.financialassistant.data.storage.models.OperationEntity
import selitskiyapp.hometasks.financialassistant.domain.models.MoneyHolder
import selitskiyapp.hometasks.financialassistant.domain.models.Operation

fun OperationEntity.toOperation() =
    Operation(
        id = id,
        category = category,
        moneyHolderId = moneyHolderId,
        date = date,
        comment = comment
    )

fun Operation.toOperationEntity() =
    OperationEntity(
        id = id,
        category = category,
        moneyHolderId = moneyHolderId,
        date = date,
        comment = comment
    )

fun MoneyHolderEntity.toMoneyHolder() =
    MoneyHolder(
        id = id,
        name = name,
        type = type,
        balance = balance
    )

fun MoneyHolder.toMoneyHolderEntity() =
    MoneyHolderEntity(
        id = id,
        name = name,
        type = type,
        balance = balance
    )

