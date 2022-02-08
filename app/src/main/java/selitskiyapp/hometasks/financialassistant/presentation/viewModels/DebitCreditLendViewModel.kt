package selitskiyapp.hometasks.financialassistant.presentation.viewModels

import androidx.lifecycle.*
import androidx.navigation.Navigation.findNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import selitskiyapp.hometasks.financialassistant.R
import selitskiyapp.hometasks.financialassistant.domain.models.PositiveAssets
import selitskiyapp.hometasks.financialassistant.domain.repository.DebitCreditLendRepository
import selitskiyapp.hometasks.financialassistant.presentation.MainActivity

class DebitCreditLendViewModel(
    private val debitCreditLendRepository: DebitCreditLendRepository
) : ViewModel() {
    private val _debitLiveData = MutableLiveData<List<PositiveAssets>>()
    val debitLiveData: LiveData<List<PositiveAssets>> get() = _debitLiveData

    fun onItemClicked(position: Int) {

        toEditBottom(R.id.to_editBottom)

        val itemPosition = _debitLiveData.value?.get(position) ?: return
        val itemValue = _debitLiveData.value?.component1() ?: return
        val imageId = _debitLiveData.value?.component1()?.imageId ?: return
        val category: String
        val typeOfValue: String
        val date: String
        val comment: String
    }

    private fun toEditBottom(id: Int) {
        findNavController(MainActivity(), R.id.fragment_container).navigate(id)
    }

    fun addDebit(positiveAssets: PositiveAssets) {
        viewModelScope.launch {
            debitCreditLendRepository.addPositiveAssets(positiveAssets)
//            Toast.makeText(coroutineContext, "getPositiveAssets", Toast.LENGTH_LONG).show()
        }
    }

    fun getDebit() {
        viewModelScope.launch {
            _debitLiveData.value = debitCreditLendRepository.getPositiveAssets()
        }
    }

    fun deleteItem(position: Int) {
        viewModelScope.launch {
            debitCreditLendRepository.deletePositiveAssets(position)

        }
    }

//    private fun getPositiveBalance(): Long {
//        var result = viewModelScope.launch {
//            val items = debitCreditLendRepository.getPositiveAssets()
//            items.component1().value.toLong()
//        }
//        return  result
//    }
}