package selitskiyapp.hometasks.financialassistant.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import selitskiyapp.hometasks.financialassistant.domain.models.Filter
import selitskiyapp.hometasks.financialassistant.domain.models.Operation
import selitskiyapp.hometasks.financialassistant.domain.repository.OperationsRepository
import javax.inject.Inject

@HiltViewModel
class OperationsFragmentViewModel @Inject constructor(
    private val operationsRepository: OperationsRepository

    ): ViewModel() {

    suspend fun getFilteredOperationsListFlow(filter: Filter) = operationsRepository.getFilteredOperationsListFlow(filter)

    val operationsSumValue = operationsRepository.getOperationsSumValue()

    fun getOperationById(id: Int) {
        viewModelScope.launch {
//            _operation.value = operationsRepository.getOperationById(id)
        }
    }

    fun addOperation(operation: Operation) {
        viewModelScope.launch {
            operationsRepository.addOperation(operation)

        }
    }

    fun updateOperation(operation: Operation) {
        viewModelScope.launch {
            operationsRepository.updateOperation(operation)
        }
    }

    fun deleteOperation(id: Int) {
        viewModelScope.launch {
            operationsRepository.deleteOperation(id)
        }
    }
}