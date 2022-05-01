package core.model

data class Cell(
    val hasMine: Boolean,
    val row: Int = 0,
    val column: Int = 0,
    val isVisible: Boolean = true,
    val nearbyMines: Int = 0
)
