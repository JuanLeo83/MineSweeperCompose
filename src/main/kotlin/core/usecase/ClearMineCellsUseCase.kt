package core.usecase

import core.model.Field
import core.model.GameSize

class ClearMineCellsUseCase {

    private lateinit var field: Field

    fun clear(gameSize: GameSize, field: Field): Field {
        this.field = field

        clearAllMines(gameSize)

        return this.field
    }

    private fun clearAllMines(gameSize: GameSize) {
        field.minePositionList.map {
            val cell = field.getCell(gameSize, it.first, it.second)!!
            field.grid[Pair(it.first, it.second)] = cell.copy(
                isVisible = false
            )
        }
    }
}