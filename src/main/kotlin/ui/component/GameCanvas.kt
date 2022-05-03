package ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ui.viewmodel.Game

@Composable
fun GameCanvas(game: MutableState<Game>) {
    Box {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            MineField(game)
        }

        if (game.value.gameLost) {
            EndGameScreen(game, "Has perdido")
        } else if (game.value.gameWin) {
            EndGameScreen(game, "¡Enhorabuena!")
        }
    }
}