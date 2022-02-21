package selitskiyapp.hometasks.financialassistant.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import selitskiyapp.hometasks.financialassistant.data.RepositoryImpl
import selitskiyapp.hometasks.financialassistant.data.storage.MoneyHolderDao
import selitskiyapp.hometasks.financialassistant.data.storage.OperationsDAO
import selitskiyapp.hometasks.financialassistant.domain.repository.MoneyHoldersRepository
import selitskiyapp.hometasks.financialassistant.domain.repository.OperationsRepository

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideMoneyHoldersRepository(
        operationsDAO: OperationsDAO, moneyHolderDao: MoneyHolderDao): MoneyHoldersRepository =
        RepositoryImpl(operationsDAO = operationsDAO, moneyHolderDao = moneyHolderDao)

    @Provides
    fun provideOperationRepository(operationsDAO: OperationsDAO, moneyHolderDao: MoneyHolderDao): OperationsRepository =
        RepositoryImpl(operationsDAO = operationsDAO, moneyHolderDao = moneyHolderDao)
}
