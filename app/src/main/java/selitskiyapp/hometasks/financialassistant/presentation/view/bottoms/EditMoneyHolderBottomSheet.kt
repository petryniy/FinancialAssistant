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
import selitskiyapp.hometasks.financialassistant.databinding.BottomEditMoneyHolderBinding
import selitskiyapp.hometasks.financialassistant.domain.models.MoneyHolder
import selitskiyapp.hometasks.financialassistant.presentation.viewModels.EditMoneyHolderViewModel

@AndroidEntryPoint
class EditMoneyHolderBottomSheet : BottomSheetDialogFragment() {

    private val viewModelMoneyHolder: EditMoneyHolderViewModel by viewModels()
    private lateinit var binding: BottomEditMoneyHolderBinding
    private var type: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomEditMoneyHolderBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTypeAdapter()

        initSaveButton()
    }

    private fun initSaveButton() = with(binding) {
        fieldsIsEmpty()

        button.setOnClickListener {
            when {
                tilName.editText?.text.isNullOrEmpty() -> tilName.error = "Вы не ввели значение"
                tilType.editText?.text.isNullOrEmpty() -> tilType.error = "Вы не выбрали тип"
                tilBalance.editText?.text.isNullOrEmpty() -> tilBalance.error =
                    "Вы не ввели текущий баланс"
                else -> {
                    viewModelMoneyHolder.addMoneyHolder(
                        MoneyHolder(
                            name = tilName.editText?.text.toString(),
                            type = type,
                            balance = tilBalance.editText?.text.toString().toLong()
                        )
                    )
                }
            }
            lifecycleScope.launchWhenResumed {
                viewModelMoneyHolder.moneyHolderSaved.collect {
                    if (it != null) dismiss()
                }
            }
        }
    }

    private fun fieldsIsEmpty() = with(binding) {
        tilName.editText?.doAfterTextChanged { tilName.error = null }
        tilType.editText?.doAfterTextChanged { tilType.error = null }
        tilBalance.editText?.doAfterTextChanged { tilBalance.error = null }
    }

    private fun initTypeAdapter() = with(binding) {
        actvType.setAdapter(operationStatusAdapter)
        actvType.setOnItemClickListener { _, _, position, _ ->
            type = when (operationStatusAdapter.getItem(position)) {
                getString(R.string.typeCash) -> CASH
                getString(R.string.typeNonCash) -> NON_CASH
                else -> null
            }
        }
    }

    private val operationStatusAdapter: ArrayAdapter<String> by lazy {
        ArrayAdapter(
            requireContext(),
            R.layout.item_text_adapter,
            listOf(
                getString(R.string.typeCash),
                getString(R.string.typeNonCash)
            )
        )
    }

    companion object {
        const val CASH = 1
        const val NON_CASH = 2
    }
}
