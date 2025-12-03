package eu.karcags.minesweeper.feature.menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
internal fun MenuScreen(
    goToPlay: () -> Unit,
    goToHighscores: () -> Unit,
    goToSettings: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier,
    ) {
        TextButton(
            onClick = goToPlay,
        ) {
            Text("Play")
        }

        TextButton(
            onClick = goToHighscores,
        ) {
            Text("Highscores")
        }

        TextButton(
            onClick = goToSettings,
        ) {
            Text("Settings")
        }
    }
}