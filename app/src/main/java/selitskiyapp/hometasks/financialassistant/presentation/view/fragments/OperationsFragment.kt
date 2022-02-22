package selitskiyapp.hometasks.financialassistant.presentation.view.fragments

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import selitskiyapp.hometasks.financialassistant.R
import selitskiyapp.hometasks.financialassistant.databinding.FragmentMoneyHolderBinding
import selitskiyapp.hometasks.financialassistant.databinding.FragmentOperationsBinding
import selitskiyapp.hometasks.financialassistant.presentation.recyclers.moneyholder.MoneyHolderAdapter
import selitskiyapp.hometasks.financialassistant.presentation.recyclers.moneyholder.MoneyHolderOnItemListener
import selitskiyapp.hometasks.financialassistant.presentation.recyclers.operations.OperationsAdapter
import selitskiyapp.hometasks.financialassistant.presentation.recyclers.operations.OperationsOnItemListener
import selitskiyapp.hometasks.financialassistant.presentation.viewModels.EditMoneyHolderViewModel
import selitskiyapp.hometasks.financialassistant.presentation.viewModels.OperationsFragmentViewModel

@AndroidEntryPoint
class OperationsFragment : Fragment(R.layout.fragment_operations) {

    private val binding: FragmentOperationsBinding by viewBinding()
    private val adapter by lazy { OperationsAdapter(itemClickListenerOperations) }
    private val viewModel: OperationsFragmentViewModel by viewModels()

    private val itemClickListenerOperations: OperationsOnItemListener =
        object : OperationsOnItemListener {
            override fun onItemClickListener(id: Int) {
                findNavController().navigate(
                    R.id.moneyHolderFragment_to_editMoneyHolderBottom,
                    bundleOf(MONEY_HOLDER_ID_FROM_HOLDER to id)
                )
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAddButton()
    }

    override fun onResume() {
        super.onResume()

        initObservers()

        initRecycler()
    }

    private fun initObservers() {

        lifecycleScope.launchWhenResumed {
            viewModel.operationsListFlow().collect {
                adapter.submitList(it)
            }
        }
    }

    private fun initRecycler() = with(binding) {
        recyclerOperations.adapter = adapter
        recyclerOperations.layoutManager = LinearLayoutManager(context)
    }

    private fun initAddButton() = with(binding) {
        fab.setOnClickListener {
            findNavController().navigate(R.id.to_addOperationBottom)
        }
    }
    companion object {
        const val MONEY_HOLDER_ID_FROM_HOLDER = "HOLDER"
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

