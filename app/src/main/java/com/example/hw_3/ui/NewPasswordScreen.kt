package com.example.hw_3.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hw_3.R
import com.example.hw_3.ui.theme.Hw3Theme

private val InputBorderColor = Color(0xFFD5C9C9)

@Composable
fun NewPasswordScreen(
    onBackClick: () -> Unit,
    onConfirmClick: () -> Unit,
) {
    var password by remember { mutableStateOf("") }
    var repeatPassword by remember { mutableStateOf("") }

    // по умолчанию пароль виден, по нажатию — скрывается
    var passwordVisible by remember { mutableStateOf(true) }
    var repeatPasswordVisible by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.Secondary)
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
                    text = stringResource(R.string.new_password_title),
                    style = TextStyle(
                        fontSize = 28.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = AppColors.Main
                    ),
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Spacer(modifier = Modifier.height(60.dp))

            // Поле: новый пароль
            NewPasswordTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = stringResource(R.string.reg_password_hint),
                passwordVisible = passwordVisible,
                onTogglePasswordVisibility = { passwordVisible = !passwordVisible }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Поле: повтор пароля
            NewPasswordTextField(
                value = repeatPassword,
                onValueChange = { repeatPassword = it },
                placeholder = stringResource(R.string.reg_repeat_password_hint),
                passwordVisible = repeatPasswordVisible,
                onTogglePasswordVisibility = { repeatPasswordVisible = !repeatPasswordVisible }
            )

            Spacer(modifier = Modifier.height(80.dp))

            // Кнопка "Подтвердить пароль"
            Button(
                onClick = onConfirmClick,
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AppColors.Main,
                    contentColor = AppColors.Secondary
                ),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color.Black),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(380.dp)
                    .height(60.dp)
            ) {
                Text(
                    text = stringResource(R.string.new_password_confirm),
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Composable
private fun NewPasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    passwordVisible: Boolean,
    onTogglePasswordVisibility: () -> Unit
) {
    val visualTransformation: VisualTransformation =
        if (passwordVisible) VisualTransformation.None
        else PasswordVisualTransformation()

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        placeholder = {
            Text(
                text = placeholder,
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
        visualTransformation = visualTransformation,
        trailingIcon = {
            val icon = if (passwordVisible) R.drawable.ic_eye_off else R.drawable.ic_eye
            val desc = if (passwordVisible) "Скрыть пароль" else "Показать пароль"

            IconButton(onClick = onTogglePasswordVisibility) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = desc,
                    modifier = Modifier.size(40.dp),
                    tint = Color.Black
                )
            }
        },
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
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun NewPasswordScreenPreview() {
    Hw3Theme {
        NewPasswordScreen(
            onBackClick = {},
            onConfirmClick = {}
        )
    }
}
