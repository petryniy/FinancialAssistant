package selitskiyapp.hometasks.financialassistant.presentation.view.bottoms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import selitskiyapp.hometasks.financialassistant.R
import selitskiyapp.hometasks.financialassistant.databinding.BottomEditMoneyHolderBinding
import selitskiyapp.hometasks.financialassistant.presentation.viewModels.EditMoneyHolderViewModel

@AndroidEntryPoint
class EditMoneyHolderBottom : BottomSheetDialogFragment() {
    private lateinit var binding: BottomEditMoneyHolderBinding
    private val viewModel: EditMoneyHolderViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomEditMoneyHolderBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = requireArguments().getInt("id")

        binding.run {
            imageViewBack.setOnClickListener {
                findNavController().navigate(R.id.editMoneyHolderBottom_to_moneyHolderFragment)
            }

            imageViewEdEdit.setOnClickListener {
                findNavController().navigate(R.id.editMoneyHolderBottom_to_addMoneyHolderBottom)
            }

            imageViewEdDelete.setOnClickListener {
                viewModel.deleteMoneyHolder(id = id)
                findNavController().navigate(R.id.editMoneyHolderBottom_to_moneyHolderFragment)
            }
        }
    }
}