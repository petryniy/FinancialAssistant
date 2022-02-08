package selitskiyapp.hometasks.financialassistant.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import selitskiyapp.hometasks.financialassistant.R
import selitskiyapp.hometasks.financialassistant.databinding.BottomEditBinding
import selitskiyapp.hometasks.financialassistant.domain.usecases.DeleteItem
import selitskiyapp.hometasks.financialassistant.presentation.recycler.DebitCreditLendAdapter
import selitskiyapp.hometasks.financialassistant.presentation.viewModels.DebitCreditLendViewModel

class EditBottom(
    private var binding: BottomEditBinding,
) : BottomSheetDialogFragment() {
    companion object {
        const val TAG = "EditBottom"
    }

    private val viewModel by viewModel<DebitCreditLendViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomEditBinding.inflate(inflater)
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