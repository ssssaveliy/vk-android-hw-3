package com.example.hw_3.ui

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hw_3.R
import com.example.hw_3.ui.theme.Hw3Theme

private val InputBorderColor = Color(0xFFD5C9C9)

@Composable
fun LoginScreen(
    onBackClick: () -> Unit,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) } // по умолчанию скрыт

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
            // стрелка + "Вход" по центру
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
                    text = stringResource(R.string.login_title),
                    style = TextStyle(
                        fontSize = 34.sp,
                        fontWeight = FontWeight.Medium,
                        color = AppColors.Main
                    ),
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Spacer(modifier = Modifier.height(75.dp))

            // поле почты
            LoginTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = stringResource(R.string.reg_email_hint),
                isPassword = false,
                passwordVisible = false,
                onTogglePasswordVisibility = {}
            )

            Spacer(modifier = Modifier.height(16.dp))

            // поле пароля
            LoginTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = stringResource(R.string.reg_password_hint),
                isPassword = true,
                passwordVisible = passwordVisible,
                onTogglePasswordVisibility = { passwordVisible = !passwordVisible }
            )

            Spacer(modifier = Modifier.height(90.dp))

            // кнопка "Войти"
            Button(
                onClick = onLoginClick,
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AppColors.Main,
                    contentColor = AppColors.Secondary
                ),
                border = BorderStroke(1.dp, Color.Black),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(380.dp)
                    .height(60.dp)
            ) {
                Text(
                    text = stringResource(R.string.login_button),
                    fontSize = 18.sp
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            // "Забыли пароль?"
            Text(
                text = stringResource(R.string.login_forgot),
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                    textDecoration = TextDecoration.Underline
                ),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable(onClick = onForgotPasswordClick)
            )

            Spacer(modifier = Modifier.height(35.dp))

            // "Или зарегистрируйтесь через"
            Text(
                text = stringResource(R.string.login_or_via),
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = AppColors.Main
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // иконки G / VK / TG
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_google),
                    contentDescription = "Google",
                    modifier = Modifier.size(32.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_vk),
                    contentDescription = "VK",
                    modifier = Modifier.size(32.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_tg),
                    contentDescription = "Telegram",
                    modifier = Modifier.size(32.dp)
                )
            }

            Spacer(modifier = Modifier.height(35.dp))

            // "Нет аккаунта? Зарегистрироваться."
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.login_no_account) + " ",
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = AppColors.Main
                    )
                )
                Text(
                    text = stringResource(R.string.login_register_link),
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = AppColors.Main,
                        textDecoration = TextDecoration.Underline,
                        fontWeight = FontWeight.Medium
                    ),
                    modifier = Modifier.clickable(onClick = onRegisterClick)
                )
            }
        }
    }
}

@Composable
private fun LoginTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isPassword: Boolean,
    passwordVisible: Boolean,
    onTogglePasswordVisibility: () -> Unit
) {
    val visualTransformation: VisualTransformation =
        if (isPassword && !passwordVisible) PasswordVisualTransformation()
        else VisualTransformation.None

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
            if (isPassword) {
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
private fun LoginScreenPreview() {
    Hw3Theme {
        LoginScreen(
            onBackClick = {},
            onLoginClick = {},
            onRegisterClick = {},
            onForgotPasswordClick = {}
        )
    }
}
