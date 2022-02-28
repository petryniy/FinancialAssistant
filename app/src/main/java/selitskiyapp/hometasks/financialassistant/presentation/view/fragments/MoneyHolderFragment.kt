package selitskiyapp.hometasks.financialassistant.presentation.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import selitskiyapp.hometasks.financialassistant.R
import selitskiyapp.hometasks.financialassistant.databinding.FragmentMoneyHolderBinding
import selitskiyapp.hometasks.financialassistant.presentation.recyclers.moneyholder.MoneyHolderAdapter
import selitskiyapp.hometasks.financialassistant.presentation.recyclers.moneyholder.MoneyHolderOnItemListener
import selitskiyapp.hometasks.financialassistant.presentation.viewModels.MoneyHolderFragmentViewModel

@AndroidEntryPoint
class MoneyHolderFragment : Fragment(R.layout.fragment_money_holder) {
    private val binding: FragmentMoneyHolderBinding by viewBinding()
    private val adapter by lazy { MoneyHolderAdapter(itemClickListenerMoneyHolder) }
    private val viewModel: MoneyHolderFragmentViewModel by viewModels()

    private val itemClickListenerMoneyHolder: MoneyHolderOnItemListener =
        object : MoneyHolderOnItemListener {

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

        initObservers()

        initRecycler()
    }

    private fun initObservers() {

        viewModel.viewModelScope.launch {
            viewModel.getAllMoneyHoldersListFlow().collect {
                adapter.submitList(it)
            }
        }

        viewModel.viewModelScope.launch {
            viewModel.moneyHoldersSumBalance.collect {
                if (it != null) {
                    moneyHoldersSumBalance = it
                }
                Log.d("myDebug", "moneyHoldersSumBalance $moneyHoldersSumBalance")
            }
        }
    }

    private fun initRecycler() = with(binding) {
        recyclerMoneyHolder.adapter = adapter
        recyclerMoneyHolder.layoutManager = LinearLayoutManager(context)

    }

    private fun initAddButton() = with(binding) {
        fabMoneyHolder.setOnClickListener {
            val action =
                MoneyHolderFragmentDirections.moneyHolderFragmentToAddMoneyHolderBottom()
            findNavController().navigate(action)

        }
    }
    companion object {
        const val MONEY_HOLDER_ID_FROM_HOLDER = "HOLDER"
        var moneyHoldersSumBalance: Long = 0
    }
}