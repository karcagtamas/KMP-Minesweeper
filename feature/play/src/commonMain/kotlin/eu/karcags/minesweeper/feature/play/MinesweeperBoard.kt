package eu.karcags.minesweeper.feature.play

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flag
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import minesweeper.feature.play.generated.resources.Res
import minesweeper.feature.play.generated.resources.mine
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MinesweeperBoard(
    tileStates: List<List<TileState>>,
    modifier: Modifier = Modifier,
    boardWidth: Int = tileStates.size,
    boardHeight: Int = tileStates.firstOrNull()?.size ?: 0,
    textStyle: TextStyle = TextStyle()
) {
    if (tileStates.isNotEmpty()) {
        BoxWithConstraints(
            contentAlignment = Alignment.Center,
            modifier = modifier,
        ) {

            val requiredRatio = boardWidth / boardHeight.toFloat()
            val currentRatio = maxWidth / maxHeight

            val tileLength = if (requiredRatio > currentRatio) {
                maxWidth / boardWidth
            } else {
                maxHeight / boardHeight
            }
            val sizeAdjustedTextStyle = textStyle.copy(
                fontSize = tileLength.value.sp * 0.6f,
            )

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .width(tileLength * boardWidth)
                    .height(tileLength * boardHeight)
            ) {
                for (x in tileStates.indices) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f)
                    ) {
                        for (y in tileStates[x].indices) {
                            val tileState = tileStates[x][y]
                            val revealedBorderWidth = tileLength / 32
                            val hiddenBorderWidth = tileLength / 8

                            Tile(
                                state = tileState,
                                revealedBorderWidth = revealedBorderWidth,
                                hiddenBorderWidth = hiddenBorderWidth,
                                textStyle = sizeAdjustedTextStyle,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Tile(
    state: TileState,
    revealedBorderWidth: Dp,
    hiddenBorderWidth: Dp,
    textStyle: TextStyle = TextStyle(),
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .testTag("tile")
            .background(color = LocalMinesweeperBoardColorScheme.current.background)
            .run {
                if (state is TileState.Revealed) {
                    border(
                        width = revealedBorderWidth,
                        color = LocalMinesweeperBoardColorScheme.current.borderDark,
                    )
                } else {
                    hiddenTile(
                        borderThickness = hiddenBorderWidth,
                        borderLight = LocalMinesweeperBoardColorScheme.current.borderLight,
                        borderDark = LocalMinesweeperBoardColorScheme.current.borderDark,
                    )
                }
            }
    ) {
        when (state) {
            is TileState.Hidden -> {
                if (state.flagged) {
                    Icon(
                        imageVector = Icons.Filled.Flag,
                        contentDescription = null,
                        tint = LocalMinesweeperBoardColorScheme.current.flag,
                        modifier = Modifier.fillMaxSize(fraction = 0.6f),
                    )
                }
            }

            TileState.Revealed.Mine -> {
                Icon(
                    painter = painterResource(Res.drawable.mine),
                    contentDescription = null,
                    tint = LocalMinesweeperBoardColorScheme.current.mine,
                    modifier = Modifier.fillMaxSize(fraction = 0.6f)
                )
            }

            is TileState.Revealed.Number -> {
                state.number?.let { n ->
                    val color = when (n) {
                        1 -> LocalMinesweeperBoardColorScheme.current.one
                        2 -> LocalMinesweeperBoardColorScheme.current.two
                        3 -> LocalMinesweeperBoardColorScheme.current.three
                        4 -> LocalMinesweeperBoardColorScheme.current.four
                        5 -> LocalMinesweeperBoardColorScheme.current.five
                        6 -> LocalMinesweeperBoardColorScheme.current.six
                        7 -> LocalMinesweeperBoardColorScheme.current.seven
                        8 -> LocalMinesweeperBoardColorScheme.current.eight
                        else -> Color.Black
                    }
                    Text(
                        text = n.toString(),
                        textAlign = TextAlign.Center,
                        color = color,
                        style = textStyle,
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

@Composable
private fun Modifier.hiddenTile(
    borderThickness: Dp,
    borderLight: Color,
    borderDark: Color,
) = drawWithContent {
    drawContent()
    val thicknessPx = borderThickness.toPx()
    val width = size.width
    val height = size.height

    drawPath(
        path = Path().apply {
            moveTo(0f, 0f)
            lineTo(0f, height)
            lineTo(thicknessPx, height - thicknessPx)
            lineTo(thicknessPx, thicknessPx)
            lineTo(width - thicknessPx, thicknessPx)
            lineTo(width, 0f)
        },
        color = borderLight,
    )

    drawPath(
        path = Path().apply {
            moveTo(0f, height)
            lineTo(thicknessPx, height - thicknessPx)
            lineTo(width - thicknessPx, height - thicknessPx)
            lineTo(width - thicknessPx, thicknessPx)
            lineTo(width, 0f)
            lineTo(width, height)
        },
        color = borderLight,
    )
}

@Immutable
data class MinesweeperBoardColorScheme(
    val background: Color,
    val borderLight: Color,
    val borderDark: Color,
    val mine: Color,
    val flag: Color,
    val one: Color,
    val two: Color,
    val three: Color,
    val four: Color,
    val five: Color,
    val six: Color,
    val seven: Color,
    val eight: Color,
    val timerText: Color,
)

internal val LocalMinesweeperBoardColorScheme = compositionLocalOf {
    MinesweeperBoardColorScheme(
        background = Color(0xFFCBCBCB),
        borderLight = Color(0xFFFFFFFF),
        borderDark = Color(0xFF8F8F8F),
        mine = Color(0xFF000000),
        flag = Color(0xFFEB392A),
        one = Color(0xFF0000F5),
        two = Color(0xFF377E22),
        three = Color(0xFFFA3323),
        four = Color(0xFF00007B),
        five = Color(0xFF75140C),
        six = Color(0xFF377E7F),
        seven = Color(0xFF75147C),
        eight = Color(0xFF808080),
        timerText = Color(0xFFEA3324),
    )
}

@Preview
@Composable
fun MinesweeperBoardPreview() {
    val allPossibleTileStates = getAllPossibleTileStates()
    val tileStates: List<List<TileState>> = buildList {
        repeat(10) {
            add(buildList {
                repeat(10) {
                    add(allPossibleTileStates.random())
                }
            })
        }
    }

    MinesweeperBoard(
        tileStates = tileStates,
        modifier = Modifier
            .fillMaxSize()
    )
}

@Preview
@Composable
fun TilesPreview() {
    val states = getAllPossibleTileStates()

    FlowRow {
        states.forEach { state ->
            Tile(
                state = state,
                revealedBorderWidth = 2.dp,
                hiddenBorderWidth = 4.dp,
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