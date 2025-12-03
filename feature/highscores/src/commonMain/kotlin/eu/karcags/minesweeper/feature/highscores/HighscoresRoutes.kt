package eu.karcags.minesweeper.feature.highscores

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel

@Serializable
data object Highscores

fun NavGraphBuilder.highscoresRoutes() {
    composable<Highscores> {
        val viewModel = koinViewModel<HighscoresViewModel>()

        HighscoresScreen(
            viewModel = viewModel,
            modifier = Modifier.fillMaxSize(),
        )
    }
}