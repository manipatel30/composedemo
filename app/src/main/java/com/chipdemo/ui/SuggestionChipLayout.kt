package com.chipdemo.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.chipdemo.ui.theme.Purple80
import com.chipdemo.ui.theme.PurpleGrey40


@Composable
fun SuggestionChipLayout() {
    var chipsData by remember {
        mutableStateOf(listOf("India", "France", "Spain"))
    }

    var chipState by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Suggestion Chip Example")
        Spacer(Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            chipsData.forEach {
                SuggestionChipEachRow(
                    label = it,
                    selected = it == chipState,
                    onChipChange = { chip ->
                        chipState = chip
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuggestionChipEachRow(
    label: String,
    selected: Boolean,
    onChipChange: (String) -> Unit
) {
    SuggestionChip(
        //modifier = Modifier.padding(horizontal = 10.dp),
        label = {
            Text(text = label)
        },
        onClick = {
            if (!selected)
                onChipChange(label)
            else
                onChipChange("")
        },
        shape = RoundedCornerShape(8.dp),
        colors = SuggestionChipDefaults.suggestionChipColors(
            containerColor = if (selected) Purple80 else Color.Transparent
        ),
        border = SuggestionChipDefaults.suggestionChipBorder(
            borderWidth = 1.dp,
            borderColor = if (selected) Color.Transparent else PurpleGrey40
        )
    )
}