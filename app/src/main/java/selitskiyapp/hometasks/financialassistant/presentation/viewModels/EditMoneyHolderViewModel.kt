package selitskiyapp.hometasks.financialassistant.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import selitskiyapp.hometasks.financialassistant.domain.models.MoneyHolder
import selitskiyapp.hometasks.financialassistant.domain.repository.Repository
import javax.inject.Inject

@HiltViewModel
class EditMoneyHolderViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {
//    private val _moneyHolderSavedFlow = MutableStateFlow<List<MoneyHolder>>(emptyList())
//    val moneyHolderSavedFlow: StateFlow<List<MoneyHolder>> = _moneyHolderSavedFlow
//
//    private val _moneyHoldersList = MutableLiveData<List<MoneyHolder>>()
//    val moneyHoldersList: LiveData<List<MoneyHolder>> get() = _moneyHoldersList



    private val _moneyHolderSavedFlow = MutableStateFlow<Unit?>(null)
    val moneyHolderSavedFlow: StateFlow<Unit?> = _moneyHolderSavedFlow

    private val _moneyHoldersList = MutableLiveData<List<MoneyHolder>>()
    val moneyHoldersList: LiveData<List<MoneyHolder>> get() = _moneyHoldersList

    private val _moneyHolder = MutableLiveData<MoneyHolder>()
    val moneyHolder: LiveData<MoneyHolder> get() = _moneyHolder

    fun addMoneyHolder(moneyHolder: MoneyHolder) {
        viewModelScope.launch {
            repository.addMoneyHolder(moneyHolder)
            _moneyHolderSavedFlow.value = Unit
        }
    }

    fun getMoneyHolders() {
        viewModelScope.launch {
            _moneyHoldersList.value = repository.getMoneyHolders()
        }
    }

    fun getMoneyHolderById(id: Int) {
        viewModelScope.launch {
           _moneyHolder.value =  repository.getMoneyHolderById(id)
        }
    }

    fun deleteMoneyHolder(id: Int) {
        viewModelScope.launch {
            repository.deleteMoneyHolder(id)
        }
    }
}