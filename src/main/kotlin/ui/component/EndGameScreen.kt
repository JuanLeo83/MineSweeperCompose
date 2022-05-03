package ui.component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ui.viewmodel.Game

@Preview
@Composable
private fun EndMessagePreview() {
    Column {
        EndMessage("Has perdido")
        EndMessage("¡Enhorabuena!")
    }
}

@Composable
fun EndMessage(message: String) {
    Card(
        elevation = 8.dp
    ) {
        Text(
            text = message,
            fontSize = 20.sp,
            modifier = Modifier.padding(32.dp)
        )
    }
}

@Composable
fun EndGameScreen(
    game: MutableState<Game>,
    message: String
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0x88000000))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.Center)
        ){
            EndMessage(message)
            Button(
                onClick = {
                    val newGame = Game()
                    newGame.generate(game.value.getSize(), game.value.getDifficult())

                    game.value = newGame
                }
            ) {
                Text("Volver a jugar")
            }
        }
    }
}