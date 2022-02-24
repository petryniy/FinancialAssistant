package selitskiyapp.hometasks.financialassistant.presentation.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import by.kirich1409.viewbindingdelegate.internal.findRootView
import kotlinx.coroutines.launch
import selitskiyapp.hometasks.financialassistant.R
import selitskiyapp.hometasks.financialassistant.presentation.view.MainActivity
import selitskiyapp.hometasks.financialassistant.presentation.viewModels.ActivityMainViewModel

class AnalyticFragment: Fragment() {

    private val viewModel: ActivityMainViewModel by viewModels()

    private var balance: String = "0"



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_analytic, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBalance()

    }

    private fun initBalance() {

        viewModel.viewModelScope.launch {
            viewModel.balance.collect {
                if (it != null) {
//                    balance = it.toString()
                }
            }
        }
        Log.d("balance", "initBalance $balance")

//        findRootView(MainActivity()).findViewById<TextView>(R.id.textViewBalanceValue).text = balance


//            getString(
//            R.string.msg_currency_byn_amount_format, balance / 100f)
    }
}