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
    private val _moneyHolderSavedFlow = MutableStateFlow<Unit?>(null)
    val moneyHolderSavedFlow: StateFlow<Unit?> = _moneyHolderSavedFlow

    private val _moneyHolderLiveData = MutableLiveData<List<MoneyHolder>>()
    val moneyHolderLiveData: LiveData<List<MoneyHolder>> get() = _moneyHolderLiveData

    fun addMoneyHolder(moneyHolder: MoneyHolder) {
        viewModelScope.launch {
            repository.addMoneyHolder(moneyHolder)
            _moneyHolderSavedFlow.value = Unit
        }
    }

     fun getMoneyHolders() {
        viewModelScope.launch {
            _moneyHolderLiveData.value = repository.getMoneyHolders()
        }
    }
}