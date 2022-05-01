package core

import kotlin.random.Random

internal class Field {

    private val grid = mutableMapOf<Pair<Int, Int>, Cell>()

    private var mines: Int = 0
    private val minePositionList = mutableListOf<Pair<Int, Int>>()

    fun generateCellsWithMine(size: GameSize, difficult: Difficult) {
        mines = getTotalMines(size, difficult)

        while (mines > 0) {
            val position = getRandomPosition(size)

            val cell = grid[position]
            if (cell == null) {
                grid[position] = Cell(true, position.first, position.second)
                minePositionList.add(position)
                mines--
            }
        }
    }

    private fun getTotalMines(size: GameSize, difficult: Difficult): Int {
        val totalCells = size.rows * size.columns
        return (totalCells * (difficult.density.toFloat() / 100)).toInt()
    }

    private fun getRandomPosition(size: GameSize): Pair<Int, Int> {
        val rand = Random(System.nanoTime())
        val randomRow = (0 until size.rows).random(rand)
        val randomColumn = (0 until size.columns).random(rand)
        return Pair(randomRow, randomColumn)
    }

    fun generateEmptyCells(size: GameSize) {
        for (row in 0 until size.rows) {
            for (column in 0 until size.columns) {
                val cell = getCell(row, column)
                if (cell == null) {
                    grid[Pair(row, column)] = Cell(
                        hasMine = false,
                        row = row,
                        column = column,
                        isVisible = true,
                        nearbyMines = getNearbyMines(size, row, column)
                    )
                }
            }
        }
    }

    // region getMines
    private fun getNearbyMines(size: GameSize, row: Int, column: Int): Int {
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
        return getCell(row, column)?.let {
            if (it.hasMine) 1 else 0
        } ?: 0
    }
    // endregion

    fun getCell(row: Int, column: Int): Cell? {
        return grid[Pair(row, column)]
    }

    fun clearZone(cell: Cell) {
//        getNearbyMines()
    }


}