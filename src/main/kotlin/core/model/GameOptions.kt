package core.model

sealed class GameSize(val rows: Int, val columns: Int) {
    object Small: GameSize(10, 8)
    object Medium: GameSize(15, 12)
    object Big: GameSize(20, 15)
    object Biggest: GameSize(30, 20)
}

sealed class Difficult(val density: Int) {
    object Easy: Difficult(10)
    object Normal: Difficult(20)
    object Hard: Difficult(30)
    object Hardest: Difficult(40)
}