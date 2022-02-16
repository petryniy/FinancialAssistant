package selitskiyapp.hometasks.financialassistant.presentation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import selitskiyapp.hometasks.financialassistant.domain.repository.Repository
import selitskiyapp.hometasks.financialassistant.presentation.viewModels.OperationsFragmentViewModel

@Module
@InstallIn(ViewModelComponent::class)
class ViewModelModule {
    @Provides
    fun provideOperationsFragmentViewModel(
        repository: Repository
    ): OperationsFragmentViewModel =
        OperationsFragmentViewModel(repository = repository)
}