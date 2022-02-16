package selitskiyapp.hometasks.financialassistant.presentation.view.bottoms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import selitskiyapp.hometasks.financialassistant.R
import selitskiyapp.hometasks.financialassistant.databinding.BottomSheetEditMoneyHolderBinding
import selitskiyapp.hometasks.financialassistant.domain.models.MoneyHolder
import selitskiyapp.hometasks.financialassistant.presentation.viewModels.EditMoneyHolderViewModel

@AndroidEntryPoint
class EditMoneyHolderBottomSheet : BottomSheetDialogFragment() {

    private val viewModelMoneyHolder: EditMoneyHolderViewModel by viewModels()
    private lateinit var binding: BottomSheetEditMoneyHolderBinding
    private var type: Int? = null


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
        binding.run {
            actvType.setAdapter(operationStatusAdapter)
            actvType.setOnItemClickListener { _, _, position, _ ->
                type = when (operationStatusAdapter.getItem(position)) {
                    "карты" -> MoneyHolderType.cash
                    "наличка" -> MoneyHolderType.nonCash
//                getString(R.string.msg_history_status_cancel) -> PaymentStatus.CANCELLED
                    else -> null
                }
            }
            tilName.editText?.doAfterTextChanged { tilName.error = null }
            button.setOnClickListener {
                if (!tilName.editText?.text.isNullOrEmpty()) {
                    viewModelMoneyHolder.addMoneyHolder(
                        MoneyHolder(
                            name = this.tilName.editText?.text.toString(),
                            type = type,
                            balance = 5000
                        )
                    )
                } else {
                    tilName.error = "Вы не ввели значение"
                }
                lifecycleScope.launchWhenResumed {
                    viewModelMoneyHolder.moneyHolderSaved.collect {
                        if (it != null) dismiss()
                    }
                }
            }
        }
    }
}

object MoneyHolderType {
    const val cash = 1
    const val nonCash = 2
}