package selitskiyapp.hometasks.financialassistant.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import selitskiyapp.hometasks.financialassistant.data.storage.models.MoneyHolderEntity
import selitskiyapp.hometasks.financialassistant.data.storage.models.OperationEntity
import selitskiyapp.hometasks.financialassistant.domain.models.Filter
import selitskiyapp.hometasks.financialassistant.domain.models.Operation
import selitskiyapp.hometasks.financialassistant.domain.models.OperationWithMoneyHolder
import selitskiyapp.hometasks.financialassistant.domain.repository.OperationsRepository
import javax.inject.Inject

@HiltViewModel
class OperationsFragmentViewModel @Inject constructor(

    private val operationsRepository: OperationsRepository,

    ) : ViewModel() {

    private val _operation = MutableStateFlow(OperationWithMoneyHolder(
        OperationEntity(
            0,"0",0, 0,0,"0","0"
        ),
    MoneyHolderEntity(0,"0", 0, 0)
    ))
    val operation: StateFlow<OperationWithMoneyHolder> get() = _operation

    fun getFilteredOperationsListFlow(filter: Filter): Flow<List<OperationWithMoneyHolder>> {

        getAllOperationsListFlow()

        return when (filter) {
            is Filter.EmptyFilter -> getAllOperationsListFlow()

            is Filter.DateFilter -> getAllOperationsListFlow().map {
                it.filter { operationWithMoneyHolder ->
                    operationWithMoneyHolder.operationEntity.date.contains(filter.date)
                }
            }

            is Filter.CategoryFilter -> getAllOperationsListFlow().map {
                it.filter { operationWithMoneyHolder ->
                    operationWithMoneyHolder.operationEntity.category
                        .lowercase()
                        .contains(filter.category.lowercase())
                }
            }

            is Filter.MoneyHolderFilter -> getAllOperationsListFlow().map {
                it.filter { operationWithMoneyHolder ->
                    operationWithMoneyHolder.operationEntity.moneyHolderId == filter.id
                }
            }
        }
    }

    private fun getAllOperationsListFlow() = operationsRepository.getAllOperations()

    val operationsSumValue = operationsRepository.getOperationsSumValue()

    fun getOperationById(id: Int) {
        viewModelScope.launch {
             operationsRepository.getOperationById(id).collect{
                 _operation.value = it
             }
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