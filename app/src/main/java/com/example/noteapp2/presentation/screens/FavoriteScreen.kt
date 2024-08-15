package com.example.noteapp2.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.noteapp2.presentation.viewModel.NoteDiViewModel

@Composable
fun FavoriteScreenContent(noteViewModel: NoteDiViewModel){
    val allNotes = noteViewModel.allNotes.collectAsState()
    val navigator = LocalNavigator.currentOrThrow

    LazyColumn(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        items(allNotes.value?.filter { it.isFavorite }?: emptyList()){ note->
            Card(colors = CardDefaults.cardColors(containerColor = Color(0xFFeb480b) ),elevation = CardDefaults.cardElevation(8.dp),modifier = Modifier
                .fillMaxWidth()
                .clickable { navigator.push(NoteDetailsScreen(note, noteViewModel)) }
                .padding(vertical = 6.dp)) {
                Column(modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()) {
                    Icon(
                        modifier = Modifier
                            .clickable {
                                noteViewModel.updateNote(note.copy(isFavorite = !note.isFavorite))
                            }
                            .align(Alignment.End).size(30.dp),imageVector = Icons.Filled.Favorite, contentDescription = null, tint = if (note.isFavorite) Color.Red else  Color.White)
                    Text(text = note.title, fontSize = 23.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        )
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(text = note.description, fontSize = 20.sp,
                            color = Color.Black,)
                }

            }
        }
    }
}
