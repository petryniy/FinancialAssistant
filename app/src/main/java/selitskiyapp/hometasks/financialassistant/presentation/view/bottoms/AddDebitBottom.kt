package selitskiyapp.hometasks.financialassistant.presentation.view.bottoms

import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddDebitBottom : BottomSheetDialogFragment() {
//    companion object {
//        const val TAG = "BottomRadioGroupFragment"
//    }
//
//    private lateinit var binding: BottomAddDebitBinding
//    private val viewModel by viewModel<DebitCreditLendViewModel>()
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        binding = BottomAddDebitBinding.inflate(inflater)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        initSaveButton()
//
//    }
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