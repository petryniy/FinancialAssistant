package selitskiyapp.hometasks.financialassistant.presentation.view.bottoms

import android.os.Bundle
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
import selitskiyapp.hometasks.financialassistant.R
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

        val operationId: Int = requireArguments().getInt(EditOperationBottom.OPERATION_ID_FROM_EDIT)

        putFieldsFromOperation(operationId)
    }

    private fun putFieldsFromOperation(operationId: Int?) {

        if (operationId != null && operationId != 0) {

            operationsViewModel.getOperationById(operationId)

            lifecycleScope.launchWhenResumed {
                operationsViewModel.operation.collect {
                    if (it != null) {

                        val item = it.operationEntity

                        binding.run {

                            if (item.value > 0) {
                                chipGroupDC.check(chipCredit.id)
                            } else {
                                chipGroupDC.check(chipDebit.id)
                            }

                            tilAddValue.editText?.setText(
                                root.context
                                    .getString(
                                        R.string.msg_currency_byn_amount_format,
                                        item.value.div(100f)
                                    )
                            )

                            chipGroupType.check(
                                when (item.category) {
                                    "Машина" -> chipCar.id

                                    "Продукты" -> chipProducts.id

                                    "Животные" -> chipPets.id

                                    "Дети" -> chipChildren.id

                                    "Дом" -> chipHouse.id

                                    "Отдых" -> chipRelax.id

                                    else -> {
                                        0
                                    }
                                }
                            )

                            tilAddDate.editText?.setText(item.date)

                            tilAddComments.editText?.setText(item.comment)
                        }
                    }
                }
            }
        }
    }

    private fun initSaveButton() = with(binding) {
        buttonOperationSave.setOnClickListener {
            when {
                !initTypeItem() -> Toast.makeText(
                    context,
                    "Вы не выбрали расход/доход!",
                    Toast.LENGTH_LONG
                ).show()

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

                else -> {
                    addOperation()

                    dismiss()
                }
            }
        }
    }

    private fun addOperation() = with(binding) {
        val operation = moneyHolderId?.let { it1 ->
            Operation(
                category = category,
                moneyHolderId = it1,
                value = tilAddValue.editText?.text.toString().toFloat()
                    .let { (it * value).toLong() },
                categoryDrawable = categoryDrawable,
                date = tilAddDate.editText?.text.toString(),
                comment = tilAddComments.editText?.text.toString()
            )
        }

        Toast.makeText(requireContext(), "Операция добавлена!", Toast.LENGTH_SHORT)
            .show()

        if (operation != null) {
            operationsViewModel.addOperation(operation)
        }
    }

    private fun isFieldsEmpty(): Boolean = with(binding) {
        when {
            !initTypeItem() -> Toast.makeText(
                context,
                "Вы не выбрали расход/доход!",
                Toast.LENGTH_LONG
            ).show()

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
        chipGroupType.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                chipCar.id -> {
                    category = "Машина"
                    categoryDrawable = R.drawable.ic_car
                    initCategoryBoolean = true
                }

                chipProducts.id -> {
                    category = "Продукты"
                    categoryDrawable = R.drawable.ic_products
                    initCategoryBoolean = true
                }

                chipPets.id -> {
                    category = "Животные"
                    categoryDrawable = R.drawable.ic_pets
                    initCategoryBoolean = true
                }

                chipChildren.id -> {
                    category = "Дети"
                    categoryDrawable = R.drawable.ic_child
                    initCategoryBoolean = true
                }

                chipHouse.id -> {
                    category = "Дом"
                    categoryDrawable = R.drawable.ic_house
                    initCategoryBoolean = true
                }

                chipRelax.id -> {
                    category = "Отдых"
                    categoryDrawable = R.drawable.ic_coffee
                    initCategoryBoolean = true
                }
                else -> initCategoryBoolean = false
            }
        }
        return initCategoryBoolean
    }

    private fun initTypeItem(): Boolean = with(binding) {
        chipGroupDC.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {

                chipCredit.id -> {
                    value = 1 * 100 * -1
                    initTypeItemBoolean = true
                }

                chipDebit.id -> {
                    value = 1 * 100
                    initTypeItemBoolean = true
                }

                else -> initTypeItemBoolean = false
            }
        }
        return initTypeItemBoolean
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
                binding.run {
                    actvAddOperation.setAdapter(adapter)
                    actvAddOperation.setOnItemClickListener { _, _, position, _ ->
                        moneyHolderId = adapter.getItem(position)?.id
                    }
                }
            }
        }
    }

    companion object {
        const val TAG = "AddOperationBottom"
        private var category: String = "0"
        private var categoryDrawable: Int = 0
        private var moneyHolderId: Int? = null
        private var initCategoryBoolean = false
        private var initTypeItemBoolean = false
        private var currentSelectedDate: Long? = null
        private var value: Long = 0
    }
}


