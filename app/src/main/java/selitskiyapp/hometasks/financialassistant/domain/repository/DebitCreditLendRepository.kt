package selitskiyapp.hometasks.financialassistant.domain.repository

import selitskiyapp.hometasks.financialassistant.domain.models.NegativeAssets
import selitskiyapp.hometasks.financialassistant.domain.models.PositiveAssets

interface DebitCreditLendRepository {
    suspend fun getPositiveAssets(): List<PositiveAssets>

    suspend fun getNegativeAssets(): List<NegativeAssets>

    suspend fun addPositiveAssets(positiveAssets: PositiveAssets)

    suspend fun addNegativeAssets(negativeAssets: NegativeAssets)

    suspend fun deletePositiveAssets(position: Int)

    suspend fun deleteNegativeAssets(position: Int)
}