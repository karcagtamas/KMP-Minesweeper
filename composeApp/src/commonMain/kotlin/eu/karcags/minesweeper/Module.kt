package eu.karcags.minesweeper

import eu.karcags.minesweeper.feature.highscores.highscoresModule
import eu.karcags.minesweeper.feature.menu.menuModule
import eu.karcags.minesweeper.feature.play.playModule
import eu.karcags.minesweeper.feature.settings.settingsModule
import org.koin.dsl.module

val appModule = module {
    includes(
        highscoresModule,
        menuModule,
        playModule,
        settingsModule,
    )
}