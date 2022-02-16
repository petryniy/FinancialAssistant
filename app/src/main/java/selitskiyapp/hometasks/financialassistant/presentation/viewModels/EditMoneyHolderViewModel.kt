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
    private val _moneyHolderSaved = MutableStateFlow<Unit?>(null)
    val moneyHolderSaved: StateFlow<Unit?> = _moneyHolderSaved

    fun addMoneyHolder(moneyHolder: MoneyHolder) {
        viewModelScope.launch {
            repository.addMoneyHolder(moneyHolder)
            _moneyHolderSaved.value = Unit
        }
    }
}