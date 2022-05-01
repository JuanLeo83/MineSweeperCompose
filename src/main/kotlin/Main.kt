// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import core.Difficult
import core.Game
import core.GameSize
import ui.component.GameCanvas
import ui.component.SideMenu

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Buscaminas Compose",
        state = WindowState(
            size = DpSize(1200.dp, 800.dp)
        )
    ) {
        App()
    }
}

@Composable
@Preview
fun App() {
    val firstGame = Game()
    firstGame.generate(GameSize.Small, Difficult.Easy)

    val game = remember { mutableStateOf(firstGame) }

    MaterialTheme {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(color = Color.DarkGray)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                SideMenu(game)
                GameCanvas(game)
            }
        }
    }
}
