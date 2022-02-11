package selitskiyapp.hometasks.financialassistant.presentation.view.bottoms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import selitskiyapp.hometasks.financialassistant.R
import selitskiyapp.hometasks.financialassistant.databinding.BottomSheetEditMoneyHolderBinding

class EditMoneyHolderBottomSheet : BottomSheetDialogFragment() {

    private var type: Int? = null
    private lateinit var binding: BottomSheetEditMoneyHolderBinding

    private val operationStatusAdapter: ArrayAdapter<String> by lazy {
        ArrayAdapter(
            requireContext(),
            R.layout.item_money_holder,
            listOf(
                "карты",
                "наличка"
//                getString(R.string.msg_status_complete),
//                getString(R.string.msg_history_status_pending),
//                getString(R.string.msg_history_status_cancel)
            )
        )
    }

    //    private val viewModel by viewModel<DebitCreditLendViewModel>()
//
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetEditMoneyHolderBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.actvType.setAdapter(operationStatusAdapter)
        binding.actvType.setOnItemClickListener { _, _, position, _ ->
            type = when (operationStatusAdapter.getItem(position)) {
                "карты" -> MoneyHolderType.cash
                "наличка" -> 2
//                getString(R.string.msg_history_status_cancel) -> PaymentStatus.CANCELLED
                else -> null
            }
        }
        binding.tilName.editText?.text.toString()
    }
//
//    private fun initSaveButton() = with(binding) {
//        buttonDebitSave.setOnClickListener {
//           val positiveAssets =  PositiveAssets(
//                value = setResult(debitEditValue).toLong(),
//                imageId = setResult(debitEditImageId),
//                category = setResult(debitEditCategory),
//                date = setResult(debitEditDate),
//                typeOfValue = setResult(debitEditTypeOfValue),
//                comment = setResult(debitEditComment)
//            )
//            viewModel.addDebit(positiveAssets)
//            dismiss()
//        }
//    }
//
//    private fun setResult(editText: EditText): String {
//        val result = editText.doAfterTextChanged { text ->
//            text.toString()
//        }
//        return result.toString()
//    }
}

object MoneyHolderType{
    const val cash = 1
}