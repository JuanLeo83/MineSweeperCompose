package ui.component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import core.model.Cell
import core.model.GameSize

@Preview
@Composable
fun MineButtonPreview() {
    Column {
        Row {
            MineButton(GameSize.Small, Cell(true), isVisible = mutableStateOf(true))
            MineButton(GameSize.Small, Cell(false), mutableStateOf(false))
            MineButton(GameSize.Small, Cell(true), mutableStateOf(false))
        }
        Row {
            MineButton(GameSize.Medium, Cell(true), isVisible = mutableStateOf(true))
            MineButton(GameSize.Medium, Cell(false), mutableStateOf(false))
            MineButton(GameSize.Medium, Cell(true), mutableStateOf(false))
        }
        Row {
            MineButton(GameSize.Big, Cell(true), isVisible = mutableStateOf(true))
            MineButton(GameSize.Big, Cell(false), mutableStateOf(false))
            MineButton(GameSize.Big, Cell(true), mutableStateOf(false))
        }
        Row {
            MineButton(GameSize.Biggest, Cell(true), isVisible = mutableStateOf(true))
            MineButton(GameSize.Biggest, Cell(false), mutableStateOf(false))
            MineButton(GameSize.Biggest, Cell(true), mutableStateOf(false))
        }
    }
}

@Composable
fun MineButton(
    gameSize: GameSize,
    cell: Cell,
    isVisible: MutableState<Boolean>,
    bombAction: () -> Unit = {},
    clearZoneAction: (cell: Cell) -> Unit = {}
) {
    val showFlag = remember { mutableStateOf(false) }

    val cellSize = getCellSize(gameSize)

    Box(
        modifier = Modifier
            .width(cellSize)
            .height(cellSize),
        contentAlignment = Alignment.Center
    ) {
        HideElement(gameSize, cell.hasMine, cell.nearbyMines)

        if (isVisible.value) {
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(1.dp),
                onClick = {
                    isVisible.value = false

                    if (cell.hasMine) {
                        bombAction()
                    } else {
                        clearZoneAction(cell)
                    }
                },
            ) {}
            if (showFlag.value) {
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
private fun HideElement(gameSize: GameSize, hasMine: Boolean, nearbyMines: Int) {
    if (hasMine) {
        Image(
            painter = painterResource("bomb.png"),
            contentDescription = "bomb",
            contentScale = ContentScale.Fit,
            modifier = Modifier.padding(getBombPadding(gameSize))
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
        GameSize.Medium -> 10.dp
        GameSize.Big -> 8.dp
        GameSize.Biggest -> 5.dp
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
        GameSize.Small -> 40.sp
        GameSize.Medium -> 35.sp
        GameSize.Big -> 30.sp
        GameSize.Biggest -> 20.sp
    }
}

private fun setNearbyMines(nearbyMines: Int): String {
    return if (nearbyMines == 0) "" else nearbyMines.toString()
}