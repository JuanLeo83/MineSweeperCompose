package ui.component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import core.model.Difficult
import ui.viewmodel.Game
import core.model.GameSize

@Preview
@Composable
fun SideMenu(game: MutableState<Game>) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(200.dp)
            .background(color = Color.Gray)
            .padding(12.dp)
    ) {
        Button(
            onClick = {
                val newGame = Game()
                newGame.generate(GameSize.Small, Difficult.Easy)

                game.value = newGame
            }
        ) {
            Text("Crear partida")
        }
    }
}