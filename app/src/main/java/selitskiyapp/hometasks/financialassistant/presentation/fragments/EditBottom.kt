package selitskiyapp.hometasks.financialassistant.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import selitskiyapp.hometasks.financialassistant.databinding.BottomEditOperationBinding
import selitskiyapp.hometasks.financialassistant.presentation.viewModels.OperationsFragmentViewModel

class EditBottom(
    private var binding: BottomEditOperationBinding,
) : BottomSheetDialogFragment() {
    companion object {
        const val TAG = "EditBottom"
    }

    private val viewModel by viewModel<OperationsFragmentViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomEditOperationBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.editImageDelete.setOnClickListener { item ->
//            private val position = adapter.
//            viewModel.deleteItem()
//            adapter.notifyItemRemoved(position)

        }
    }
}