package selitskiyapp.hometasks.financialassistant.presentation.fragments

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import selitskiyapp.hometasks.financialassistant.R
import selitskiyapp.hometasks.financialassistant.databinding.FragmentDebitCreditBinding
import selitskiyapp.hometasks.financialassistant.presentation.recycler.DebitCreditLendAdapter
import selitskiyapp.hometasks.financialassistant.presentation.recycler.OnItemListener
import selitskiyapp.hometasks.financialassistant.presentation.viewModels.DebitCreditLendViewModel

class DebitCreditFragment : Fragment(R.layout.fragment_debit_credit) {

    private val binding: FragmentDebitCreditBinding by viewBinding(FragmentDebitCreditBinding::bind)
    private val adapter by lazy { DebitCreditLendAdapter(itemClickListener) }
    private val viewModel by viewModel<DebitCreditLendViewModel>()

    private val itemClickListener : OnItemListener = object : OnItemListener {
        override fun onItemClickListener(position: Int) {
            viewModel.onItemClicked(position)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()

        initRecycler()

        initFloatingButtons()

        viewModel.getDebit()
    }

    private fun initObservers() {
        viewModel.debitLiveData.observe(viewLifecycleOwner) { positiveAssets ->
            adapter.submitList(positiveAssets)
        }
    }

    private fun initRecycler() {
        binding.apply {
            recycler.adapter = adapter
            recycler.layoutManager = LinearLayoutManager(context)
        }
    }

    private fun initFloatingButtons() = with(binding) {
        floatingPlus.setOnClickListener {
            onClickAddButton()
        }

        floatingAddCredit.setOnClickListener {
            findNavController().navigate(R.id.to_addDebitFragment)
        }

        floatingAddDebit.setOnClickListener {
            findNavController().navigate(R.id.to_addDebitFragment)
        }
    }

    private fun onClickAddButton() {
        setVisibility(clicked)

        setAnimation(clicked)

        clicked = !clicked
    }

    private fun setAnimation(clicked: Boolean) = with(binding) {
        if (!clicked) {
            floatingAddDebit.startAnimation(fromBottom)
            floatingAddCredit.startAnimation(fromBottom)
            floatingPlus.startAnimation(rotateOpen)
        } else {
            floatingAddDebit.startAnimation(toBottom)
            floatingAddCredit.startAnimation(toBottom)
            floatingPlus.startAnimation(rotateClose)
        }
    }

    private fun setVisibility(clicked: Boolean) {
        if (!clicked) {
            with(binding) {
                floatingAddDebit.visibility = View.VISIBLE
                floatingAddCredit.visibility = View.VISIBLE
            }
        } else {
            with(binding) {
                floatingAddDebit.visibility = View.GONE
                floatingAddCredit.visibility = View.GONE
            }
        }
    }

    private var clicked = false
    private val rotateOpen: Animation by lazy {
        AnimationUtils.loadAnimation(
            activity?.applicationContext,
            R.anim.rotate_open_anim
        )
    }
    private val rotateClose: Animation by lazy {
        AnimationUtils.loadAnimation(
            activity?.applicationContext,
            R.anim.rotate_close_anim
        )
    }
    private val fromBottom: Animation by lazy {
        AnimationUtils.loadAnimation(
            activity?.applicationContext,
            R.anim.from_bottom_anim
        )
    }
    private val toBottom: Animation by lazy {
        AnimationUtils.loadAnimation(
            activity?.applicationContext,
            R.anim.to_bottom_anim
        )
    }
}