package eu.karcags.minesweeper

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import eu.karcags.minesweeper.feature.menu.MenuScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        MenuScreen(
            goToPlay = {},
            goToHighscores = {},
            goToSettings = {},
            modifier = Modifier.fillMaxSize(),
        )
    }
}