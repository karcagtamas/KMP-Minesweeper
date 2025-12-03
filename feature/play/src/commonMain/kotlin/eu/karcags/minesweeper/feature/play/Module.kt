package eu.karcags.minesweeper.feature.play

import eu.karcags.minesweeper.domain.game.domainGameModule
import org.koin.dsl.module

val playModule = module {
    includes(domainGameModule)
}