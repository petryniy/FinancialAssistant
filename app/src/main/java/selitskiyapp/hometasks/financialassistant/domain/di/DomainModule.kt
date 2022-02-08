package selitskiyapp.hometasks.financialassistant.domain.di

import org.koin.dsl.module
import selitskiyapp.hometasks.financialassistant.data.repository.DebitCreditLendRepositoryImpl
import selitskiyapp.hometasks.financialassistant.domain.repository.DebitCreditLendRepository

val domainModule = module {
    single<DebitCreditLendRepository> {
        DebitCreditLendRepositoryImpl(
            debitCreditLendStorage = get()
        )
    }
}