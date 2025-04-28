package com.aks.notesapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.aks.notesapp.data.Notes
import kotlinx.coroutines.launch

@Composable
fun AddEditDetailView(
    id:Long,
    viewModel: NotesViewModel,
    navController: NavController
){
    val snackMessage = remember {
        mutableStateOf("")
    }

    val scope  = rememberCoroutineScope()

    val scaffoldState = rememberScaffoldState()

    if(id != 0L){
        val note = viewModel.getNoteById(id).collectAsState(initial = Notes(0L,"",""))
        viewModel.noteTitleState = note.value.title
        viewModel.notedescriptionState = note.value.description
    }else{
        viewModel.noteTitleState = ""
        viewModel.notedescriptionState = ""
    }

    Scaffold (
        topBar = {
            AppBarView(
                title = if(id != 0L) stringResource(R.string.update_note) else stringResource(R.string.add_note),
                OnBackNavClick = {
                    navController.navigate(Screen.Home.route)
                }
            )
        },
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        backgroundColor = MaterialTheme.colorScheme.background
    ){ it ->
        Column(
            modifier = Modifier
                .padding(it)
                .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,

        ) {
            Spacer(modifier = Modifier.height(10.dp))

            NoteTextField(
                label = "Enter Title",
                value = viewModel.noteTitleState,
                onValueChanged = {
                    viewModel.onNoteTitleChanged(it)
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            NoteTextField(
                label = "Enter Description",
                value = viewModel.notedescriptionState,
                onValueChanged = {
                    viewModel.onNoteDescriptionChanged(it)
                }
            )

            Spacer(modifier = Modifier.height(10.dp))


            Button(
                modifier = Modifier,
                onClick = {
                    if(viewModel.noteTitleState.isNotEmpty() && viewModel.notedescriptionState.isNotEmpty()){
                        // update note
                        if(id != 0L){
                            viewModel.updateNote(
                                Notes(
                                    id=id,
                                    title = viewModel.noteTitleState.trim(),
                                    description = viewModel.notedescriptionState.trim()
                                )
                            )
                        }else{
                            viewModel.addNote(
                                Notes(
                                    title = viewModel.noteTitleState.trim(),
                                    description = viewModel.notedescriptionState.trim()
                                )
                            )
                            snackMessage.value = "Note has been created"
                        }
                    }else{
                        // add note
                        snackMessage.value = "Enter fields to create Notes"
                    }
                    scope.launch {
                        navController.navigateUp()
                    }
                },

            ) {
                Text(
                    text = if(id != 0L) stringResource(id = R.string.update_note)
                    else stringResource(id = R.string.add_note),
                    style = TextStyle(
                        fontSize = 18.sp
                    )
                )
            }

        }
    }
}

@Composable
fun NoteTextField(
    label: String,
    value: String,
    onValueChanged: (String) -> Unit
){
    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        label = { Text(text = label, color = Color.White) },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            // using predefined Color
            textColor = Color.White,
            // using our own colors in Res.Values.Color
            focusedBorderColor = colorResource(id = R.color.white),
            unfocusedBorderColor = colorResource(id = R.color.white),
            cursorColor = colorResource(id = R.color.white),
            focusedLabelColor = colorResource(id = R.color.white),
            unfocusedLabelColor = colorResource(id = R.color.white),
        )


    )
}