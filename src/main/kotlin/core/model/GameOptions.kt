package core.model

sealed class GameOption(val name: String)

sealed class GameSize(name: String, val rows: Int, val columns: Int): GameOption(name) {
    object Small: GameSize("Pequeño", 10, 8)
    object Medium: GameSize("Mediano", 15, 12)
    object Big: GameSize("Grande", 20, 15)
    object Biggest: GameSize("Gigante", 30, 20)
}

sealed class Difficult(name: String, val density: Int): GameOption(name) {
    object Easy: Difficult("Fácil", 10)
    object Normal: Difficult("Normal", 20)
    object Hard: Difficult("Difícil", 30)
    object Hardest: Difficult("Locura", 40)
}

fun getAllSizes(): List<GameOption> = listOf(
    GameSize.Small,
    GameSize.Medium,
    GameSize.Big,
    GameSize.Biggest
)

fun getAllDifficulties(): List<GameOption> = listOf(
    Difficult.Easy,
    Difficult.Normal,
    Difficult.Hard,
    Difficult.Hardest
)