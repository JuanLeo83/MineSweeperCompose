package ui.viewmodel

import core.model.Cell
import core.model.Difficult
import core.model.Field
import core.model.GameSize
import core.usecase.DetectNearbyMinesUseCase
import core.usecase.GenerateGameUseCase

class Game {
    private lateinit var size: GameSize
    fun getSize() = size

    private lateinit var field: Field

    private val detectNearbyMinesUseCase = DetectNearbyMinesUseCase()
    private val generateGameUseCase = GenerateGameUseCase(detectNearbyMinesUseCase)

    fun generate(size: GameSize, difficult: Difficult) {
        this.size = size

        field = generateGameUseCase.generate(size, difficult)
    }

    fun getCell(row: Int, column: Int): Cell? {
        return field.getCell(row, column)
    }

    fun mineFoundAction() {
        println("Fin del juego")
    }

    fun clearZoneAction(cell: Cell) {

    }
}