package com.example.hw_3.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@Composable
fun ContinueWithoutAccountScreen(
    onBackClick: () -> Unit,
    onContinueClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .background(AppColors.Secondary)  // F4FBF4
            .padding(WindowInsets(0, 0, 0, 0).asPaddingValues())
    ) {
        // Стрелка "назад"
        Image(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = "Назад",
            modifier = Modifier
                .size(85.dp)
                .align(Alignment.TopStart)
                .padding(start = 0.dp, top = 60.dp)
                .clickable(onClick = onBackClick)
        )

        // Первый текстовый блок
        Text(
            text = stringResource(R.string.no_account_text_primary),
            style = TextStyle(
                fontSize = 20.sp,
                lineHeight = 28.sp,
                fontWeight = FontWeight.ExtraBold,
                color = AppColors.Main
            ),
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 16.dp, top = 225.dp)
        )

        // Второй текстовый блок
        Text(
            text = stringResource(R.string.no_account_text_secondary),
            style = TextStyle(
                fontSize = 20.sp,
                lineHeight = 28.sp,
                fontWeight = FontWeight.Medium,
                color = AppColors.Main
            ),
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 16.dp, top = 400.dp)
        )

        // Кнопка "Продолжить без аккаунта"
        Button(
            onClick = onContinueClick,
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = AppColors.Main,
                contentColor = AppColors.Secondary
            ),
            border = BorderStroke(1.dp, Color.Black),
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 601.dp)
                .width(275.dp)
                .height(60.dp)
        ) {
            Text(
                text = stringResource(R.string.start_continue_without_account),
                fontSize = 16.sp
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ContinueWithoutAccountPreview() {
    Hw3Theme {
        ContinueWithoutAccountScreen(
            onBackClick = {},
            onContinueClick = {}
        )
    }
}
