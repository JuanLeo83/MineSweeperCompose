package core.usecase

import core.model.Cell
import core.model.GameSize

class DetectNearbyMinesUseCase {

    private lateinit var grid: MutableMap<Pair<Int, Int>, Cell>

    fun getNearbyMines(
        grid: MutableMap<Pair<Int, Int>, Cell>,
        size: GameSize,
        row: Int,
        column: Int
    ): Int {
        this.grid = grid

        return getTopLeftCell(size, row, column) +
                getTopCell(size, row, column) +
                getTopRightCell(size, row, column) +
                getLeftCell(size, row, column) +
                getRightCell(size, row, column) +
                getBottomLeftCell(size, row, column) +
                getBottomCell(size, row, column) +
                getBottomRightCell(size, row, column)
    }

    private fun getTopLeftCell(size: GameSize, row: Int, column: Int): Int {
        return getMine(size, row - 1, column - 1)
    }

    private fun getTopCell(size: GameSize, row: Int, column: Int): Int {
        return getMine(size, row - 1, column)
    }

    private fun getTopRightCell(size: GameSize, row: Int, column: Int): Int {
        return getMine(size, row - 1, column + 1)
    }

    private fun getLeftCell(size: GameSize, row: Int, column: Int): Int {
        return getMine(size, row, column - 1)
    }

    private fun getRightCell(size: GameSize, row: Int, column: Int): Int {
        return getMine(size, row, column + 1)
    }

    private fun getBottomLeftCell(size: GameSize, row: Int, column: Int): Int {
        return getMine(size, row + 1, column - 1)
    }

    private fun getBottomCell(size: GameSize, row: Int, column: Int): Int {
        return getMine(size, row + 1, column)
    }

    private fun getBottomRightCell(size: GameSize, row: Int, column: Int): Int {
        return getMine(size, row + 1, column + 1)
    }

    private fun getMine(size: GameSize, row: Int, column: Int): Int {
        if (row < 0 || row == size.rows || column < 0 || column == size.columns) return 0
        return grid[Pair(row, column)]?.let {
            if (it.hasMine) 1 else 0
        } ?: 0
    }

}