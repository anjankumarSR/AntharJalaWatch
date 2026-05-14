package com.example.anthar_jalawatch.ui.screens

import android.app.Activity

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.material3.Button
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.anthar_jalawatch.R

import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

@Composable
fun GoogleSignInScreen(

    onLoginSuccess: () -> Unit

) {

    val context = LocalContext.current

    val activity = context as Activity

    val auth = FirebaseAuth.getInstance()

    val gso =

        GoogleSignInOptions.Builder(
            GoogleSignInOptions.DEFAULT_SIGN_IN
        )

            .requestIdToken(
                activity.getString(
                    R.string.default_web_client_id
                )
            )

            .requestEmail()

            .build()

    val googleSignInClient =

        GoogleSignIn.getClient(
            activity,
            gso
        )

    val launcher =

        rememberLauncherForActivityResult(

            contract =
                ActivityResultContracts.StartActivityForResult()

        ) { result ->

            val task =

                GoogleSignIn
                    .getSignedInAccountFromIntent(
                        result.data
                    )

            if (task.isSuccessful) {

                val account =
                    task.result

                val credential =

                    GoogleAuthProvider
                        .getCredential(
                            account.idToken,
                            null
                        )

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
            ),

        contentAlignment =
            Alignment.Center
    ) {

        Button(

            onClick = {

                launcher.launch(
                    googleSignInClient.signInIntent
                )
            },

            modifier = Modifier

                .fillMaxWidth()

                .padding(24.dp)

                .height(58.dp)
        ) {

            Text(

                text =
                    "Sign In with Google",

                fontSize = 18.sp
            )
        }
    }
}