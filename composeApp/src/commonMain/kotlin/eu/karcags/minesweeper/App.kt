package eu.karcags.minesweeper

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import eu.karcags.eu.minesweeper.ui.core.Dimensions
import eu.karcags.eu.minesweeper.ui.core.LocalDimensions
import eu.karcags.eu.minesweeper.ui.core.LocalPadding
import eu.karcags.eu.minesweeper.ui.core.Padding
import eu.karcags.minesweeper.feature.highscores.Highscores
import eu.karcags.minesweeper.feature.highscores.highscoresRoutes
import eu.karcags.minesweeper.feature.menu.Menu
import eu.karcags.minesweeper.feature.menu.menuRoutes
import eu.karcags.minesweeper.feature.settings.Settings
import eu.karcags.minesweeper.feature.settings.settingsRoutes
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
            CompositionLocalProvider(
                LocalPadding provides Padding(
                    normal = 0.dp,
                ),

                LocalDimensions provides Dimensions(
                    maxWidthSmall = 400.dp,
                ),
            ) {
                NavHost(
                    navController = navController,
                    startDestination = Menu,
                ) {
                    menuRoutes(
                        goToPlay = {},
                        goToHighscores = {
                            navController.navigate(Highscores)
                        },
                        goToSettings = {
                            navController.navigate(Settings)
                        },
                    )
                    highscoresRoutes()
                    settingsRoutes()
                }
            }
        }
    }
}