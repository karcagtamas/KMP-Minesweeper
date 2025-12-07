package eu.karcags.minesweeper.feature.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel,
    modifier: Modifier = Modifier
) {
    val username by viewModel.username.collectAsState()
    Column(modifier = modifier) {
        Text("Username")

        TextField(
            value = username,
            onValueChange = {
                viewModel.setUsername(it)
            }
        )

        Button(
            onClick = {
                viewModel.save()
            }
        ) {
            Text("Save")
        }
    }
}