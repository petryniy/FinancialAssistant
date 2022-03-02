package selitskiyapp.hometasks.financialassistant.presentation.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import selitskiyapp.hometasks.financialassistant.R
import selitskiyapp.hometasks.financialassistant.databinding.FragmentOperationsBinding
import selitskiyapp.hometasks.financialassistant.domain.models.Filter
import selitskiyapp.hometasks.financialassistant.domain.models.OperationWithMoneyHolder
import selitskiyapp.hometasks.financialassistant.presentation.recyclers.operations.OperationsAdapter
import selitskiyapp.hometasks.financialassistant.presentation.recyclers.operations.OperationsOnItemListener
import selitskiyapp.hometasks.financialassistant.presentation.viewModels.FilterSharedViewModel
import selitskiyapp.hometasks.financialassistant.presentation.viewModels.OperationsFragmentViewModel

@AndroidEntryPoint
class OperationsFragment : Fragment(R.layout.fragment_operations) {

    private val binding: FragmentOperationsBinding by viewBinding()
    private val adapter by lazy { OperationsAdapter(itemClickListenerOperations) }
    private val viewModel: OperationsFragmentViewModel by viewModels()
    private val sharedViewModel: FilterSharedViewModel by activityViewModels()

    private val itemClickListenerOperations: OperationsOnItemListener =
        object : OperationsOnItemListener {

            override fun onItemClickListener(id: Int) {

                Log.d("myDebug", "ID_FROM_OPERATIONS_FRAGMENT $id")

                findNavController().navigate(
                    R.id.operationsFragment_to_editOperationBottom,
                    bundleOf(ID_FROM_OPERATIONS_FRAGMENT to id)
                )

            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAddButton()

        initObservers()

        initRecycler()
    }

    private fun initObservers() {
        lifecycleScope.launchWhenResumed {
            sharedViewModel.filter.collect { newFilter ->
                viewModel.viewModelScope.launch {
                    viewModel.getFilteredOperationsListFlow(newFilter).collect {
                       listOperations = it.sortedByDescending { operationWithMoneyHolder ->
                           operationWithMoneyHolder?.operationEntity?.date
                        }
                        adapter.submitList(listOperations)
                    }
                }
            }
        }

        lifecycleScope.launchWhenResumed {
            viewModel.operationsSumValue.collect {
                if (it != null) {
                    operationsSumValue = it
                }
                Log.d("myDebug", "operationsSumValue $operationsSumValue")
            }
        }
    }

    private fun initRecycler() = with(binding) {
        recyclerOperations.adapter = adapter
        recyclerOperations.layoutManager = LinearLayoutManager(context)
    }

    private fun initAddButton() = with(binding) {
        fabOperation.setOnClickListener {
            val action = OperationsFragmentDirections.operationsFragmentToAddOperationBottom()
            findNavController().navigate(action)
        }
    }

    companion object {
        const val ID_FROM_OPERATIONS_FRAGMENT = "OPERATIONS_FRAGMENT"
        var operationsSumValue: Long = 0
        private var listOperations: List<OperationWithMoneyHolder?> = emptyList()
    }
}

