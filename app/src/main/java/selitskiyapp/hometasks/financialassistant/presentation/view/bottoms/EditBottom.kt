package selitskiyapp.hometasks.financialassistant.presentation.view.bottoms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import selitskiyapp.hometasks.financialassistant.databinding.BottomEditOperationBinding

class EditBottom(
    private var binding: BottomEditOperationBinding,
) : BottomSheetDialogFragment() {
    companion object {
        const val TAG = "EditBottom"
    }

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