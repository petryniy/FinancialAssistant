package selitskiyapp.hometasks.financialassistant.data.repository

import selitskiyapp.hometasks.financialassistant.data.storage.models.NegativeAssetsEntity
import selitskiyapp.hometasks.financialassistant.data.storage.models.PositiveAssetsEntity
import selitskiyapp.hometasks.financialassistant.domain.models.NegativeAssets
import selitskiyapp.hometasks.financialassistant.domain.models.PositiveAssets

fun NegativeAssetsEntity.toNegativeAssetsStorage() =
    NegativeAssets(
        value = value,
        imageId = imageId,
        category = category,
        typeOfValue = typeOfValue,
        date = date,
        comment = comment
    )

fun PositiveAssetsEntity.toPositiveAssetsStorage() =
    PositiveAssets(
        value = value,
        imageId = imageId,
        category = category,
        typeOfValue = typeOfValue,
        date = date,
        comment = comment
    )

fun NegativeAssets.toNegativeAssetsEntity() =
    NegativeAssetsEntity(
        value = value,
        imageId = imageId,
        category = category,
        typeOfValue = typeOfValue,
        date = date,
        comment = comment
    )

fun PositiveAssets.toPositiveAssetsEntity() =
    PositiveAssetsEntity(
        value = value,
        imageId = imageId,
        category = category,
        typeOfValue = typeOfValue,
        date = date,
        comment = comment
    )