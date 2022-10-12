package ui.component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerButton
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import core.model.Cell
import core.model.Difficult
import core.model.GameSize
import ui.viewmodel.Game

@Preview
@Composable
fun MineButtonPreview() {
    val firstGame = Game()
    firstGame.generate(GameSize.Small, Difficult.Easy)

    val game = remember { mutableStateOf(firstGame) }

    Column {
        Row {
            MineButton(game, Cell(true), isVisible = mutableStateOf(true))
            MineButton(game, Cell(false, nearbyMines = 1), mutableStateOf(false))
            MineButton(game, Cell(true), mutableStateOf(false))
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MineButton(
    game: MutableState<Game>,
    cell: Cell,
    isVisible: MutableState<Boolean>,
    bombAction: () -> Unit = {},
    clearZoneAction: (cell: Cell) -> Unit = {}
) {
    var showFlag by remember { mutableStateOf(false) }

    val cellSize = getCellSize(game.value.getSize())
    val backgroundColor = remember { mutableStateOf(Color.Transparent) }

    Box(
        modifier = Modifier
            .width(cellSize)
            .height(cellSize),
        contentAlignment = Alignment.Center
    ) {
        HideElement(game.value.getSize(), cell.hasMine, cell.nearbyMines, backgroundColor)

        if (isVisible.value) {
            Button(
                enabled = !game.value.gameLost,
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(1.dp)
                    .onClick(
                        matcher = PointerMatcher.mouse(PointerButton.Secondary),
                        onClick = {
                            showFlag = !showFlag
                        }
                    ),
                onClick = {
                    if (!showFlag) {
                        isVisible.value = false

                        if (cell.hasMine) {
                            bombAction()
                            backgroundColor.value = Color.Red
                        } else {
                            clearZoneAction(cell)
                        }
                    }
                },
            ) {}
            if (showFlag) {
                Image(
                    painter = painterResource("flag.png"),
                    contentDescription = "flag",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .width(cellSize * 0.75f)
                        .height(cellSize * 0.75f)
                )
            }
        }
    }
}

private fun getCellSize(gameSize: GameSize): Dp {
    return when (gameSize) {
        GameSize.Small -> 40.dp
        GameSize.Medium -> 30.dp
        GameSize.Big -> 20.dp
        GameSize.Biggest -> 15.dp
    }
}

@Composable
private fun HideElement(gameSize: GameSize, hasMine: Boolean, nearbyMines: Int, backgroundColor: MutableState<Color>) {
    if (hasMine) {
        Image(
            painter = painterResource("bomb.png"),
            contentDescription = "bomb",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .padding(1.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(color = backgroundColor.value)
                .padding(getBombPadding(gameSize))
        )
    } else {
        Text(
            text = setNearbyMines(nearbyMines),
            fontSize = getFontSize(gameSize),
            color = getTextColor(nearbyMines),
            textAlign = TextAlign.Center
        )
    }
}

private fun getBombPadding(gameSize: GameSize): Dp {
    return when (gameSize) {
        GameSize.Small -> 10.dp
        GameSize.Medium -> 7.dp
        GameSize.Big -> 4.dp
        GameSize.Biggest -> 3.dp
    }
}

private fun getTextColor(nearbyMines: Int): Color {
    return when (nearbyMines) {
        1 -> Color.Blue
        2 -> Color.Green
        3 -> Color.Yellow
        4 -> Color.Red
        5 -> Color.Magenta
        6 -> Color.Cyan
        7 -> Color.White
        8 -> Color.Black
        else -> Color.Transparent
    }
}

private fun getFontSize(gameSize: GameSize): TextUnit {
    return when (gameSize) {
        GameSize.Small -> 30.sp
        GameSize.Medium -> 25.sp
        GameSize.Big -> 18.sp
        GameSize.Biggest -> 12.sp
    }
}

private fun setNearbyMines(nearbyMines: Int): String {
    return if (nearbyMines == 0) "" else nearbyMines.toString()
}