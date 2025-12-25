package com.example.hw_3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import com.example.hw_3.ui.*
import com.example.hw_3.ui.theme.Hw3Theme

enum class Screen {
    Start,
    ContinueWithoutAccount,
    Registration,
    Login,
    ForgotPasswordEmail,
    ConfirmEmailCodeScreen,
    NewPassword,
    EmptyNotes,
    NoteEditing
}

enum class NotesLoadState {
    Loading,
    Success,
    Error
}


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Hw3Theme {
                AppRoot()
            }
        }
    }
}

@Composable
private fun AppRoot() {
    var currentScreen by remember { mutableStateOf(Screen.Start) }

    // заметки
    var notes by remember { mutableStateOf(NotesRepository.getAll()) }

    // состояние загрузки заметок
    var notesLoadState by remember { mutableStateOf(NotesLoadState.Loading) }

    // счётчик попыток, чтобы перезапускать загрузку по кнопке "Повторить"
    var notesLoadAttempt by remember { mutableIntStateOf(0) }

    // id заметки, которую редактируем
    var editingNoteId by remember { mutableStateOf<Long?>(null) }

    // ассинхронная загрузка заметок
    LaunchedEffect(currentScreen, notesLoadAttempt) {
        if (currentScreen == Screen.EmptyNotes) {
            notesLoadState = NotesLoadState.Loading

            // задержка 2 секунды (по заданию вроде так)
            kotlinx.coroutines.delay(2000)

            try {
                // тут можно эмулировать ошибку, если нужно показать экран ошибки:
                val shouldFail = false //поставь true, чтобы проверить ErrorScreen. По крайней мере я так тестировал

                if (shouldFail) error("Network error")

                notes = NotesRepository.getAll()
                notesLoadState = NotesLoadState.Success
            } catch (_: Exception) {
                notesLoadState = NotesLoadState.Error
            }
        }
    }

    // экраны
    when (currentScreen) {
        Screen.Start -> StartScreen(
            onLoginClick = { currentScreen = Screen.Login },
            onSignUpClick = { currentScreen = Screen.Registration },
            onContinueWithoutAccountClick = {
                currentScreen = Screen.ContinueWithoutAccount
            }
        )

        Screen.ContinueWithoutAccount -> ContinueWithoutAccountScreen(
            onBackClick = { currentScreen = Screen.Start },
            onContinueClick = { currentScreen = Screen.EmptyNotes }
        )

        Screen.Registration -> RegistrationScreen(
            onBackClick = { currentScreen = Screen.Start },
            onRegisterClick = { currentScreen = Screen.EmptyNotes },
            onLoginClick = { currentScreen = Screen.Login }
        )

        Screen.Login -> LoginScreen(
            onBackClick = { currentScreen = Screen.Start },
            onLoginClick = { currentScreen = Screen.EmptyNotes },
            onRegisterClick = { currentScreen = Screen.Registration },
            onForgotPasswordClick = { currentScreen = Screen.ForgotPasswordEmail }
        )

        Screen.ForgotPasswordEmail -> ForgotPasswordEmailScreen(
            onBackClick = { currentScreen = Screen.Login },
            onSendCodeClick = { currentScreen = Screen.ConfirmEmailCodeScreen }
        )

        Screen.ConfirmEmailCodeScreen -> ConfirmEmailCodeScreen(
            onBackClick = { currentScreen = Screen.ForgotPasswordEmail },
            onContinueClick = { _ ->
                currentScreen = Screen.NewPassword
            },
            onResendClick = { }
        )

        Screen.NewPassword -> NewPasswordScreen(
            onBackClick = { currentScreen = Screen.ConfirmEmailCodeScreen },
            onConfirmClick = {
                currentScreen = Screen.Login
            }
        )

        Screen.EmptyNotes -> when (notesLoadState) {
            NotesLoadState.Loading -> NotesLoadingScreen()

            NotesLoadState.Error -> ErrorScreen(
                message = "Не удалось загрузить заметки.\nПроверьте подключение к интернету.",
                onRetryClick = {
                    // увеличиваем счётчик — LaunchedEffect перезапустится
                    notesLoadAttempt++
                }
            )

            NotesLoadState.Success -> EmptyNotesScreen(
                notes = notes,
                onCreateNoteClick = {
                    val note = NotesRepository.createEmpty()
                    editingNoteId = note.id
                    notes = NotesRepository.getAll()
                    currentScreen = Screen.NoteEditing
                },
                onNoteClick = { id ->
                    editingNoteId = id
                    currentScreen = Screen.NoteEditing
                }
            )
        }

        Screen.NoteEditing -> {
            val id = editingNoteId
            val note = id?.let { NotesRepository.getById(it) }

            if (note == null) {
                currentScreen = Screen.EmptyNotes
            } else {
                var title by remember(note.id) { mutableStateOf(note.title) }
                var text by remember(note.id) { mutableStateOf(note.text) }

                NoteEditingScreen(
                    title = title,
                    text = text,
                    onTitleChange = { newTitle ->
                        title = newTitle
                        NotesRepository.updateTitle(note.id, newTitle)
                        notes = NotesRepository.getAll().toList()
                    },
                    onTextChange = { newText ->
                        text = newText
                        NotesRepository.updateText(note.id, newText)
                        notes = NotesRepository.getAll().toList()
                    },
                    onBackClick = {
                        NotesRepository.deleteIfEmpty(note.id)
                        notes = NotesRepository.getAll().toList()
                        currentScreen = Screen.EmptyNotes
                    },
                    onShareClick = { /* TODO */ }
                )
            }
        }
    }
}
