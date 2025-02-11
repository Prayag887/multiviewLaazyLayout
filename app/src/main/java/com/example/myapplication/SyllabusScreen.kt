package com.example.myapplication

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter

@Composable
fun SyllabusScreen() {
    val books = remember { generateBooks() }
    val listState = rememberLazyListState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "Syllabus",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp),
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = listState,
        ) {
            items(books, key = { it.title }) { book ->
                BookItem(book)
            }
        }
    }
}

@Composable
fun BookItem(book: Book) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            model = book.imageUrl,
            contentDescription = "Book Cover",
            modifier =
                Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop,
            placeholder = rememberAsyncImagePainter(R.drawable.ic_launcher_background), // Placeholder image
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(text = book.title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(text = book.author, fontSize = 14.sp, color = Color.Gray)
        }
    }
}

data class Book(
    val title: String,
    val author: String,
    val imageUrl: String,
)

fun generateBooks(): List<Book> {
    val dummyImages =
        listOf(
            "https://images.unsplash.com/photo-1541963463532-d68292c34b19",
        )
    return List(1020) { index ->
        Book(
            title = "Book Title $index",
            author = "Author $index",
            imageUrl = dummyImages[index % dummyImages.size],
        )
    }
}
