package com.example.anthar_jalawatch.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack

import androidx.compose.material3.*

import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.input.PasswordVisualTransformation

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.google.firebase.auth.FirebaseAuth

@Composable
fun RegisterScreen(

    onRegisterSuccess: () -> Unit,

    onBackClick: () -> Unit

) {

    val auth = FirebaseAuth.getInstance()

    var fullName by remember {

        mutableStateOf("")
    }

    var phone by remember {

        mutableStateOf("")
    }

    var email by remember {

        mutableStateOf("")
    }

    var password by remember {

        mutableStateOf("")
    }

    var confirmPassword by remember {

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

                text =
                    "Create Account",

                color = Color.White,

                fontSize = 34.sp
            )

            Spacer(
                modifier =
                    Modifier.height(40.dp)
            )

            // COMMON COLORS
            val fieldColors =
                OutlinedTextFieldDefaults.colors(

                    focusedBorderColor =
                        Color(0xFF8B5CF6),

                    unfocusedBorderColor =
                        Color.Gray,

                    focusedLabelColor =
                        Color(0xFF8B5CF6),

                    unfocusedLabelColor =
                        Color.LightGray,

                    focusedTextColor =
                        Color.White,

                    unfocusedTextColor =
                        Color.White,

                    cursorColor =
                        Color.White
                )

            // FULL NAME
            OutlinedTextField(

                value = fullName,

                onValueChange = {

                    fullName = it
                },

                label = {

                    Text("Full Name")
                },

                modifier = Modifier

                    .fillMaxWidth()

                    .height(64.dp),

                shape =
                    RoundedCornerShape(14.dp),

                colors = fieldColors
            )

            Spacer(
                modifier =
                    Modifier.height(20.dp)
            )

            // PHONE
            OutlinedTextField(

                value = phone,

                onValueChange = {

                    phone = it
                },

                label = {

                    Text("Phone Number")
                },

                modifier = Modifier

                    .fillMaxWidth()

                    .height(64.dp),

                shape =
                    RoundedCornerShape(14.dp),

                colors = fieldColors
            )

            Spacer(
                modifier =
                    Modifier.height(20.dp)
            )

            // EMAIL
            OutlinedTextField(

                value = email,

                onValueChange = {

                    email = it
                },

                label = {

                    Text("Email")
                },

                modifier = Modifier

                    .fillMaxWidth()

                    .height(64.dp),

                shape =
                    RoundedCornerShape(14.dp),

                colors = fieldColors
            )

            Spacer(
                modifier =
                    Modifier.height(20.dp)
            )

            // PASSWORD
            OutlinedTextField(

                value = password,

                onValueChange = {

                    password = it
                },

                label = {

                    Text("Password")
                },

                visualTransformation =
                    PasswordVisualTransformation(),

                modifier = Modifier

                    .fillMaxWidth()

                    .height(64.dp),

                shape =
                    RoundedCornerShape(14.dp),

                colors = fieldColors
            )

            Spacer(
                modifier =
                    Modifier.height(20.dp)
            )

            // CONFIRM PASSWORD
            OutlinedTextField(

                value = confirmPassword,

                onValueChange = {

                    confirmPassword = it
                },

                label = {

                    Text("Confirm Password")
                },

                visualTransformation =
                    PasswordVisualTransformation(),

                modifier = Modifier

                    .fillMaxWidth()

                    .height(64.dp),

                shape =
                    RoundedCornerShape(14.dp),

                colors = fieldColors
            )

            Spacer(
                modifier =
                    Modifier.height(40.dp)
            )

            // CREATE ACCOUNT BUTTON
            Button(

                onClick = {

                    if (

                        fullName.isBlank() ||
                        phone.isBlank() ||
                        email.isBlank() ||
                        password.isBlank() ||
                        confirmPassword.isBlank()

                    ) {

                        message =
                            "Fill all fields"

                        return@Button
                    }

                    if (

                        password != confirmPassword
                    ) {

                        message =
                            "Passwords do not match"

                        return@Button
                    }

                    auth.createUserWithEmailAndPassword(

                        email.trim(),

                        password.trim()

                    )

                        .addOnSuccessListener {

                            message =
                                "Account Created"

                            onRegisterSuccess()
                        }

                        .addOnFailureListener {

                            message =
                                it.message.toString()
                        }
                },

                modifier = Modifier

                    .fillMaxWidth()

                    .height(60.dp),

                shape =
                    RoundedCornerShape(20.dp),

                colors =
                    ButtonDefaults.buttonColors(

                        containerColor =
                            Color(0xFF8B5CF6)
                    )
            ) {

                Text(

                    text =
                        "Create Account",

                    fontSize = 18.sp,

                    color = Color.White
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