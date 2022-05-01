package core.model

data class Field(
    val grid: MutableMap<Pair<Int, Int>, Cell>,
    val minePositionList: MutableList<Pair<Int, Int>>
) {

    fun getCell(size: GameSize, row: Int, column: Int): Cell? {
        if (row < 0 || row == size.rows || column < 0 || column == size.columns)
            return null

        return grid[Pair(row, column)]
    }

}