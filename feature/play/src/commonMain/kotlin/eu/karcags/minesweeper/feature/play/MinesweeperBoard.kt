package eu.karcags.minesweeper.feature.play

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flag
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import minesweeper.feature.play.generated.resources.Res
import minesweeper.feature.play.generated.resources.mine
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun Tile(
    state: TileState,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(color = Color.Gray),
    ) {
        when (state) {
            is TileState.Hidden -> {
                if (state.flagged) {
                    Icon(
                        imageVector = Icons.Filled.Flag,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(fraction = 0.6f),
                    )
                }
            }

            TileState.Revealed.Mine -> {
                Icon(
                    painter = painterResource(Res.drawable.mine),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(fraction = 0.6f)
                )
            }

            is TileState.Revealed.Number -> {
                state.number?.let { n ->
                    Text(
                        text = n.toString(),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentHeight(
                                align = Alignment.CenterVertically,
                            )
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun TilesPreview() {
    val states = getAllPossibleTileStates()

    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        states.forEach { state ->
            Tile(
                state = state,
                modifier = Modifier.size(32.dp),
            )
        }
    }
}

private fun getAllPossibleTileStates(): Set<TileState> = setOf(
    TileState.Hidden(false),
    TileState.Hidden(true),
    TileState.Revealed.Mine,
    TileState.Revealed.Number(null),
    TileState.Revealed.Number(1),
    TileState.Revealed.Number(2),
    TileState.Revealed.Number(3),
    TileState.Revealed.Number(4),
    TileState.Revealed.Number(5),
    TileState.Revealed.Number(6),
    TileState.Revealed.Number(7),
    TileState.Revealed.Number(8),
)