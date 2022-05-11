package ui.component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import core.model.Difficult
import ui.viewmodel.Game
import core.model.GameSize
import core.model.getAllDifficulties
import core.model.getAllSizes

@Preview
@Composable
fun SideMenu(game: MutableState<Game>) {
    val size = remember { mutableStateOf<GameSize>(GameSize.Small) }
    val difficult = remember { mutableStateOf<Difficult>(Difficult.Easy) }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(200.dp)
            .background(color = Color.Gray)
            .padding(12.dp)
    ) {
        RadioButtonGroup("Tamaño", getAllSizes()) {
            size.value = it as GameSize
        }

        Spacer(Modifier.height(30.dp))

        RadioButtonGroup("Dificultad", getAllDifficulties()) {
            difficult.value = it as Difficult
        }

        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Button(
                onClick = {
                    val newGame = Game()
                    newGame.generate(size.value, difficult.value)
                    game.value = newGame
                }
            ) {
                Text("Crear partida")
            }
        }
    }
}