package ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import core.Game

@Composable
fun GameCanvas(game: MutableState<Game>) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        MineField(game)
    }
}