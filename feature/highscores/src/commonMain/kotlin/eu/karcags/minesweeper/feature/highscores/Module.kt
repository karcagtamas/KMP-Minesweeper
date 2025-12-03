package eu.karcags.minesweeper.feature.highscores

import eu.karcags.minesweeper.domain.game.domainGameModule
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val highscoresModule = module {
    includes(domainGameModule)

    viewModelOf(::HighscoresViewModel)
}