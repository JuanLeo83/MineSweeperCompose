package ui.viewmodel

import core.model.Cell
import core.model.Difficult
import core.model.Field
import core.model.GameSize
import core.usecase.ClearZoneWithoutMinesUseCase
import core.usecase.DetectNearbyMinesUseCase
import core.usecase.GenerateGameUseCase

class Game {
    private lateinit var size: GameSize
    fun getSize() = size

    private lateinit var field: Field

    private val detectNearbyMinesUseCase = DetectNearbyMinesUseCase()
    private val generateGameUseCase = GenerateGameUseCase(detectNearbyMinesUseCase)
    private val clearZoneWithoutMinesUseCase = ClearZoneWithoutMinesUseCase()

    fun generate(size: GameSize, difficult: Difficult) {
        this.size = size
        field = generateGameUseCase.generate(size, difficult)
    }

    fun getCell(row: Int, column: Int): Cell? {
        return field.getCell(size, row, column)
    }

    fun mineFoundAction() {
        println("Fin del juego")
    }

    private fun initialize(size: GameSize, field: Field) {
        this.size = size
        this.field = field
    }

    private fun clone(): Game {
        val game = Game()
        game.initialize(size, field)
        return game
    }

    fun clearZoneAction(cell: Cell): Game {
        field = clearZoneWithoutMinesUseCase.clear(size, field, cell)
        return clone()
    }
}