package com.example.anthar_jalawatch.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock

import androidx.compose.material3.*

import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation

import androidx.compose.foundation.text.KeyboardOptions

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.google.firebase.auth.FirebaseAuth

@Composable
fun EmailLoginScreen(

    onLoginSuccess: () -> Unit,

    onCreateAccountClick: () -> Unit,

    onBackClick: () -> Unit,

    onGoogleClick: () -> Unit

) {

    val auth = FirebaseAuth.getInstance()

    var email by remember {

        mutableStateOf("")
    }

    var password by remember {

        mutableStateOf("")
    }

    var message by remember {

        mutableStateOf("")
    }

    Box(

        modifier = Modifier

            .fillMaxSize()

            .background(

                brush = Brush.verticalGradient(

                    colors = listOf(

                        Color(0xFF0F2027),

                        Color(0xFF203A43),

                        Color(0xFF2C5364)
                    )
                )
            )
    ) {

        Column(

            modifier = Modifier

                .fillMaxSize()

                .verticalScroll(
                    rememberScrollState()
                )

                .padding(24.dp)
        ) {

            Spacer(
                modifier =
                    Modifier.height(20.dp)
            )

            // BACK BUTTON
            IconButton(

                onClick = {

                    onBackClick()
                }
            ) {

                Icon(

                    imageVector =
                        Icons.Default.ArrowBack,

                    contentDescription =
                        "Back",

                    tint = Color.White
                )
            }

            Spacer(
                modifier =
                    Modifier.height(20.dp)
            )

            // TITLE
            Text(

                text = "Welcome Back",

                color = Color.White,

                fontSize = 34.sp,

                fontWeight =
                    FontWeight.Bold
            )

            Spacer(
                modifier =
                    Modifier.height(8.dp)
            )

            // DESCRIPTION
            Text(

                text =
                    "Login to continue using Anthar-Jala Watch",

                color = Color(0xFFD1D5DB),

                fontSize = 15.sp
            )

            Spacer(
                modifier =
                    Modifier.height(40.dp)
            )

            // EMAIL FIELD
            OutlinedTextField(

                value = email,

                onValueChange = {

                    email = it
                },

                label = {

                    Text("Email")
                },

                leadingIcon = {

                    Icon(

                        imageVector =
                            Icons.Default.Email,

                        contentDescription = null,

                        tint =
                            Color(0xFF38BDF8)
                    )
                },

                modifier = Modifier

                    .fillMaxWidth()

                    .height(58.dp),

                shape =
                    RoundedCornerShape(14.dp),

                keyboardOptions =
                    KeyboardOptions(

                        keyboardType =
                            KeyboardType.Email
                    ),

                colors =
                    OutlinedTextFieldDefaults.colors(

                        focusedBorderColor =
                            Color(0xFF38BDF8),

                        unfocusedBorderColor =
                            Color.Gray,

                        focusedLabelColor =
                            Color(0xFF38BDF8),

                        unfocusedLabelColor =
                            Color.LightGray,

                        focusedTextColor =
                            Color.White,

                        unfocusedTextColor =
                            Color.White,

                        cursorColor =
                            Color.White
                    )
            )

            Spacer(
                modifier =
                    Modifier.height(20.dp)
            )

            // PASSWORD FIELD
            OutlinedTextField(

                value = password,

                onValueChange = {

                    password = it
                },

                label = {

                    Text("Password")
                },

                leadingIcon = {

                    Icon(

                        imageVector =
                            Icons.Default.Lock,

                        contentDescription = null,

                        tint =
                            Color(0xFF22C55E)
                    )
                },

                visualTransformation =
                    PasswordVisualTransformation(),

                modifier = Modifier

                    .fillMaxWidth()

                    .height(58.dp),

                shape =
                    RoundedCornerShape(14.dp),

                colors =
                    OutlinedTextFieldDefaults.colors(

                        focusedBorderColor =
                            Color(0xFF22C55E),

                        unfocusedBorderColor =
                            Color.Gray,

                        focusedLabelColor =
                            Color(0xFF22C55E),

                        unfocusedLabelColor =
                            Color.LightGray,

                        focusedTextColor =
                            Color.White,

                        unfocusedTextColor =
                            Color.White,

                        cursorColor =
                            Color.White
                    )
            )

            Spacer(
                modifier =
                    Modifier.height(10.dp)
            )

            // FORGOT PASSWORD
            Text(

                text =
                    "Forgot Password?",

                color =
                    Color(0xFF38BDF8),

                fontSize = 13.sp,

                modifier = Modifier

                    .align(
                        Alignment.End
                    )

                    .clickable {

                        if (email.isBlank()) {

                            message =
                                "Enter your email first"

                        } else {

                            auth.sendPasswordResetEmail(

                                email.trim()
                            )

                                .addOnSuccessListener {

                                    message =
                                        "Password reset email sent"
                                }

                                .addOnFailureListener {

                                    message =
                                        it.message.toString()
                                }
                        }
                    }
            )

            Spacer(
                modifier =
                    Modifier.height(30.dp)
            )

            // LOGIN BUTTON
            Button(

                onClick = {

                    if (

                        email.isBlank() ||
                        password.isBlank()

                    ) {

                        message =
                            "Enter Email and Password"

                        return@Button
                    }

                    auth.signInWithEmailAndPassword(

                        email.trim(),

                        password.trim()

                    )

                        .addOnSuccessListener {

                            message =
                                "Login Successful"

                            onLoginSuccess()
                        }

                        .addOnFailureListener {

                            message =
                                it.message.toString()
                        }
                },

                modifier = Modifier

                    .fillMaxWidth()

                    .height(58.dp),

                shape =
                    RoundedCornerShape(16.dp),

                colors =
                    ButtonDefaults.buttonColors(

                        containerColor =
                            Color(0xFF22C55E)
                    )
            ) {

                Text(

                    text = "Sign In",

                    color = Color.White,

                    fontSize = 18.sp
                )
            }

            Spacer(
                modifier =
                    Modifier.height(16.dp)
            )

            // CREATE ACCOUNT BUTTON
            OutlinedButton(

                onClick = {

                    onCreateAccountClick()
                },

                modifier = Modifier

                    .fillMaxWidth()

                    .height(58.dp),

                shape =
                    RoundedCornerShape(16.dp),

                colors =
                    ButtonDefaults.outlinedButtonColors(

                        contentColor =
                            Color.White
                    )

            ) {

                Text(
                    "Create Account"
                )
            }

            Spacer(
                modifier =
                    Modifier.height(20.dp)
            )

            // GOOGLE BUTTON
            OutlinedButton(

                onClick = {

                    onGoogleClick()
                },

                modifier = Modifier

                    .fillMaxWidth()

                    .height(58.dp),

                shape =
                    RoundedCornerShape(16.dp),

                colors =
                    ButtonDefaults.outlinedButtonColors(

                        contentColor =
                            Color.White
                    )
            ) {

                Text(

                    text =
                        "Continue with Google",

                    fontSize = 16.sp
                )
            }

            Spacer(
                modifier =
                    Modifier.height(20.dp)
            )

            // MESSAGE
            Text(

                text = message,

                color = Color.Yellow,

                fontSize = 14.sp
            )
        }
    }
}