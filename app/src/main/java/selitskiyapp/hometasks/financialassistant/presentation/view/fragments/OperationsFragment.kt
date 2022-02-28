package selitskiyapp.hometasks.financialassistant.presentation.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import selitskiyapp.hometasks.financialassistant.R
import selitskiyapp.hometasks.financialassistant.databinding.FragmentOperationsBinding
import selitskiyapp.hometasks.financialassistant.domain.models.BaseItem
import selitskiyapp.hometasks.financialassistant.domain.models.Filter
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

    private lateinit var filter: Filter
    private lateinit var listOperations: List<OperationWithMoneyHolder>
    private lateinit var listBaseItem: MutableList<BaseItem>


    private val itemClickListenerOperations: OperationsOnItemListener =
        object : OperationsOnItemListener {
            override fun onItemClickListener(id: Int) {
//                findNavController().navigate(
//                    R.id.moneyHolderFragment_to_editMoneyHolderBottom,
//                    bundleOf(MONEY_HOLDER_ID_FROM_HOLDER to id)
//                )
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAddButton()
    viewModel.viewModelScope.launch {
        initObservers()
    }

        initRecycler()
    }

    private suspend fun initObservers() = coroutineScope {
       launch {
            sharedViewModel.filter.collect { newFilter ->
                filter = newFilter
                viewModel.viewModelScope.launch {
                    viewModel.getFilteredOperationsListFlow(filter).collect {
                        listOperations = it.sortedByDescending { operationWithMoneyHolder ->
                            operationWithMoneyHolder.operationEntity.date
                        }
                        launch {
//                            listBaseItem.addAll(0, listOperations)
                            adapter.submitList(listOperations)
                    }
                }
            }
        }

       }




//        var listOperations = listOf(
//            OperationWithMoneyHolder(
//                operationEntity = OperationEntity(
//                id = 20,
//                category = "Животные",
//                moneyHolderId = 3,
//                value = 2500,
//                categoryImageId = 2,
//                date = "28.02.2023",
//                comment = "создал сам"
//            ),
//            MoneyHolderEntity(moneyId = 1, name = "3", type = 2, balance = 200000)
//            ),
//            HeadItem(date = "2020")
//        )
//
//        adapter.submitList(listOperations)


        viewModel.viewModelScope.launch {
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
            findNavController().navigate(R.id.operationsFragment_to_addOperationBottom)
        }
    }

    companion object {
        const val ID_FROM_OPERATIONS_FRAGMENT = "HOLDER"
        var operationsSumValue: Long = 0
    }
}

//    private fun initFloatingButtons() = with(binding) {
//        floatingPlus.setOnClickListener {
//            onClickAddButton()
//        }
//
////        floatingAddCredit.setOnClickListener {
////            findNavController().navigate(R.id.to_addDebitFragment)
////        }
//
//        floatingAddOperation.setOnClickListener {
//            findNavController().navigate(R.id.to_addOperationBottom)
//        }
//    }
//
//    private fun onClickAddButton() {
//        setVisibility(clicked)
//
//        setAnimation(clicked)
//
//        clicked = !clicked
//    }
//
//    private fun setAnimation(clicked: Boolean) = with(binding) {
//        if (!clicked) {
//            floatingAddOperation.startAnimation(fromBottom)
////            floatingAddCredit.startAnimation(fromBottom)
//            floatingPlus.startAnimation(rotateOpen)
//        } else {
//            floatingAddOperation.startAnimation(toBottom)
////            floatingAddCredit.startAnimation(toBottom)
//            floatingPlus.startAnimation(rotateClose)
//        }
//    }
//
//    private fun setVisibility(clicked: Boolean) {
//        if (!clicked) {
//            with(binding) {
//                floatingAddOperation.visibility = View.VISIBLE
////                floatingAddCredit.visibility = View.VISIBLE
//            }
//        } else {
//            with(binding) {
//                floatingAddOperation.visibility = View.GONE
////                floatingAddCredit.visibility = View.GONE
//            }
//        }
//    }
//
//    private var clicked = false
//    private val rotateOpen: Animation by lazy {
//        AnimationUtils.loadAnimation(
//            activity?.applicationContext,
//            R.anim.rotate_open_anim
//        )
//    }
//    private val rotateClose: Animation by lazy {
//        AnimationUtils.loadAnimation(
//            activity?.applicationContext,
//            R.anim.rotate_close_anim
//        )
//    }
//    private val fromBottom: Animation by lazy {
//        AnimationUtils.loadAnimation(
//            activity?.applicationContext,
//            R.anim.from_bottom_anim
//        )
//    }
//    private val toBottom: Animation by lazy {
//        AnimationUtils.loadAnimation(
//            activity?.applicationContext,
//            R.anim.to_bottom_anim
//        )

