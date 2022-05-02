package ui.component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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