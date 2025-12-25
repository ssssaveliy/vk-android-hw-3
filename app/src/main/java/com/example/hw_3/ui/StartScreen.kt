package com.example.hw_3.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hw_3.R
import com.example.hw_3.ui.theme.Hw3Theme

@Composable
fun StartScreen(
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit,
    onContinueWithoutAccountClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.Main)
    ) {
        // Фоновая картинка с цветами
        Image(
            painter = painterResource(id = R.drawable.bg_start_flowers),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Центральные кнопки
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Кнопка "Войти"
            Button(
                onClick = onLoginClick,
                shape = RoundedCornerShape(60.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AppColors.Secondary,
                    contentColor = androidx.compose.ui.graphics.Color.Black
                ),
                modifier = Modifier
                    .width(275.dp)
                    .height(60.dp)
            ) {
                Text(
                    text = stringResource(R.string.start_login),
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Кнопка "Зарегистрироваться"
            Button(
                onClick = onSignUpClick,
                shape = RoundedCornerShape(60.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AppColors.Accent,
                    contentColor = androidx.compose.ui.graphics.Color.White
                ),
                modifier = Modifier
                    .width(275.dp)
                    .height(60.dp)
            ) {
                Text(
                    text = stringResource(R.string.start_register),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        // "Продолжить без аккаунта"
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 36.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = stringResource(R.string.start_continue_without_account),
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    color = AppColors.Secondary,
                    textDecoration = TextDecoration.Underline
                ),
                modifier = Modifier
                    .clickable(onClick = onContinueWithoutAccountClick)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun StartScreenPreview() {
    Hw3Theme {
        StartScreen(
            onLoginClick = {},
            onSignUpClick = {},
            onContinueWithoutAccountClick = {}
        )
    }
}
