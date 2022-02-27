package selitskiyapp.hometasks.financialassistant.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import selitskiyapp.hometasks.financialassistant.domain.models.Filter
import javax.inject.Inject

@HiltViewModel
class FilterSharedViewModel @Inject constructor() : ViewModel() {
    private val _filter = MutableLiveData<Filter>()
    val filter: LiveData<Filter> get() = _filter

    fun setFilter(filter: Filter) {
        _filter.value = filter
    }
}