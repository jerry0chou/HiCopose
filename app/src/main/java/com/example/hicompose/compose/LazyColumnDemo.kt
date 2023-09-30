package com.example.hicompose.compose

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Person(val age: Int, val name: String)

val personList: List<Person> = listOf(
    Person(25, "jerry"),
    Person(12, "kitty"),
    Person(27, "jack"),
    Person(27, "jack"),
    Person(27, "jack"),
    Person(27, "jack"),
    Person(27, "jack"),
    Person(27, "jack"),
    Person(27, "jack"),
    Person(27, "jack"),
)

@Composable
fun ColumnItem(person: Person) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(Color.Magenta)
            .padding(15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "${person.age}",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = person.name,
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LazyColumPreview() {
    LazyColumn(contentPadding = PaddingValues(5.dp)) {
//        itemsIndexed(items = personList)
        // stickyHeader
        items(items = personList) {
            Row(modifier = Modifier.padding(5.dp)) {
                Log.d("MainActivity", it.toString())
                ColumnItem(person = it)
            }
        }
    }
}

