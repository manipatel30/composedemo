package com.chipdemo.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.chipdemo.ui.theme.Purple40
import com.chipdemo.ui.theme.Purple80
import com.chipdemo.ui.theme.PurpleGrey40

@Composable
fun FilterChipLayout() {
    var list by remember {
        mutableStateOf(
            listOf(
                "Chip1",
                "Chip2",
                "Chip3",
                "Chip4",
                "Chip5",
                "Chip6"
            )
        )
    }

    val tempList: Set<Int> = emptySet()
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        ChipEachRow(
            list = list,
            tempList = tempList
        )
    }
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ChipEachRow(
    list: List<String>,
    tempList: Set<Int>
) {

    var multipleChecked by rememberSaveable { mutableStateOf(tempList) }

    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        list.forEachIndexed { index, s ->
            FilterChip(
                selected = multipleChecked.contains(index),
                onClick = {
                    multipleChecked = if (multipleChecked.contains(index))
                        multipleChecked.minus(index) else
                        multipleChecked.plus(index)
                },
                label = {
                    Text(text = s)
                },
                border = FilterChipDefaults.filterChipBorder(
                    borderWidth = if (multipleChecked.contains(index)) 0.dp else 2.dp,
                    borderColor = if (!multipleChecked.contains(index)) PurpleGrey40 else Color.Transparent
                ),
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = if (multipleChecked.contains(index)) Purple80 else Color.Transparent
                ),
                shape = RoundedCornerShape(8.dp),
                trailingIcon = {
                    if (multipleChecked.contains(index))
                        Icon(Icons.Default.Check, contentDescription = null) else null
                },
                modifier = Modifier.padding(start = 3.dp, end = 3.dp)
            )
        }
    }
}