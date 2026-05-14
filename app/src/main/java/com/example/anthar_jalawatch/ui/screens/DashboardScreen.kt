package com.example.anthar_jalawatch.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun DashboardScreen(

    onBorewellClick: () -> Unit,

    onWaterMapClick: () -> Unit,

    onRechargeClick: () -> Unit,

    onAlertClick: () -> Unit
) {

    // FIREBASE
    val db = FirebaseFirestore.getInstance()

    // LIVE STATS
    var totalBorewells by remember {

        mutableStateOf(0)
    }

    var safeCount by remember {

        mutableStateOf(0)
    }

    var mediumCount by remember {

        mutableStateOf(0)
    }

    var criticalCount by remember {

        mutableStateOf(0)
    }

    // LOAD LIVE DATA
    LaunchedEffect(Unit) {

        db.collection("borewells")

            .get()

            .addOnSuccessListener { result ->

                totalBorewells =
                    result.size()

                var safe = 0
                var medium = 0
                var critical = 0

                result.documents.forEach { document ->

                    val status =

                        document.getString(
                            "waterStatus"
                        ) ?: ""

                    when {

                        status.contains(
                            "Good",
                            true
                        ) -> safe++

                        status.contains(
                            "Medium",
                            true
                        ) -> medium++

                        else -> critical++
                    }
                }

                safeCount = safe
                mediumCount = medium
                criticalCount = critical
            }
    }

    Column(

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

            .verticalScroll(
                rememberScrollState()
            )

            .padding(

                start = 18.dp,
                top = 18.dp,
                end = 18.dp,
                bottom = 100.dp
            )
    ) {

        Spacer(
            modifier =
                Modifier.height(10.dp)
        )

        // TITLE
        Text(

            text =
                "💧 Anthar-Jala Watch",

            fontSize = 32.sp,

            fontWeight =
                FontWeight.ExtraBold,

            color =
                Color(0xFF0D47A1)
        )

        Spacer(
            modifier =
                Modifier.height(8.dp)
        )

        // SUBTITLE
        Text(

            text =
                "Natural Resources Monitoring System",

            style =
                MaterialTheme.typography.bodyLarge,

            color =
                Color.DarkGray
        )

        Spacer(
            modifier =
                Modifier.height(24.dp)
        )

        // LIVE STATUS CARD
        Card(

            modifier =
                Modifier.fillMaxWidth(),

            shape =
                RoundedCornerShape(28.dp),

            elevation =
                CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                ),

            colors =
                CardDefaults.cardColors(

                    containerColor =
                        Color(0xFF1565C0)
                )
        ) {

            Column(

                modifier =
                    Modifier.padding(22.dp)
            ) {

                Text(

                    text =
                        "🌍 Groundwater Status",

                    fontSize = 24.sp,

                    fontWeight =
                        FontWeight.Bold,

                    color = Color.White
                )

                Spacer(
                    modifier =
                        Modifier.height(10.dp)
                )

                Text(

                    text =
                        "Total Borewells: $totalBorewells",

                    fontSize = 18.sp,

                    fontWeight =
                        FontWeight.Bold,

                    color = Color.White
                )

                Spacer(
                    modifier =
                        Modifier.height(18.dp)
                )

                Row(

                    modifier =
                        Modifier.fillMaxWidth(),

                    horizontalArrangement =
                        Arrangement.SpaceBetween
                ) {

                    DashboardMiniCard(

                        title = "Safe",

                        value = safeCount.toString(),

                        color =
                            Color(0xFF66BB6A)
                    )

                    DashboardMiniCard(

                        title = "Medium",

                        value = mediumCount.toString(),

                        color =
                            Color(0xFFFFC107)
                    )

                    DashboardMiniCard(

                        title = "Critical",

                        value = criticalCount.toString(),

                        color =
                            Color(0xFFEF5350)
                    )
                }
            }
        }

        Spacer(
            modifier =
                Modifier.height(26.dp)
        )

        // FEATURE TITLE
        Text(

            text =
                "Smart Features",

            fontSize = 28.sp,

            fontWeight =
                FontWeight.Bold,

            color =
                Color(0xFF0D47A1)
        )

        Spacer(
            modifier =
                Modifier.height(18.dp)
        )

        // BOREWELL CARD
        DashboardCard(

            emoji = "💧",

            title = "Borewell Log",

            description =
                "Add and manage groundwater records",

            color = Color(0xFF42A5F5),

            onClick = onBorewellClick
        )

        Spacer(
            modifier =
                Modifier.height(16.dp)
        )

        // MAP CARD
        DashboardCard(

            emoji = "🗺️",

            title = "Water Stress Map",

            description =
                "View groundwater condition on live map",

            color = Color(0xFF26A69A),

            onClick = onWaterMapClick
        )

        Spacer(
            modifier =
                Modifier.height(16.dp)
        )

        // RECHARGE CARD
        DashboardCard(

            emoji = "♻️",

            title = "Recharge Guide",

            description =
                "Smart groundwater recharge suggestions",

            color = Color(0xFF66BB6A),

            onClick = onRechargeClick
        )

        Spacer(
            modifier =
                Modifier.height(16.dp)
        )

        // ALERT CARD
        DashboardCard(

            emoji = "🚨",

            title = "Alerts",

            description =
                "Groundwater warning system",

            color = Color(0xFFEF5350),

            onClick = onAlertClick
        )

        Spacer(
            modifier =
                Modifier.height(30.dp)
        )
    }
}

@Composable
fun DashboardMiniCard(

    title: String,

    value: String,

    color: Color
) {

    Card(

        shape =
            RoundedCornerShape(18.dp),

        colors =
            CardDefaults.cardColors(

                containerColor = color
            )
    ) {

        Column(

            modifier =
                Modifier
                    .padding(

                        horizontal = 18.dp,
                        vertical = 14.dp
                    ),

            horizontalAlignment =
                Alignment.CenterHorizontally
        ) {

            Text(

                text = value,

                fontSize = 20.sp,

                fontWeight =
                    FontWeight.Bold,

                color = Color.White
            )

            Spacer(
                modifier =
                    Modifier.height(4.dp)
            )

            Text(

                text = title,

                fontSize = 13.sp,

                color = Color.White
            )
        }
    }
}

@Composable
fun DashboardCard(

    emoji: String,

    title: String,

    description: String,

    color: Color,

    onClick: () -> Unit
) {

    Card(

        modifier = Modifier

            .fillMaxWidth()

            .height(125.dp)

            .clickable {

                onClick()
            },

        shape =
            RoundedCornerShape(26.dp),

        elevation =
            CardDefaults.cardElevation(
                defaultElevation = 8.dp
            ),

        colors =
            CardDefaults.cardColors(

                containerColor = color
            )
    ) {

        Row(

            modifier = Modifier

                .fillMaxSize()

                .padding(20.dp),

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            // ICON
            Text(

                text = emoji,

                fontSize = 46.sp
            )

            Spacer(
                modifier =
                    Modifier.width(18.dp)
            )

            // TEXTS
            Column(

                verticalArrangement =
                    Arrangement.Center
            ) {

                Text(

                    text = title,

                    fontSize = 24.sp,

                    fontWeight =
                        FontWeight.Bold,

                    color = Color.White
                )

                Spacer(
                    modifier =
                        Modifier.height(6.dp)
                )

                Text(

                    text = description,

                    fontSize = 14.sp,

                    color = Color.White,

                    maxLines = 2,

                    overflow =
                        TextOverflow.Ellipsis
                )
            }
        }
    }
}