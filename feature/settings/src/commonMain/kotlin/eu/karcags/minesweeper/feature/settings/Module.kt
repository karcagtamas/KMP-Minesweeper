package eu.karcags.minesweeper.feature.settings

import eu.karcags.minesweeper.domain.settings.domainSettingsModule
import org.koin.dsl.module

val settingsModule = module {
    includes(domainSettingsModule)
}