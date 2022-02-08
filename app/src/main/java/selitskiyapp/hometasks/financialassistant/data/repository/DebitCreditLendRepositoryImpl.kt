package selitskiyapp.hometasks.financialassistant.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import selitskiyapp.hometasks.financialassistant.data.storage.DebitCreditLendStorage
import selitskiyapp.hometasks.financialassistant.domain.models.NegativeAssets
import selitskiyapp.hometasks.financialassistant.domain.models.PositiveAssets
import selitskiyapp.hometasks.financialassistant.domain.repository.DebitCreditLendRepository

class DebitCreditLendRepositoryImpl(private val debitCreditLendStorage: DebitCreditLendStorage) :
    DebitCreditLendRepository {
    override suspend fun getPositiveAssets(): List<PositiveAssets> {
        return withContext(Dispatchers.IO) {
            debitCreditLendStorage.getPositiveAssetsStorage().map { positiveAssetsEntity ->
                positiveAssetsEntity.toPositiveAssetsStorage()

            }
        }
    }

    override suspend fun getNegativeAssets(): List<NegativeAssets> {
        return withContext(Dispatchers.IO) {
            debitCreditLendStorage.getNegativeAssetsStorage().map { negativeAssetsEntity ->
                negativeAssetsEntity.toNegativeAssetsStorage()
            }
        }
    }

    override suspend fun addPositiveAssets(positiveAssets: PositiveAssets) {
        withContext(Dispatchers.IO) {
            debitCreditLendStorage.addPositiveAssetsStorage(positiveAssets.toPositiveAssetsEntity())
        }
    }

    override suspend fun addNegativeAssets(negativeAssets: NegativeAssets) {
        withContext(Dispatchers.IO) {
            debitCreditLendStorage.addNegativeAssetsStorage(negativeAssets.toNegativeAssetsEntity())
        }
    }

    override suspend fun deletePositiveAssets(position: Int) {
        withContext(Dispatchers.IO) {
            debitCreditLendStorage.deletePositiveAssets(position)
        }
    }

    override suspend fun deleteNegativeAssets(position: Int) {
        withContext(Dispatchers.IO) {
            debitCreditLendStorage.deleteNegativeAssets(position)
        }
    }
}