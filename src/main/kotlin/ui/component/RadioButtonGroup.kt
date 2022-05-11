package ui.component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import core.model.GameOption
import core.model.getAllSizes

@Preview
@Composable
fun previewRadioButtonGroup() {
    val options = getAllSizes()
    RadioButtonGroup("Todas las opciones:", options) {}
}

@Composable
fun RadioButtonGroup(
    groupLabel: String,
    options: List<GameOption>,
    onClickAction: (optionSelected: GameOption) -> Unit
) {
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(options[0]) }

    Column {
        Text(
            text = groupLabel,
            fontWeight = FontWeight.Bold
        )

        options.forEach { option ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.selectable(
                    selected = option == selectedOption,
                    onClick = {
                        onOptionSelected(option)
                        onClickAction(option)
                    }
                )
            ) {
                RadioButton(
                    selected = option == selectedOption,
                    onClick = {
                        onOptionSelected(option)
                        onClickAction(option)
                    }
                )
                Text(option.name)
            }
        }
    }
}