package com.example.anthar_jalawatch.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text

import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.google.firebase.firestore.FirebaseFirestore

data class AlertItem(

    val title: String,

    val description: String,

    val suggestion: String,

    val color: Color,

    val emoji: String
)

@Composable
fun AlertScreen() {

    val db = FirebaseFirestore.getInstance()

    var alerts by remember {

        mutableStateOf<List<AlertItem>>(emptyList())
    }

    // FETCH FIREBASE
    LaunchedEffect(Unit) {

        db.collection("borewells")

            .get()

            .addOnSuccessListener { result ->

                val alertList =
                    mutableListOf<AlertItem>()

                for (document in result.documents) {

                    val waterStatus =
                        document.getString(
                            "waterStatus"
                        ) ?: ""

                    val villageName =
                        document.getString(
                            "villageName"
                        ) ?: ""

                    when {

                        waterStatus.contains(
                            "Good",
                            true
                        ) -> {

                            alertList.add(

                                AlertItem(

                                    title =
                                        "Safe Water Level",

                                    description =
                                        "Groundwater stable in $villageName.",

                                    suggestion =
                                        "Maintain recharge pits\nContinue rainwater harvesting",

                                    color =
                                        Color(0xFFC8E6C9),

                                    emoji = "✅"
                                )
                            )
                        }

                        waterStatus.contains(
                            "Medium",
                            true
                        ) -> {

                            alertList.add(

                                AlertItem(

                                    title =
                                        "Medium Water Level",

                                    description =
                                        "Water level reducing in $villageName.",

                                    suggestion =
                                        "Build recharge pit\nReduce excess usage\nMonitor weekly",

                                    color =
                                        Color(0xFFFFF9C4),

                                    emoji = "⚠️"
                                )
                            )
                        }

                        else -> {

                            alertList.add(

                                AlertItem(

                                    title =
                                        "Critical Water Level",

                                    description =
                                        "Critical groundwater condition in $villageName.",

                                    suggestion =
                                        "Deep recharge bore\nFarm pond\nRainwater storage",

                                    color =
                                        Color(0xFFFFCDD2),

                                    emoji = "🚨"
                                )
                            )
                        }
                    }
                }

                alerts = alertList
            }
    }

    LazyColumn(

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

            .padding(

                start = 14.dp,
                top = 14.dp,
                end = 14.dp,
                bottom = 90.dp
            ),

        verticalArrangement =
            Arrangement.spacedBy(14.dp)

    ) {

        // HEADER
        item {

            Column {

                Text(

                    text =
                        "🚨 Smart Alerts",

                    fontSize = 28.sp,

                    fontWeight =
                        FontWeight.Bold,

                    color =
                        Color(0xFF0D47A1)
                )

                Spacer(
                    modifier =
                        Modifier.height(4.dp)
                )

                Text(

                    text =
                        "Live groundwater monitoring",

                    fontSize = 14.sp,

                    color = Color.DarkGray
                )
            }
        }

        // ALERT CARDS
        items(alerts) { alert ->

            Card(

                modifier = Modifier
                    .fillMaxWidth(),

                shape =
                    RoundedCornerShape(22.dp),

                colors =
                    CardDefaults.cardColors(

                        containerColor =
                            alert.color
                    ),

                elevation =
                    CardDefaults.cardElevation(
                        defaultElevation = 4.dp
                    )
            ) {

                Column(

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp)
                ) {

                    // TOP ROW
                    Row(

                        verticalAlignment =
                            Alignment.CenterVertically
                    ) {

                        Text(

                            text =
                                alert.emoji,

                            fontSize = 34.sp
                        )

                        Spacer(
                            modifier =
                                Modifier.width(12.dp)
                        )

                        Column {

                            Text(

                                text =
                                    alert.title,

                                fontSize = 20.sp,

                                fontWeight =
                                    FontWeight.Bold
                            )

                            Text(

                                text =
                                    "Groundwater Alert",

                                fontSize = 12.sp,

                                color = Color.DarkGray
                            )
                        }
                    }

                    Spacer(
                        modifier =
                            Modifier.height(12.dp)
                    )

                    // DESCRIPTION
                    Text(

                        text =
                            alert.description,

                        fontSize = 14.sp,

                        lineHeight = 20.sp
                    )

                    Spacer(
                        modifier =
                            Modifier.height(12.dp)
                    )

                    // SUGGESTION BOX
                    Card(

                        modifier = Modifier
                            .fillMaxWidth(),

                        colors =
                            CardDefaults.cardColors(

                                containerColor =
                                    Color.White
                            ),

                        shape =
                            RoundedCornerShape(14.dp),

                        elevation =
                            CardDefaults.cardElevation(
                                defaultElevation = 2.dp
                            )
                    ) {

                        Column(

                            modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp)
                        ) {

                            Text(

                                text =
                                    "Suggested Actions",

                                fontWeight =
                                    FontWeight.Bold,

                                fontSize = 15.sp,

                                color =
                                    Color(0xFF1565C0)
                            )

                            Spacer(
                                modifier =
                                    Modifier.height(8.dp)
                            )

                            // SPLIT SUGGESTIONS
                            val suggestions =

                                alert.suggestion.split("\n")

                            suggestions.forEach { item ->

                                if (item.isNotBlank()) {

                                    Row(

                                        modifier =
                                            Modifier
                                                .fillMaxWidth()
                                                .padding(bottom = 6.dp),

                                        verticalAlignment =
                                            Alignment.Top
                                    ) {

                                        Text(

                                            text = "• ",

                                            fontSize = 13.sp,

                                            color = Color.Black
                                        )

                                        Text(

                                            text =
                                                item.trim(),

                                            fontSize = 13.sp,

                                            lineHeight = 18.sp,

                                            color = Color.Black,

                                            modifier =
                                                Modifier.weight(1f)
                                        )
                                    }
                                }
                            }
                        }
                    }

                    Spacer(
                        modifier =
                            Modifier.height(14.dp)
                    )

                    // STATUS BAR
                    Box(

                        modifier = Modifier

                            .fillMaxWidth()

                            .height(8.dp)

                            .background(

                                color = when {

                                    alert.title.contains(
                                        "Critical"
                                    ) ->

                                        Color.Red

                                    alert.title.contains(
                                        "Medium"
                                    ) ->

                                        Color(0xFFFFC107)

                                    else ->

                                        Color(0xFF4CAF50)
                                },

                                shape =
                                    RoundedCornerShape(
                                        50.dp
                                    )
                            )
                    )
                }
            }
        }

        // EXTRA SPACE
        item {

            Spacer(
                modifier =
                    Modifier.height(120.dp)
            )
        }
    }
}