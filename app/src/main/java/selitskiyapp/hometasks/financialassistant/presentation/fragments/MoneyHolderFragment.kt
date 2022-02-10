package selitskiyapp.hometasks.financialassistant.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import selitskiyapp.hometasks.financialassistant.R
import selitskiyapp.hometasks.financialassistant.databinding.FragmentMoneyHolderBinding

class MoneyHolderFragment : Fragment(R.layout.fragment_money_holder) {
    private val binding: FragmentMoneyHolderBinding by viewBinding(FragmentMoneyHolderBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}