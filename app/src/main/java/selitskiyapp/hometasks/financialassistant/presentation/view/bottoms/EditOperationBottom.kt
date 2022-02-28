package selitskiyapp.hometasks.financialassistant.presentation.view.bottoms

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import selitskiyapp.hometasks.financialassistant.R
import selitskiyapp.hometasks.financialassistant.databinding.BottomEditOperationBinding
import selitskiyapp.hometasks.financialassistant.presentation.view.fragments.OperationsFragment
import selitskiyapp.hometasks.financialassistant.presentation.viewModels.OperationsFragmentViewModel

@AndroidEntryPoint
class EditOperationBottom : BottomSheetDialogFragment() {

    private lateinit var binding: BottomEditOperationBinding
    private val viewModel: OperationsFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomEditOperationBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id: Int = requireArguments().getInt(OperationsFragment.ID_FROM_OPERATIONS_FRAGMENT)

//        initFields(id)
    }

    private fun initFields(id: Int?) {
        if (id != null && id != 0) {
            viewModel.getOperationById(id)

            lifecycleScope.launchWhenResumed {
                viewModel.operation.collect { it ->
                    binding.run {
//                        editImage.setImageResource(
//                            when (moneyHolder.type) {
//                                1 -> R.drawable.ic_credit_card
//                                2 -> R.drawable.ic_cash
//                                else -> R.drawable.ic_add
//                            }
//                        )
                    }
                }
            }
        }
    }

    companion object {
        const val TAG = "EditBottom"
    }
}
