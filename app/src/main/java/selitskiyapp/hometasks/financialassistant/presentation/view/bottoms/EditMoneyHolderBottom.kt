package selitskiyapp.hometasks.financialassistant.presentation.view.bottoms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import selitskiyapp.hometasks.financialassistant.R
import selitskiyapp.hometasks.financialassistant.databinding.BottomEditMoneyHolderBinding
import selitskiyapp.hometasks.financialassistant.presentation.view.fragments.MoneyHolderFragment
import selitskiyapp.hometasks.financialassistant.presentation.viewModels.EditMoneyHolderViewModel

@AndroidEntryPoint
class EditMoneyHolderBottom : BottomSheetDialogFragment() {
    private lateinit var binding: BottomEditMoneyHolderBinding
    private val viewModel: EditMoneyHolderViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomEditMoneyHolderBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id: Int = requireArguments().getInt(MoneyHolderFragment.MONEY_HOLDER_ID_FROM_HOLDER)
        Toast.makeText(
            context, "MONEY_HOLDER_ID_FROM_HOLDER $id",
            Toast.LENGTH_LONG
        ).show()

        initClickOnIcon(id)

        initFields(id)
    }

    private fun initFields(id: Int?) {
        if (id != null && id !== 0) {
            viewModel.getMoneyHolderById(id)
            viewModel.moneyHolder.observe(viewLifecycleOwner) { moneyHolder ->
                binding.run {

                    when (moneyHolder.type) {
                        1 -> imageViewEdType.setImageResource(R.drawable.ic_credit_card)
                        2 -> imageViewEdType.setImageResource(R.drawable.ic_cash)
                    }

                    textEdViewName.text = moneyHolder.name
                    textViewEdBalance.text = moneyHolder.balance.toInt().toString()
                }
            }
        }
    }

    private fun initClickOnIcon(moneyHolderId: Int) = with(binding) {
        imageViewBack.setOnClickListener {
            findNavController().navigate(R.id.editMoneyHolderBottom_to_moneyHolderFragment)
        }

        imageViewEdEdit.setOnClickListener {
            findNavController().navigate(
                R.id.editMoneyHolderBottom_to_addMoneyHolderBottom,
                bundleOf(MONEY_HOLDER_ID_FROM_EDIT to moneyHolderId)
            )
        }

        imageViewEdDelete.setOnClickListener {
            viewModel.deleteMoneyHolder(id = moneyHolderId)
            findNavController().navigate(R.id.editMoneyHolderBottom_to_moneyHolderFragment)
        }
    }

    companion object {
        const val MONEY_HOLDER_ID_FROM_EDIT = "EDIT"
    }
}

