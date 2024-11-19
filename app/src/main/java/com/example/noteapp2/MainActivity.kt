package com.example.noteapp2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.navigator.Navigator
import com.example.noteapp2.presentation.screens.MainScreen
import com.example.noteapp2.presentation.viewModel.NoteDiViewModel
import com.example.noteapp2.ui.theme.NoteApp2Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  enableEdgeToEdge()
      //  val noteViewModel = NoteViewModel(application)
        setContent {
            val noteViewModel: NoteDiViewModel = hiltViewModel()

            Navigator(screen = MainScreen(noteViewModel))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteDialog(onSaveClick : (String,String)->Unit,onDismissRequest : ()->Unit){
    val title = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }
    AlertDialog(containerColor = Color(0xfff0e8e6),onDismissRequest = { onDismissRequest() },


        icon = {
            Box(modifier = Modifier
                .fillMaxWidth()
                .clickable { onDismissRequest() }) {
                Icon(Icons.Default.Close, contentDescription =null , modifier = Modifier.align(Alignment.CenterEnd))

            }
               },
        confirmButton = {
            Button(onClick = { onSaveClick(title.value,description.value) },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFE6902))
            ) {
                Text(text ="Save Note", color = Color(0xFF18181B) )
            }
        },
        title = { Text(text = "Add New Note", color = Color.Black)},
        text = {
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                OutlinedTextField(modifier =Modifier.border(1.dp,Color.Black) , textStyle = TextStyle(color = Color.Black

                ) ,
                    value = title.value, onValueChange = {title.value=it},
                    placeholder = { Text(text = "Add Title", color = Color.Black)})
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(textStyle = TextStyle(color = Color.Black

                ),modifier = Modifier.border(1.dp,Color.Black).height(100.dp),value = description.value, onValueChange = {description.value=it},
                    placeholder = { Text(text = "Add Description",color = Color.Black)})

            }
        }


    )
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NoteApp2Theme {
        Greeting("Android")
    }
}