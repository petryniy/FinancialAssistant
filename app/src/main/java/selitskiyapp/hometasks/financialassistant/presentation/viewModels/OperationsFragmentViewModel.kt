package selitskiyapp.hometasks.financialassistant.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation.findNavController
import kotlinx.coroutines.launch
import selitskiyapp.hometasks.financialassistant.R
import selitskiyapp.hometasks.financialassistant.domain.models.Operations
import selitskiyapp.hometasks.financialassistant.domain.repository.Repository
import selitskiyapp.hometasks.financialassistant.presentation.view.MainActivity

class OperationsFragmentViewModel(
    private val repository: Repository
) : ViewModel() {
    //    private val _idToEdit = MutableLiveData<Assets>
    private val _operationsLiveData = MutableLiveData<List<Operations>>()
    val operationsLiveData: LiveData<List<Operations>> get() = _operationsLiveData

    fun onItemClicked(id: Int) {
        toEditBottom(R.id.to_editOperationBottom)
        viewModelScope.launch {
//            repository.getOperationById(id)
        }

    }

    private fun toEditBottom(id: Int) {
        findNavController(MainActivity(), R.id.fragment_container).navigate(id)
    }

    fun saveOperation(operations: Operations) {
        viewModelScope.launch {
            repository.addOperations(operations)
        }
    }

    fun getAllOperations() {
        viewModelScope.launch {
            _operationsLiveData.value = repository.getOperations()
        }
    }

    fun deleteOperation(idOperation: Int) {
        viewModelScope.launch {
            repository.deleteOperations(idOperation)
        }
    }
}