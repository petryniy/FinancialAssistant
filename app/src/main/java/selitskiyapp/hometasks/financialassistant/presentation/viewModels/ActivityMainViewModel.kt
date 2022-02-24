package selitskiyapp.hometasks.financialassistant.presentation.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import selitskiyapp.hometasks.financialassistant.domain.models.MoneyHolder
import selitskiyapp.hometasks.financialassistant.presentation.view.fragments.MoneyHolderFragment
import selitskiyapp.hometasks.financialassistant.presentation.view.fragments.OperationsFragment
import javax.inject.Inject

@HiltViewModel
class ActivityMainViewModel @Inject constructor(): ViewModel() {

    private val _balance = MutableStateFlow(0L)
    val balance: StateFlow<Long?> get() = _balance

    init {
        getBalance()
    }

    private fun getBalance() {
        viewModelScope.launch {
            _balance.value = MoneyHolderFragment.moneyHoldersSumBalance +
                    OperationsFragment.operationsSumValue
            Log.d("ActivityMainViewModelbalance", "initBalance ${_balance.value}")

        }
    }

//    binding.textViewBalanceValue.text = getString(
//    R.string.msg_currency_byn_amount_format,
//    (MoneyHolderFragment.moneyHoldersSumBalance +
//    OperationsFragment.operationsSumValue) / 100f
}