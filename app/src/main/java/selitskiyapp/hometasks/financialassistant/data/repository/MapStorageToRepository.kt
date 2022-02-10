package selitskiyapp.hometasks.financialassistant.data.repository

import selitskiyapp.hometasks.financialassistant.data.storage.models.OperationsEntity
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