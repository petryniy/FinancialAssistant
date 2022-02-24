package selitskiyapp.hometasks.financialassistant.presentation.view.bottoms

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import selitskiyapp.hometasks.financialassistant.databinding.BottomAddOperationBinding
import selitskiyapp.hometasks.financialassistant.domain.models.Operation
import selitskiyapp.hometasks.financialassistant.presentation.recyclers.moneyholder.MoneyHolderArrayAdapter
import selitskiyapp.hometasks.financialassistant.presentation.utils.AmountInputFilter
import selitskiyapp.hometasks.financialassistant.presentation.viewModels.MoneyHolderFragmentViewModel
import selitskiyapp.hometasks.financialassistant.presentation.viewModels.OperationsFragmentViewModel
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class AddOperationBottom : BottomSheetDialogFragment() {

    private lateinit var binding: BottomAddOperationBinding
    private val operationsViewModel: OperationsFragmentViewModel by viewModels()
    private val moneyHoldersFragmentViewModel: MoneyHolderFragmentViewModel by viewModels()

    private var category: String = "0"
    private var categoryImageId: Int = 0
    private var moneyHolderId: Int? = null
    private var result = false
    private var currentSelectedDate: Long? = null
    private var value: Long = 0


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomAddOperationBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initActvAddOperationAdapter()

        initTextFields()

        initCategory()

        initTypeItem()

        initSaveButton()

    }

    private fun initSaveButton() = with(binding) {
        buttonOperationSave.setOnClickListener {

            isFieldsEmpty()

            val operation = Operation(
                category = category,
                moneyHolderId = moneyHolderId!!,
                value = tilAddValue.editText?.text.toString().toFloat()
                    .let { (it * value).toLong() },
                categoryImageId = categoryImageId,
                date = tilAddDate.editText?.text.toString(),
                comment = tilAddComments.editText?.text.toString()
            )

            Log.d("nnjdnf!", operation.toString())

            Toast.makeText(requireContext(), operation.toString(), Toast.LENGTH_LONG)
                .show()

            operationsViewModel.addOperation(operation)

            dismiss()
        }
    }

    private fun isFieldsEmpty(): Boolean = with(binding) {

        when {
            tilAddValue.editText?.text.isNullOrEmpty() ->
                tilAddValue.error = "Вы не ввели значение"

            !initCategory() -> Toast.makeText(
                context,
                "Вы не выбрали категорию расхода!",
                Toast.LENGTH_LONG
            ).show()

            tilAddOperation.editText?.text.isNullOrEmpty() ->
                tilAddOperation.error = "Вы не выбрали счёт"

            tilAddDate.editText?.text.isNullOrEmpty() ->
                tilAddDate.error = "Вы не выбрали дату"
        }
        return true
    }


    private fun initTextFields() = with(binding) {
        tilAddValue.editText?.doAfterTextChanged { tilAddValue.error = null }
        tilAddOperation.editText?.doAfterTextChanged { tilAddOperation.error = null }
        tilAddDate.editText?.doAfterTextChanged { tilAddDate.error = null }
        tilAddValue.editText?.filters = arrayOf(AmountInputFilter())
        binding.tilAddDateClick.setOnClickListener { showDatePicker() }
    }

    private fun initCategory(): Boolean = with(binding) {
        chipGroupType.setOnCheckedChangeListener { group, checkedId ->

            when (checkedId) {
                chipCar.id -> {
                    category = "Машина"
                    categoryImageId = 1
                    result = true

                }
                chipProducts.id -> {
                    category = "Продукты"
                    categoryImageId = 2
                    result = true

                }
                chipPets.id -> {
                    category = "Животные"
                    categoryImageId = 3
                    result = true
                }
                chipChildren.id -> {
                    category = "Дети"
                    categoryImageId = 2
                    result = true
                }
                chipHouse.id -> {
                    category = "Дом"
                    categoryImageId = 2
                    result = true
                }
                chipRelax.id -> {
                    category = "Отдых"
                    categoryImageId = 2
                    result = true
                }
                else -> result = false
            }
        }
        return result
    }

    private fun initTypeItem() = with(binding) {
        chipGroupDC.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {

                chipCredit.id -> value = 1 * 100 * -1

                chipDebit.id -> value = 1 * 100

            }

        }
    }

    private fun showDatePicker() {
        val selectedDateInMillis = currentSelectedDate ?: System.currentTimeMillis()

        val dataPicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Выберете дату")
                .setSelection(selectedDateInMillis)
                .build()
                .apply {
                    addOnPositiveButtonClickListener { dateInMillis ->
                        onDateSelected(
                            dateInMillis
                        )
                    }
                }

        dataPicker.show(childFragmentManager, TAG)
    }

    private fun onDateSelected(date: Long?) {
        currentSelectedDate = date

        val dateTime: LocalDateTime = LocalDateTime.ofInstant(
            currentSelectedDate?.let { Instant.ofEpochMilli(it) }, ZoneId.systemDefault()
        )
        val dateAsFormattedText: String? =
            dateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
        binding.tilAddDate.editText?.setText(dateAsFormattedText)
    }

    private fun initActvAddOperationAdapter() {
        lifecycleScope.launchWhenResumed {
            moneyHoldersFragmentViewModel.getAllMoneyHoldersListFlow().collect { list ->
                val adapter = MoneyHolderArrayAdapter(requireContext(), list)
                binding.actvAddOperation.setAdapter(adapter)
                binding.actvAddOperation.setOnItemClickListener { _, _, position, _ ->
                    moneyHolderId = adapter.getItem(position)?.id
                }
            }
        }
    }

    companion object {
        const val TAG = "AddOperationBottom"
    }
}


