package selitskiyapp.hometasks.financialassistant.domain.di

import org.koin.core.scope.get
import org.koin.dsl.module
import selitskiyapp.hometasks.financialassistant.data.repository.RepositoryImpl
import selitskiyapp.hometasks.financialassistant.domain.repository.Repository

val domainModule = module {
    single<Repository> {
        RepositoryImpl(
            storage = get()
        )
    }
}