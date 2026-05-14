package com.example.anthar_jalawatch.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text

import androidx.compose.runtime.*

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun RechargeScreen(

    onBackClick: () -> Unit
) {

    val db = FirebaseFirestore.getInstance()

    var rechargeGuide by remember {

        mutableStateOf(
            "Loading recharge guide..."
        )
    }

    var waterStatus by remember {

        mutableStateOf("")
    }

    // FETCH DATA
    LaunchedEffect(Unit) {

        db.collection("borewells")

            .get()

            .addOnSuccessListener { result ->

                if (
                    result.documents.isNotEmpty()
                ) {

                    val latestData =

                        result.documents.last()

                    val yield =

                        latestData
                            .getString("yield")
                            ?.toIntOrNull() ?: 0

                    waterStatus =

                        latestData
                            .getString(
                                "waterStatus"
                            ) ?: ""

                    rechargeGuide = when {

                        yield >= 1000 -> {

                            "✅ Water level is good.\n\nMaintain recharge pits and rainwater harvesting systems regularly."
                        }

                        yield >= 500 -> {

                            "⚠️ Medium water level detected.\n\nRecommended recharge methods:\n\n• Rainwater harvesting\n• Recharge trench\n• Rooftop water collection\n• Percolation pit"
                        }

                        else -> {

                            "🚨 Critical groundwater level detected.\n\nImmediate recharge required:\n\n• Deep recharge bore\n• Check dam construction\n• Farm pond recharge\n• Recharge shaft\n• Rainwater storage system"
                        }
                    }

                } else {

                    rechargeGuide =

                        "No borewell data found"
                }
            }
    }

    Column(

        modifier = Modifier

            .fillMaxSize()

            .background(

                Brush.verticalGradient(

                    colors = listOf(

                        Color(0xFFE8F5E9),

                        Color.White
                    )
                )
            )

            .verticalScroll(
                rememberScrollState()
            )

            .padding(16.dp)
    ) {

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        // TITLE
        Text(

            text = "♻️ Recharge Guide",

            fontSize = 32.sp,

            fontWeight = FontWeight.Bold,

            color = Color(0xFF1B5E20)
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(

            text =
                "Smart groundwater recharge recommendations",

            fontSize = 16.sp,

            color = Color.DarkGray
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        // STATUS CARD
        Card(

            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(24.dp),

            elevation =
                CardDefaults.cardElevation(
                    defaultElevation = 10.dp
                ),

            colors = CardDefaults.cardColors(

                containerColor =
                    Color(0xFFC8E6C9)
            )
        ) {

            Column(

                modifier =
                    Modifier.padding(20.dp)
            ) {

                Text(

                    text = "Current Water Status",

                    fontSize = 22.sp,

                    fontWeight = FontWeight.Bold,

                    color = Color(0xFF1B5E20)
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Text(

                    text = waterStatus,

                    fontSize = 18.sp,

                    color = Color.Black
                )
            }
        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        // GUIDE CARD
        Card(

            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(24.dp),

            elevation =
                CardDefaults.cardElevation(
                    defaultElevation = 10.dp
                )
        ) {

            Column(

                modifier =
                    Modifier.padding(20.dp)
            ) {

                Text(

                    text = "Recharge Recommendation",

                    fontSize = 24.sp,

                    fontWeight = FontWeight.Bold,

                    color = Color(0xFF1565C0)
                )

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                Text(

                    text = rechargeGuide,

                    fontSize = 17.sp,

                    lineHeight = 28.sp,

                    color = Color.DarkGray
                )
            }
        }

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        // EXTRA METHODS
        Text(

            text = "Popular Recharge Methods",

            fontSize = 24.sp,

            fontWeight = FontWeight.Bold,

            color = Color(0xFF0D47A1)
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        RechargeMethodCard(

            title = "🌧 Rainwater Harvesting",

            description =
                "Collect and store rainwater for groundwater recharge"
        )

        RechargeMethodCard(

            title = "🕳 Recharge Pit",

            description =
                "Allows rainwater to seep into underground layers"
        )

        RechargeMethodCard(

            title = "🏞 Farm Pond",

            description =
                "Stores excess rainwater and improves groundwater level"
        )

        RechargeMethodCard(

            title = "🚰 Rooftop Collection",

            description =
                "Direct rooftop rainwater into recharge systems"
        )

        Spacer(
            modifier = Modifier.height(30.dp)
        )
    }
}

@Composable
fun RechargeMethodCard(

    title: String,

    description: String
) {

    Card(

        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 14.dp),

        shape = RoundedCornerShape(22.dp),

        elevation =
            CardDefaults.cardElevation(
                defaultElevation = 8.dp
            )
    ) {

        Column(

            modifier =
                Modifier.padding(20.dp)
        ) {

            Text(

                text = title,

                fontSize = 20.sp,

                fontWeight = FontWeight.Bold,

                color = Color(0xFF1565C0)
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            Text(

                text = description,

                fontSize = 16.sp,

                color = Color.DarkGray
            )
        }
    }
}