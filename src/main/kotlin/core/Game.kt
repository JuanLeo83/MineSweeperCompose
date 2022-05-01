package core

class Game {
    private lateinit var size: GameSize
    fun getSize() = size

    private val field = Field()

    fun generate(size: GameSize, difficult: Difficult) {
        this.size = size

        field.generateCellsWithMine(size, difficult)
        field.generateEmptyCells(size)
    }

    fun getCell(row: Int, column: Int): Cell? {
        return field.getCell(row, column)
    }

    fun mineFoundAction() {
        println("Fin del juego")
    }

    fun clearZoneAction(cell: Cell) {
        field.clearZone(cell)
    }
}