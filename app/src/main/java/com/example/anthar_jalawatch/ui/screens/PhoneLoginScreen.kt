package com.example.anthar_jalawatch.ui.screens

import android.app.Activity

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.*

import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.layout.ContentScale

import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.res.painterResource

import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.anthar_jalawatch.R

import com.google.firebase.FirebaseException
import com.google.firebase.auth.*

import java.util.concurrent.TimeUnit

@Composable
fun PhoneLoginScreen(

    onLoginSuccess: () -> Unit

) {

    val context = LocalContext.current

    val activity = context as Activity

    val auth = FirebaseAuth.getInstance()

    var phoneNumber by remember {

        mutableStateOf("")
    }

    var otp by remember {

        mutableStateOf("")
    }

    var verificationId by remember {

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
                    .size(110.dp),

                contentScale =
                    ContentScale.Fit
            )

            Spacer(
                modifier =
                    Modifier.height(20.dp)
            )

            Text(

                text =
                    "Phone Login",

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
                    "Login securely using OTP",

                color = Color.Gray
            )

            Spacer(
                modifier =
                    Modifier.height(35.dp)
            )

            // LOGIN CARD
            Card(

                modifier = Modifier
                    .fillMaxWidth(),

                shape =
                    RoundedCornerShape(28.dp),

                colors =
                    CardDefaults.cardColors(
                        containerColor =
                            Color.White
                    ),

                elevation =
                    CardDefaults.cardElevation(
                        defaultElevation = 8.dp
                    )
            ) {

                Column(

                    modifier = Modifier
                        .padding(24.dp)
                ) {

                    // PHONE FIELD
                    OutlinedTextField(

                        value = phoneNumber,

                        onValueChange = {

                            phoneNumber = it
                        },

                        label = {

                            Text("Phone Number")
                        },

                        modifier =
                            Modifier.fillMaxWidth(),

                        shape =
                            RoundedCornerShape(16.dp)
                    )

                    Spacer(
                        modifier =
                            Modifier.height(20.dp)
                    )

                    // SEND OTP BUTTON
                    Button(

                        onClick = {

                            val options =

                                PhoneAuthOptions.newBuilder(auth)

                                    .setPhoneNumber(
                                        phoneNumber
                                    )

                                    .setTimeout(
                                        60L,
                                        TimeUnit.SECONDS
                                    )

                                    .setActivity(activity)

                                    .setCallbacks(

                                        object :
                                            PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                                            override fun onVerificationCompleted(

                                                credential:
                                                PhoneAuthCredential
                                            ) {

                                                auth
                                                    .signInWithCredential(
                                                        credential
                                                    )

                                                    .addOnCompleteListener {

                                                        if (it.isSuccessful) {

                                                            onLoginSuccess()
                                                        }
                                                    }
                                            }

                                            override fun onVerificationFailed(

                                                e: FirebaseException
                                            ) {

                                                message =
                                                    e.message.toString()
                                            }

                                            override fun onCodeSent(

                                                id: String,

                                                token:
                                                PhoneAuthProvider
                                                .ForceResendingToken
                                            ) {

                                                verificationId = id

                                                message =
                                                    "OTP Sent Successfully"
                                            }
                                        }

                                    )

                                    .build()

                            PhoneAuthProvider
                                .verifyPhoneNumber(
                                    options
                                )
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

                            text = "Send OTP",

                            fontSize = 18.sp
                        )
                    }

                    Spacer(
                        modifier =
                            Modifier.height(24.dp)
                    )

                    // OTP FIELD
                    OutlinedTextField(

                        value = otp,

                        onValueChange = {

                            otp = it
                        },

                        label = {

                            Text("Enter OTP")
                        },

                        modifier =
                            Modifier.fillMaxWidth(),

                        shape =
                            RoundedCornerShape(16.dp)
                    )

                    Spacer(
                        modifier =
                            Modifier.height(24.dp)
                    )

                    // VERIFY BUTTON
                    Button(

                        onClick = {

                            val credential =

                                PhoneAuthProvider
                                    .getCredential(
                                        verificationId,
                                        otp
                                    )

                            auth
                                .signInWithCredential(
                                    credential
                                )

                                .addOnCompleteListener {

                                    if (it.isSuccessful) {

                                        onLoginSuccess()

                                    } else {

                                        message =
                                            "Invalid OTP"
                                    }
                                }
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

                            text = "Verify OTP",

                            fontSize = 18.sp
                        )
                    }

                    Spacer(
                        modifier =
                            Modifier.height(20.dp)
                    )

                    Text(

                        text = message,

                        color = Color.Red
                    )
                }
            }
        }
    }
}