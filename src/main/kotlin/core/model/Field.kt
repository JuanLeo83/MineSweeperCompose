package core.model

data class Field(
    val grid: MutableMap<Pair<Int, Int>, Cell>,
    val minePositionList: MutableList<Pair<Int, Int>>
) {

    fun getCell(row: Int, column: Int): Cell? {
        return grid[Pair(row, column)]
    }

}