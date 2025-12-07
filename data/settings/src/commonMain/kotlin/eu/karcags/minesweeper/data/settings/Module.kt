package eu.karcags.minesweeper.data.settings

import eu.karcags.minesweeper.data.core.dataCoreModule
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataSettingsModule = module {
    includes(dataCoreModule)

    singleOf(::DefaultUsernameRepository) {
        bind<UsernameRepository>()
    }
}