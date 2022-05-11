package core.usecase

import core.model.Cell
import core.model.Field
import core.model.GameSize

class ClearZoneWithoutMinesUseCase {

    private lateinit var size: GameSize
    private lateinit var field: Field

    private val nextIterationToInspect = mutableListOf<Cell>()
    private val toInspect = mutableListOf<Cell>()

    fun clear(size: GameSize, field: Field, cell: Cell): Field {
        this.size = size
        this.field = field

        nextIterationToInspect.add(cell)
        checkToInspect()

        return this.field
    }

    private fun checkToInspect() {
        toInspect.clear()
        toInspect.addAll(nextIterationToInspect)
        nextIterationToInspect.clear()

        toInspect.map {
            if (it.isVisible) {
                clearCell(it)
                if (it.nearbyMines == 0) {
                    val neighbors = getNeighborsCells(it)
                    checkNeighbors(neighbors)
                }
            }
        }

        if (nextIterationToInspect.isNotEmpty()) {
            checkToInspect()
        }
    }

    private fun clearCell(cell: Cell) {
        if (field.grid[Pair(cell.row, cell.column)]!!.isVisible) {
            field.oneFreeCellLess()
        }
        field.grid[Pair(cell.row, cell.column)] = cell.copy(
            isVisible = false
        )
    }

    private fun getNeighborsCells(cell: Cell): List<Cell> {
        val neighbors = mutableListOf<Cell>()
        getTopLeftNeighbor(cell)?.let { neighbors.add(it) }
        getTopNeighbor(cell)?.let { neighbors.add(it) }
        getTopRightNeighbor(cell)?.let { neighbors.add(it) }
        getLeftNeighbor(cell)?.let { neighbors.add(it) }
        getRightNeighbor(cell)?.let { neighbors.add(it) }
        getBottomLeftNeighbor(cell)?.let { neighbors.add(it) }
        getBottomNeighbor(cell)?.let { neighbors.add(it) }
        getBottomRightNeighbor(cell)?.let { neighbors.add(it) }
        return neighbors
    }

    private fun getTopLeftNeighbor(cell: Cell): Cell? {
        return field.getCell(size, cell.row - 1, cell.column - 1)
    }

    private fun getTopNeighbor(cell: Cell): Cell? {
        return field.getCell(size, cell.row - 1, cell.column)
    }

    private fun getTopRightNeighbor(cell: Cell): Cell? {
        return field.getCell(size, cell.row - 1, cell.column + 1)
    }

    private fun getLeftNeighbor(cell: Cell): Cell? {
        return field.getCell(size, cell.row, cell.column - 1)
    }

    private fun getRightNeighbor(cell: Cell): Cell? {
        return field.getCell(size, cell.row, cell.column + 1)
    }

    private fun getBottomLeftNeighbor(cell: Cell): Cell? {
        return field.getCell(size, cell.row + 1, cell.column - 1)
    }

    private fun getBottomNeighbor(cell: Cell): Cell? {
        return field.getCell(size, cell.row + 1, cell.column)
    }

    private fun getBottomRightNeighbor(cell: Cell): Cell? {
        return field.getCell(size, cell.row + 1, cell.column + 1)
    }

    private fun checkNeighbors(neighbors: List<Cell>) {
        neighbors.map { cell ->
            if (!cell.hasMine && cell.isVisible) {
                if (cell.nearbyMines == 0) {
                    nextIterationToInspect.add(cell)
                } else {
                    clearCell(cell)
                }
            }
        }
    }

}