package eu.karcags.minesweeper

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import eu.karcags.minesweeper.feature.highscores.Highscores
import eu.karcags.minesweeper.feature.highscores.highscoresRoutes
import eu.karcags.minesweeper.feature.menu.Menu
import eu.karcags.minesweeper.feature.menu.menuRoutes
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import org.koin.core.module.Module

@Composable
@Preview
fun App(
    platformModule: Module = Module(),
) {
    KoinApplication(
        application = {
            modules(appModule, platformModule)
        }
    ) {
        MaterialTheme {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = Menu,
            ) {
                menuRoutes(
                    goToPlay = {},
                    goToHighscores = {
                        navController.navigate(Highscores)
                    },
                    goToSettings = {},
                )
                highscoresRoutes()
                //playRoutes()
                //settingsRoutes()
            }
        }
    }
}