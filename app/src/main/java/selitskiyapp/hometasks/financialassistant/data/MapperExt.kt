package selitskiyapp.hometasks.financialassistant.data

import selitskiyapp.hometasks.financialassistant.data.storage.models.MoneyHolderEntity
import selitskiyapp.hometasks.financialassistant.data.storage.models.OperationsEntity
import selitskiyapp.hometasks.financialassistant.domain.models.MoneyHolder
import selitskiyapp.hometasks.financialassistant.domain.models.Operations

fun OperationsEntity.toOperations() =
    Operations(
        category = category,
        moneyHolderId = moneyHolderId,
        date = date,
        comment = comment
    )

fun Operations.toOperationsEntity() =
    OperationsEntity(
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

