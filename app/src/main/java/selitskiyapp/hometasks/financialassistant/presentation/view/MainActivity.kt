package selitskiyapp.hometasks.financialassistant.presentation.view

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import selitskiyapp.hometasks.financialassistant.R
import selitskiyapp.hometasks.financialassistant.databinding.ActivityMainBinding
import selitskiyapp.hometasks.financialassistant.presentation.view.fragments.OperationsFragment
import selitskiyapp.hometasks.financialassistant.presentation.viewModels.ActivityMainViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding: ActivityMainBinding by viewBinding()
    private val viewModel: ActivityMainViewModel by viewModels()
    private var balance: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController = navHostFragment.navController
        findViewById<BottomNavigationView>(R.id.bottom_nav_menu)
            .setupWithNavController(navController)

        initBalance()

    }

    private fun initBalance() {

        viewModel.viewModelScope.launch {
            viewModel.balance.collect {
                if (it != null) {
                    balance = it
                }

            }
        }
        Log.d("balance", "initBalance $balance")


//        binding.textViewBalanceValue.text = getString(
//            R.string.msg_currency_byn_amount_format, balance / 100f
//        )
    }

}