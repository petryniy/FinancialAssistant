package selitskiyapp.hometasks.financialassistant.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import selitskiyapp.hometasks.financialassistant.domain.models.MoneyHolder
import selitskiyapp.hometasks.financialassistant.domain.repository.MoneyHoldersRepository
import javax.inject.Inject

@HiltViewModel
class MoneyHolderFragmentViewModel @Inject constructor(
    private val moneyHoldersRepository: MoneyHoldersRepository,
) : ViewModel() {

    private val _moneyHolder = MutableStateFlow(MoneyHolder(0, "0",0,0))
    val moneyHolder: StateFlow<MoneyHolder> get() = _moneyHolder

    val moneyHoldersSumBalance = moneyHoldersRepository.getMoneyHoldersSumBalance()

    fun getAllMoneyHoldersListFlow() = moneyHoldersRepository.getMoneyHolders()

    fun getMoneyHolderById(id: Int) {
        viewModelScope.launch {
            _moneyHolder.value =  moneyHoldersRepository.getMoneyHolderById(id)
        }
    }

    fun addMoneyHolder(moneyHolder: MoneyHolder) {
        viewModelScope.launch {
            moneyHoldersRepository.addMoneyHolder(moneyHolder)
        }
    }

    fun updateMoneyHolder(moneyHolder: MoneyHolder) {
        viewModelScope.launch {
            moneyHoldersRepository.updateMoneyHolder(moneyHolder)
        }
    }

    fun deleteMoneyHolder(id: Int) {
        viewModelScope.launch {
            moneyHoldersRepository.deleteMoneyHolder(id)
        }
    }
}