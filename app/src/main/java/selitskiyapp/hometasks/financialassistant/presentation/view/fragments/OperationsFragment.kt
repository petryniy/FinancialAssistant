package selitskiyapp.hometasks.financialassistant.presentation.view.fragments

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.zip
import selitskiyapp.hometasks.financialassistant.R
import selitskiyapp.hometasks.financialassistant.databinding.FragmentOperationsBinding
import selitskiyapp.hometasks.financialassistant.domain.models.BaseItem
import selitskiyapp.hometasks.financialassistant.domain.models.HeadItem
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

                findNavController().navigate(
                    R.id.operationsFragment_to_editOperationBottom,
                    bundleOf(ID_FROM_OPERATIONS_FRAGMENT to id)
                )

            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAddButton()

        initRecycler()

        initObservers()

//        initRecyclerAdapter(listOperations)

    }

    private fun initRecyclerAdapter(listOperations: List<OperationWithMoneyHolder?>) {
        listBaseItem = listOperations.toMutableList()
        listBaseItem.add(0, HeadItem("22.02.2022"))
        adapter.submitList(listBaseItem)
    }

    private fun initObservers() {
        lifecycleScope.launchWhenResumed {
            sharedViewModel.filter.collect { newFilter ->
                viewModel.getFilteredOperationsListFlow(newFilter).collect {
                    listOperations = it.sortedByDescending { operationWithMoneyHolder ->
                        operationWithMoneyHolder?.operationEntity?.date
                    }
                    initRecyclerAdapter(listOperations)
                }
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
        private var listOperations: List<OperationWithMoneyHolder?> = emptyList()
        private var listBaseItem: MutableList<BaseItem?> = emptyList<BaseItem>().toMutableList()
//        = listOf(OperationWithMoneyHolder(
//            OperationEntity(3, "2", 3, 4, 2131165310, "6", "7"),
//        MoneyHolderEntity(1,"2", 2131165294,4)
//        ))
    }
}

