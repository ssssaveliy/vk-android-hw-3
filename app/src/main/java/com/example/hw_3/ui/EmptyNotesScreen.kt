package com.example.hw_3.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.BorderStroke
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.viewinterop.AndroidView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hw_3.Note
import com.example.hw_3.NotesAdapter
import com.example.hw_3.R
import com.example.hw_3.ui.theme.Hw3Theme

@Composable
fun EmptyNotesScreen(
    notes: List<Note>,
    onCreateNoteClick: () -> Unit,
    onNoteClick: (Long) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.Secondary)
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg_start_flowers),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.25f)
        )

        if (notes.isEmpty()) {
            // пустой экран
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.main_empty_title),
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        color = AppColors.Main
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .widthIn(max = 368.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = onCreateNoteClick,
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AppColors.Main,
                        contentColor = AppColors.Secondary
                    ),
                    border = BorderStroke(1.dp, Color.Black),
                    modifier = Modifier
                        .width(275.dp)
                        .height(60.dp)
                ) {
                    Text(
                        text = stringResource(R.string.main_empty_button),
                        fontSize = 18.sp
                    )
                }
            }
        } else {
            // список заметок
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(50.dp))

                // верхняя панель с иконками (нет реализации, просто иконки)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // меню + сортировка
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_menu),
                            contentDescription = "Меню",
                            tint = Color.Black,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.ic_sort),
                            contentDescription = "Сортировка",
                            tint = Color.Black,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    // поиск + три точки
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = "Поиск",
                            tint = Color.Black,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.ic_more),
                            contentDescription = "Ещё",
                            tint = Color.Black,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // зона, которую можно скрол.: только список
                AndroidView(
                    factory = { context ->
                        RecyclerView(context).apply {
                            layoutManager = GridLayoutManager(context, 2)
                            adapter = NotesAdapter(onNoteClick).also {
                                it.submitNotes(notes)
                            }
                        }
                    },
                    update = { recyclerView ->
                        (recyclerView.adapter as NotesAdapter).submitNotes(notes)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                )
            }

            // кнопка создания заметки
            FloatingActionButton(
                onClick = onCreateNoteClick,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
                containerColor = AppColors.Main
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_pencil),
                    contentDescription = "Создать заметку",
                    tint = Color.Unspecified
                )
            }
        }
    }
}

// Preview только для пустого состояния
@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun EmptyNotesScreenPreview() {
    Hw3Theme {
        EmptyNotesScreen(
            notes = emptyList(),
            onCreateNoteClick = {},
            onNoteClick = {}
        )
    }
}
