package selitskiyapp.hometasks.financialassistant.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import selitskiyapp.hometasks.financialassistant.data.RepositoryImpl
import selitskiyapp.hometasks.financialassistant.data.storage.MoneyHolderDao
import selitskiyapp.hometasks.financialassistant.data.storage.OperationsDAO
import selitskiyapp.hometasks.financialassistant.domain.repository.Repository

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideRepository(
        operationsDAO: OperationsDAO, moneyHolderDao: MoneyHolderDao): Repository =
        RepositoryImpl(operationsDAO = operationsDAO, moneyHolderDao = moneyHolderDao)
}
