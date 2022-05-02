package ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color(0x88000000))
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.align(Alignment.Center)
                ){
                    EndMessage("Has perdido")
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
    }


}