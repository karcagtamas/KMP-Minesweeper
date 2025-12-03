package eu.karcags.minesweeper.data.settings

import eu.karcags.minesweeper.data.core.dataCoreModule
import org.koin.dsl.module

val dataSettingsModule = module {
    includes(dataCoreModule)
}