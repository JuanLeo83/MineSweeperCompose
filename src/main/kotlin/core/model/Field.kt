package core.model

data class Field(
    val grid: MutableMap<Pair<Int, Int>, Cell>,
    val minePositionList: MutableList<Pair<Int, Int>>
) {

    var remainingFreeCells = grid.values.size - minePositionList.size - 1
        private set

    fun oneFreeCellLess(){
        remainingFreeCells--
    }

    fun getCell(size: GameSize, row: Int, column: Int): Cell? {
        if (row < 0 || row == size.rows || column < 0 || column == size.columns)
            return null

        return grid[Pair(row, column)]
    }

}