package selitskiyapp.hometasks.financialassistant.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import selitskiyapp.hometasks.financialassistant.domain.models.Filter
import javax.inject.Inject

@HiltViewModel
class FilterSharedViewModel @Inject constructor() : ViewModel() {
    private val _filter = MutableStateFlow<Filter>(Filter.EmptyFilter)
    val filter: StateFlow<Filter> get() = _filter

    fun setFilter(filter: Filter) {
        _filter.value = filter
    }
}