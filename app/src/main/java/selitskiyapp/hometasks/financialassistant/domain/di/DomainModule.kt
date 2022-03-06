package selitskiyapp.hometasks.financialassistant.domain.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import selitskiyapp.hometasks.financialassistant.data.RepositoryImpl
import selitskiyapp.hometasks.financialassistant.data.storage.MoneyHolderDao
import selitskiyapp.hometasks.financialassistant.data.storage.OperationsDAO
import selitskiyapp.hometasks.financialassistant.domain.repository.MoneyHoldersRepository
import selitskiyapp.hometasks.financialassistant.domain.repository.OperationsRepository

@Module
@InstallIn(ViewModelComponent::class)
abstract class DomainModule {

    @Binds
    @ViewModelScoped
    abstract fun provideMoneyHoldersRepository(repository: RepositoryImpl): MoneyHoldersRepository


    @Binds
    @ViewModelScoped
    abstract fun provideOperationRepository(repository: RepositoryImpl): OperationsRepository
}
