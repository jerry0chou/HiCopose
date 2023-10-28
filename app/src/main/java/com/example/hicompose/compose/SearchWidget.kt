package com.example.hicompose.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenForSearch() {
    Scaffold(topBar = {
        DefaultAppBar {}
    }) { it ->
        println(it)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultAppBar(onSearchClicked: () -> Unit) {
    TopAppBar(colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Magenta),
        title = { Text(text = "Home") }, actions = {
            IconButton(onClick = { onSearchClicked }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search Icon",
                    tint = Color.Black
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(), value = text, onValueChange = {
                onTextChange(it)
            },
            placeholder = {
                Text(modifier = Modifier.alpha(1f), text = "Search Here", color = Color.Black)
            },
            textStyle = TextStyle(fontSize = 18.sp),
            singleLine = true,
            leadingIcon = {
                IconButton(modifier = Modifier.alpha(0.5f), onClick = { }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                        tint = Color.Black
                    )
                }
            },
            trailingIcon = {
                IconButton(modifier = Modifier.alpha(0.5f), onClick = {
                    if (text.isNotEmpty()) onTextChange("") else onCloseClicked()
                }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close Icon",
                        tint = Color.Black
                    )
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { onSearchClicked(text) }),
            colors = TextFieldDefaults.textFieldColors(
            )
        )
    }

}

@Preview
@Composable
fun SearchAppbarPreview(){
    SearchAppBar(text = "Hi", onTextChange = {}, onCloseClicked = {  }, onSearchClicked = {})
}