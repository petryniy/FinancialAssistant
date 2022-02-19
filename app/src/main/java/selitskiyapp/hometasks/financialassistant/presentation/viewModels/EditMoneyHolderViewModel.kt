package selitskiyapp.hometasks.financialassistant.presentation.viewModels

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
    private val _moneyHoldersListFlow = MutableStateFlow<List<MoneyHolder>>(emptyList())
    val moneyHoldersListFlow: StateFlow<List<MoneyHolder>> get() = _moneyHoldersListFlow

    private val _moneyHolder = MutableStateFlow(MoneyHolder(0, "0",0,0))
    val moneyHolder: StateFlow<MoneyHolder> get() = _moneyHolder

    init {
        getMoneyHolders()
    }

    fun addMoneyHolder(moneyHolder: MoneyHolder) {
        viewModelScope.launch {
            repository.addMoneyHolder(moneyHolder)
        }
    }

    fun updateMoneyHolder(moneyHolder: MoneyHolder) {
        viewModelScope.launch {
            repository.updateMoneyHolder(moneyHolder)
        }
    }

    private fun getMoneyHolders() {
        viewModelScope.launch {
            _moneyHoldersListFlow.value = repository.getMoneyHolders()
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