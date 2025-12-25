package com.example.hw_3.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import com.example.hw_3.R
import com.example.hw_3.ui.theme.Hw3Theme

private val InputBorderColor = Color(0xFFD5C9C9)

@Composable
fun ConfirmEmailCodeScreen(
    onBackClick: () -> Unit,
    onContinueClick: (String) -> Unit,
    onResendClick: () -> Unit,
) {
    var d1 by remember { mutableStateOf("") }
    var d2 by remember { mutableStateOf("") }
    var d3 by remember { mutableStateOf("") }
    var d4 by remember { mutableStateOf("") }

    val focusManager = LocalFocusManager.current

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
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                Spacer(modifier = Modifier.height(200.dp))

                Image(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Назад",
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .size(24.dp)
                        .clickable(onClick = onBackClick)
                )

                Text(
                    text = stringResource(R.string.confirm_email_title),
                    style = TextStyle(
                        fontSize = 28.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = AppColors.Main
                    ),
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // поясняющий текст под заголовком
            Text(
                text = stringResource(R.string.confirm_email_subtitle_1) + "\n" +
                        stringResource(R.string.confirm_email_subtitle_2),
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = AppColors.Main
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(48.dp))

            // ряд из 4 ячеек для кода
            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                CodeDigitBox(
                    value = d1,
                    onValueChange = { new ->
                        val digit = new.takeLast(1).filter { it.isDigit() }
                        d1 = digit
                        if (digit.isNotEmpty()) {
                            focusManager.moveFocus(FocusDirection.Next)
                        }
                    }
                )
                CodeDigitBox(
                    value = d2,
                    onValueChange = { new ->
                        val digit = new.takeLast(1).filter { it.isDigit() }
                        d2 = digit
                        if (digit.isNotEmpty()) {
                            focusManager.moveFocus(FocusDirection.Next)
                        } else {
                            focusManager.moveFocus(FocusDirection.Previous)
                        }
                    }
                )
                CodeDigitBox(
                    value = d3,
                    onValueChange = { new ->
                        val digit = new.takeLast(1).filter { it.isDigit() }
                        d3 = digit
                        if (digit.isNotEmpty()) {
                            focusManager.moveFocus(FocusDirection.Next)
                        } else {
                            focusManager.moveFocus(FocusDirection.Previous)
                        }
                    }
                )
                CodeDigitBox(
                    value = d4,
                    onValueChange = { new ->
                        val digit = new.takeLast(1).filter { it.isDigit() }
                        d4 = digit
                        if (digit.isEmpty()) {
                            focusManager.moveFocus(FocusDirection.Previous)
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // кнопка "Продолжить"
            Button(
                onClick = {
                    val code = d1 + d2 + d3 + d4
                    onContinueClick(code)
                },
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
                    text = stringResource(R.string.confirm_email_continue),
                    fontSize = 18.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // "Отправить ещё раз"
            Text(
                text = stringResource(R.string.confirm_email_resend),
                style = TextStyle(
                    fontSize = 14.sp,
                    color = AppColors.Main,
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable(onClick = onResendClick)
            )
        }
    }
}

@Composable
private fun CodeDigitBox(
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        textStyle = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = AppColors.Main
        ),
        modifier = Modifier
            .width(62.dp)
            .height(60.dp),
        shape = RoundedCornerShape(12.dp),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
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
private fun ConfirmEmailCodeScreenPreview() {
    Hw3Theme {
        ConfirmEmailCodeScreen(
            onBackClick = {},
            onContinueClick = {},
            onResendClick = {}
        )
    }
}
