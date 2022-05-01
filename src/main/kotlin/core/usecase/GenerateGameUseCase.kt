package core.usecase

import core.model.Cell
import core.model.Difficult
import core.model.Field
import core.model.GameSize
import kotlin.random.Random

class GenerateGameUseCase(
    private val detectNearbyMinesUseCase: DetectNearbyMinesUseCase
) {

    private val grid = mutableMapOf<Pair<Int, Int>, Cell>()

    private var mines: Int = 0
    private val minePositionList = mutableListOf<Pair<Int, Int>>()

    fun generate(size: GameSize, difficult: Difficult): Field {
        generateCellsWithMine(size, difficult)
        generateEmptyCells(size)

        return Field(grid, minePositionList)
    }

    private fun generateCellsWithMine(size: GameSize, difficult: Difficult) {
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

    private fun generateEmptyCells(size: GameSize) {
        for (row in 0 until size.rows) {
            for (column in 0 until size.columns) {
                val cell = getCell(row, column)
                if (cell == null) {
                    grid[Pair(row, column)] = Cell(
                        hasMine = false,
                        row = row,
                        column = column,
                        isVisible = true,
                        nearbyMines = detectNearbyMinesUseCase.getNearbyMines(grid, size, row, column)
                    )
                }
            }
        }
    }

    private fun getCell(row: Int, column: Int): Cell? {
        return grid[Pair(row, column)]
    }
}