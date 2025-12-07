package eu.karcags.minesweeper.data.core

import eu.karcags.minesweeper.data.core.storage.DataStoreStorage
import eu.karcags.minesweeper.data.core.storage.Storage
import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataCoreModule = module {
    includes(platformModule)
    singleOf(::DataStoreStorage) {
        bind<Storage>()
    }
}

internal expect val platformModule: Module