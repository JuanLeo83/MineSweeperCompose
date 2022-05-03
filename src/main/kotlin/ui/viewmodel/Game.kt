package ui.viewmodel

import core.model.Cell
import core.model.Difficult
import core.model.Field
import core.model.GameSize
import core.usecase.ClearMineCellsUseCase
import core.usecase.ClearZoneWithoutMinesUseCase
import core.usecase.DetectNearbyMinesUseCase
import core.usecase.GenerateGameUseCase

class Game {
    private lateinit var size: GameSize
    fun getSize() = size

    private lateinit var difficult: Difficult
    fun getDifficult() = difficult

    private lateinit var field: Field

    var gameLost: Boolean = false
    private set

    var gameWin: Boolean = false
    private set

    private val detectNearbyMinesUseCase = DetectNearbyMinesUseCase()
    private val generateGameUseCase = GenerateGameUseCase(detectNearbyMinesUseCase)
    private val clearZoneWithoutMinesUseCase = ClearZoneWithoutMinesUseCase()
    private val clearMineCellsUseCase = ClearMineCellsUseCase()

    fun generate(size: GameSize, difficult: Difficult) {
        this.size = size
        this.difficult = difficult

        field = generateGameUseCase.generate(size, difficult)
    }

    fun getCell(row: Int, column: Int): Cell? {
        return field.getCell(size, row, column)
    }

    fun mineFoundAction(): Game {
        field = clearMineCellsUseCase.clear(size, field)

        val game = clone()
        game.gameLost = true
        return game
    }

    private fun initialize(size: GameSize, difficult: Difficult, field: Field) {
        this.size = size
        this.difficult = difficult
        this.field = field
    }

    private fun clone(): Game {
        val game = Game()
        game.initialize(size, difficult, field)
        return game
    }

    fun clearZoneAction(cell: Cell): Game {
        field = clearZoneWithoutMinesUseCase.clear(size, field, cell)
        return clone()
    }
}