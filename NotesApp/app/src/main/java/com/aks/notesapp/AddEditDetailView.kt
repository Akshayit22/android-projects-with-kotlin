package com.aks.notesapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aks.notesapp.data.Notes
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

@Composable
fun AddEditDetailView(
    id: Long,
    viewModel: NotesViewModel,
    navController: NavController
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var isSubmitting by remember { mutableStateOf(false) }

    // When id==0 we're adding a new note — skip the DB query entirely to avoid null emission
    val noteFlow = remember(id) {
        if (id != 0L) viewModel.getNoteById(id) else flowOf(Notes(0L, "", ""))
    }
    val noteState by noteFlow.collectAsState(initial = Notes(0L, "", ""))

    LaunchedEffect(noteState.id) {
        if (id != 0L && noteState.id != 0L) {
            viewModel.noteTitleState = noteState.title
            viewModel.notedescriptionState = noteState.description
        }
    }
    LaunchedEffect(id) {
        if (id == 0L) {
            viewModel.noteTitleState = ""
            viewModel.notedescriptionState = ""
        }
    }

    Scaffold(
        topBar = {
            AppBarView(
                title = if (id != 0L) stringResource(R.string.update_note)
                        else stringResource(R.string.add_note),
                OnBackNavClick = { navController.navigateUp() }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = viewModel.noteTitleState,
                onValueChange = { viewModel.onNoteTitleChanged(it) },
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    capitalization = KeyboardCapitalization.Sentences
                ),
                isError = isSubmitting && viewModel.noteTitleState.isBlank()
            )

            OutlinedTextField(
                value = viewModel.notedescriptionState,
                onValueChange = { viewModel.onNoteDescriptionChanged(it) },
                label = { Text("Description") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
                shape = RoundedCornerShape(12.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    capitalization = KeyboardCapitalization.Sentences
                )
            )

            Button(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                enabled = !isSubmitting,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                onClick = {
                    isSubmitting = true
                    when {
                        viewModel.noteTitleState.isBlank() -> {
                            scope.launch {
                                snackbarHostState.showSnackbar("Title cannot be empty")
                            }
                            isSubmitting = false
                        }
                        viewModel.notedescriptionState.isBlank() -> {
                            scope.launch {
                                snackbarHostState.showSnackbar("Description cannot be empty")
                            }
                            isSubmitting = false
                        }
                        else -> {
                            if (id != 0L) {
                                viewModel.updateNote(
                                    Notes(
                                        id = id,
                                        title = viewModel.noteTitleState.trim(),
                                        description = viewModel.notedescriptionState.trim()
                                    )
                                )
                            } else {
                                viewModel.addNote(
                                    Notes(
                                        title = viewModel.noteTitleState.trim(),
                                        description = viewModel.notedescriptionState.trim()
                                    )
                                )
                            }
                            navController.navigateUp()
                        }
                    }
                }
            ) {
                Text(
                    text = if (id != 0L) stringResource(R.string.update_note)
                           else stringResource(R.string.add_note),
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}
