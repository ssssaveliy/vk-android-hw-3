package com.example.hw_3.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hw_3.R
import com.example.hw_3.ui.theme.Hw3Theme

private val InputBorderColor = Color(0xFFD5C9C9)

@Composable
fun ForgotPasswordEmailScreen(
    onBackClick: () -> Unit,
    onSendCodeClick: () -> Unit,
) {
    var email by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.Secondary) // F4FBF4
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            // стрелка + заголовок по центру
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Назад",
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .size(24.dp)
                        .clickable(onClick = onBackClick)
                )

                Spacer(modifier = Modifier.height(200.dp))

                Text(
                    text = stringResource(R.string.forgot_email_title),
                    style = TextStyle(
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Medium,
                        color = AppColors.Main
                    ),
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            // Отступ до поля
            Spacer(modifier = Modifier.height(120.dp))

            // Поле для ввода почты
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                singleLine = true,
                placeholder = {
                    Text(
                        text = stringResource(R.string.forgot_email_hint),
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = InputBorderColor
                        )
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = InputBorderColor,
                    unfocusedBorderColor = InputBorderColor,
                    cursorColor = AppColors.Main,
                    focusedContainerColor = AppColors.Secondary,
                    unfocusedContainerColor = AppColors.Secondary,
                    focusedTextColor = AppColors.Main,
                    unfocusedTextColor = AppColors.Main
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Кнопка "Получить код"
            Button(
                onClick = onSendCodeClick,
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AppColors.Main,
                    contentColor = AppColors.Secondary
                ),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(380.dp)
                    .height(60.dp)
            ) {
                Text(
                    text = stringResource(R.string.forgot_email_button),
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ForgotPasswordEmailScreenPreview() {
    Hw3Theme {
        ForgotPasswordEmailScreen(
            onBackClick = {},
            onSendCodeClick = {}
        )
    }
}
