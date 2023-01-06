package com.peterchege.gamesapp.presentation.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.peterchege.gamesapp.data.api.responses.single_game_model.PlatformXX
import com.peterchege.gamesapp.util.Screens

@Composable
fun GamePlatformCard(
    modifier: Modifier = Modifier,
    platform:PlatformXX

    ) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .border(
                width = 1.dp,
                color = Color.White,
                shape = RoundedCornerShape(5.dp),
            )
            .padding(6.dp)

    ) {
        Text(
            text = platform.platform.name,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            color = Color.White,
        )
    }
}