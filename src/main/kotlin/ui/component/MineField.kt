package ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import core.Cell
import core.Game

@Composable
fun MineField(game: MutableState<Game>) {
    for (row in 0 until game.value.getSize().rows) {
        Row {
            for (column in 0 until game.value.getSize().columns) {
                setMineButton(game, row, column, { game.value.mineFoundAction() }) {
                    game.value.clearZoneAction(it)
                }
            }
        }
    }
}

@Composable
private fun setMineButton(
    game: MutableState<Game>,
    row: Int, column: Int,
    bombAction: () -> Unit,
    clearZoneAction: (cell: Cell) -> Unit
) {
    val cell = game.value.getCell(row, column)

    val isVisible = remember { mutableStateOf(true) }
    isVisible.value = cell?.isVisible ?: true

    cell?.let {
        MineButton(
            gameSize = game.value.getSize(),
            cell = cell,
            isVisible = isVisible,
            bombAction = bombAction,
            clearZoneAction = clearZoneAction
        )
    }
}