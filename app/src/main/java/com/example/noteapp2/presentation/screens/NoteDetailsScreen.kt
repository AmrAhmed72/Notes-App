package com.example.noteapp2.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.noteapp2.presentation.viewModel.NoteDiViewModel
import com.example.noteapp2.data.db.Note


class NoteDetailsScreen(val note: Note, val noteViewModel: NoteDiViewModel) : Screen {
    @Composable
    override fun Content() {
        val title = remember { mutableStateOf(note.title) }
        val description = remember { mutableStateOf(note.description) }
        val navigator = LocalNavigator.currentOrThrow

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFF18181B))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Edit Your Note",
                color = Color(0xFFFE6902),
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp)
            Spacer(modifier = Modifier.height(80.dp))
            OutlinedTextField(
                value = title.value,
                onValueChange = { title.value = it },
                placeholder = { Text(text = "Add Title", color = Color.Gray) },
                modifier = Modifier.fillMaxWidth(),
                textStyle = androidx.compose.ui.text.TextStyle(color = Color.White),shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp)


            )
            Spacer(modifier = Modifier.height(50.dp))
            OutlinedTextField(
                value = description.value,
                onValueChange = { description.value = it },
                placeholder = { Text(text = "Add Description", color = Color.Gray) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                textStyle = androidx.compose.ui.text.TextStyle(color = Color.White),shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        noteViewModel.updateNote(note.copy(title = title.value, description = description.value))
                        navigator.pop()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFE6902))
                ) {
                    Text(text = "Update")
                }

                Button(
                    onClick = {
                        noteViewModel.deleteNote(note)
                        navigator.pop()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFE6902))
                ) {
                    Text(text = "Delete")
                }
            }
        }
    }
}