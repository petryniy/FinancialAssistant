package selitskiyapp.hometasks.financialassistant.data.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import selitskiyapp.hometasks.financialassistant.data.storage.models.NegativeAssetsEntity
import selitskiyapp.hometasks.financialassistant.data.storage.models.PositiveAssetsEntity
import selitskiyapp.hometasks.financialassistant.domain.models.NegativeAssets
import selitskiyapp.hometasks.financialassistant.domain.models.PositiveAssets

@Dao
interface DebitCreditLendStorage {
    @Query("SELECT*FROM positiveBalance")
    fun getPositiveAssetsStorage(): List<PositiveAssetsEntity>

    @Query("SELECT*FROM negativeBalance")
    fun getNegativeAssetsStorage(): List<NegativeAssetsEntity>

    @Insert
    fun addPositiveAssetsStorage(positiveAssetsData: PositiveAssetsEntity)

    @Insert
    fun addNegativeAssetsStorage(negativeAssetsData: NegativeAssetsEntity)

    @Query("DELETE FROM positiveBalance WHERE ID = :position")
    fun deletePositiveAssets(position: Int?)

    @Query("DELETE FROM negativeBalance WHERE ID = :position")
    fun deleteNegativeAssets(position: Int?)
}