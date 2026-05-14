package com.example.anthar_jalawatch.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.*

import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.layout.ContentScale

import androidx.compose.ui.res.painterResource

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.anthar_jalawatch.R

@Composable
fun AuthChoiceScreen(

    onPhoneClick: () -> Unit,

    onEmailClick: () -> Unit

) {

    Box(

        modifier = Modifier

            .fillMaxSize()

            .background(

                brush = Brush.verticalGradient(

                    colors = listOf(

                        Color(0xFFE3F2FD),

                        Color.White
                    )
                )
            )
    ) {

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),

            horizontalAlignment =
                Alignment.CenterHorizontally,

            verticalArrangement =
                Arrangement.Center
        ) {

            // LOGO
            Image(

                painter =
                    painterResource(
                        id = R.drawable.logo
                    ),

                contentDescription =
                    "Logo",

                modifier = Modifier
                    .size(120.dp),

                contentScale =
                    ContentScale.Fit
            )

            Spacer(
                modifier =
                    Modifier.height(20.dp)
            )

            Text(

                text =
                    "Welcome Back!",

                fontSize = 30.sp,

                fontWeight =
                    FontWeight.Bold,

                color =
                    Color(0xFF1565C0)
            )

            Spacer(
                modifier =
                    Modifier.height(10.dp)
            )

            Text(

                text =
                    "Login to continue using Anthar-Jala Watch",

                fontSize = 16.sp,

                color = Color.Gray
            )

            Spacer(
                modifier =
                    Modifier.height(40.dp)
            )

            // CARD
            Card(

                modifier = Modifier
                    .fillMaxWidth(),

                shape =
                    RoundedCornerShape(30.dp),

                colors =
                    CardDefaults.cardColors(
                        containerColor = Color.White
                    ),

                elevation =
                    CardDefaults.cardElevation(
                        defaultElevation = 8.dp
                    )
            ) {

                Column(

                    modifier = Modifier
                        .padding(24.dp),

                    horizontalAlignment =
                        Alignment.CenterHorizontally
                ) {

                    // PHONE BUTTON
                    Button(

                        onClick = {
                            onPhoneClick()
                        },

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(58.dp),

                        shape =
                            RoundedCornerShape(18.dp),

                        colors =
                            ButtonDefaults.buttonColors(
                                containerColor =
                                    Color(0xFF1565C0)
                            )
                    ) {

                        Text(

                            text =
                                "Continue with Phone",

                            fontSize = 18.sp
                        )
                    }

                    Spacer(
                        modifier =
                            Modifier.height(20.dp)
                    )

                    // EMAIL BUTTON
                    OutlinedButton(

                        onClick = {
                            onEmailClick()
                        },

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(58.dp),

                        shape =
                            RoundedCornerShape(18.dp)
                    ) {

                        Text(

                            text =
                                "Continue with Email",

                            fontSize = 18.sp,

                            color =
                                Color(0xFF1565C0)
                        )
                    }

                    Spacer(
                        modifier =
                            Modifier.height(20.dp)
                    )

                    Text(

                        text =
                            "Secure Groundwater Monitoring",

                        color = Color.Gray
                    )
                }
            }
        }
    }
}