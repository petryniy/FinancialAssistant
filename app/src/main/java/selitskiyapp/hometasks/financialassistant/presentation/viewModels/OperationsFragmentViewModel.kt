package selitskiyapp.hometasks.financialassistant.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import selitskiyapp.hometasks.financialassistant.domain.models.Operation
import selitskiyapp.hometasks.financialassistant.domain.repository.OperationsRepository
import javax.inject.Inject

@HiltViewModel
class OperationsFragmentViewModel @Inject constructor(
    private val operationsRepository: OperationsRepository
) : ViewModel() {
    fun operationsListFlow() = operationsRepository.getOperations()


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