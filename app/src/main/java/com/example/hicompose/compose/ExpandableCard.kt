package com.example.hicompose.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.example.hicompose.ui.theme.HiComposeTheme
//
@Composable
fun ExpandableCard(title: String, body: String){
    var expanded by remember {
        mutableStateOf(false)
    }

    Card{
        Column {
            Text(text = title)
            if(expanded){
                Text(text = body)
                IconButton(onClick = { expanded = false }) {
                    Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Collapse")
                }
            }else{
                IconButton(onClick = { expanded = true }) {
                    Icon(Icons.Default.KeyboardArrowUp, contentDescription = "Collapse")
                }
            }
        }
    }
}