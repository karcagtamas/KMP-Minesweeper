package eu.karcags.minesweeper.domain.settings

import eu.karcags.minesweeper.data.settings.dataSettingsModule
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainSettingsModule = module {
    includes(dataSettingsModule)

    factoryOf(::GetUsernameAsFlowUseCase)
    factoryOf(::UpdateUsernameUseCase)
}