package com.example.anthar_jalawatch.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

import kotlinx.coroutines.delay

import com.example.anthar_jalawatch.R

@Composable
fun SplashScreen(

    onSplashFinished: () -> Unit
) {

    LaunchedEffect(Unit) {

        delay(2500)

        onSplashFinished()
    }

    Box(

        modifier = Modifier

            .fillMaxSize()

            .background(

                brush = Brush.verticalGradient(

                    colors = listOf(

                        Color(0xFF1565C0),

                        Color(0xFF42A5F5)
                    )
                )
            ),

        contentAlignment =
            Alignment.Center
    ) {

        Image(

            painter =
                painterResource(
                    id = R.drawable.logo
                ),

            contentDescription =
                "Logo",

            modifier = Modifier
                .size(220.dp)
        )
    }
}