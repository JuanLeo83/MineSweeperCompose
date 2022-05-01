package core.model

sealed class GameSize(val rows: Int, val columns: Int) {
    object Small: GameSize(10, 8)
    object Medium: GameSize(30, 40)
    object Big: GameSize(60, 80)
    object Biggest: GameSize(80, 120)
}

sealed class Difficult(val density: Int) {
    object Easy: Difficult(10)
    object Normal: Difficult(40)
    object Hard: Difficult(60)
    object Hardest: Difficult(75)
}