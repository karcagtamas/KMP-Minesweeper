package eu.karcags.minesweeper.data.game

import eu.karcags.minesweeper.data.core.dataCoreModule
import org.koin.dsl.module

val dataGameModule = module {
    includes(dataCoreModule)
}