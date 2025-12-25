package com.example.hw_3.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
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
import com.example.hw_3.R
import com.example.hw_3.ui.theme.Hw3Theme

@Composable
fun NoteEditingScreen(
    title: String,
    text: String,
    onTitleChange: (String) -> Unit,
    onTextChange: (String) -> Unit,
    onBackClick: () -> Unit,
    onShareClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.Secondary)
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg_start_flowers),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            alpha = 0.25f,
            contentScale = ContentScale.Crop
        )

        Column(modifier = Modifier.fillMaxSize()) {

            Spacer(modifier = Modifier.height(50.dp))

            // Верхняя панель
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.arrow_back),
                    contentDescription = "Назад",
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .size(24.dp)
                        .clickable(onClick = onBackClick)
                )

                BasicTextField(
                    value = title,
                    onValueChange = onTitleChange,
                    textStyle = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(horizontal = 32.dp),
                    decorationBox = { innerField ->
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            if (title.isEmpty()) {
                                Text(
                                    text = stringResource(R.string.changeable_title),
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color(0xFFAFB4AE)
                                )
                            }
                            innerField()
                        }
                    }
                )

                Image(
                    painter = painterResource(id = R.drawable.share),
                    contentDescription = "Поделиться",
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .size(24.dp)
                        .clickable(onClick = onShareClick)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Карточка текста
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                    .weight(1f),
                shape = RoundedCornerShape(18.dp),
                border = BorderStroke(1.dp, Color.Black),
                color = AppColors.Glass.copy(alpha = 0.9f)
            ) {
                BasicTextField(
                    value = text,
                    onValueChange = onTextChange,
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        color = Color.Black
                    ),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    decorationBox = { innerField ->
                        Box(modifier = Modifier.fillMaxSize()) {
                            if (text.isEmpty()) {
                                Text(
                                    text = stringResource(R.string.note_text_placeholder),
                                    style = TextStyle(
                                        fontSize = 16.sp,
                                        color = Color(0xFFB0B0B0)
                                    )
                                )
                            }
                            innerField()
                        }
                    }
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun NoteEditingScreenPreview() {
    Hw3Theme {
        NoteEditingScreen(
            title = "",
            text = "",
            onTitleChange = {},
            onTextChange = {},
            onBackClick = {},
            onShareClick = {}
        )
    }
}
