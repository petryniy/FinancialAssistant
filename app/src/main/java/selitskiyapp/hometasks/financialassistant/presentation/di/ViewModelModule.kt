package selitskiyapp.hometasks.financialassistant.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import selitskiyapp.hometasks.financialassistant.presentation.viewModels.OperationsFragmentViewModel

val viewModelModule = module {
    viewModel {
        OperationsFragmentViewModel(repository = get())
    }
}