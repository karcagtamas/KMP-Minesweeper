package eu.karcags.minesweeper.domain.game

import eu.karcags.minesweeper.data.game.dataGameModule
import eu.karcags.minesweeper.data.settings.dataSettingsModule
import org.koin.dsl.module

val domainGameModule = module {
    includes(dataSettingsModule, dataGameModule)
}