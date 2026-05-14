package com.example.anthar_jalawatch.ui.screens

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.widget.Toast

import androidx.activity.ComponentActivity

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll

import androidx.compose.material3.*

import androidx.compose.runtime.*

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.location.LocationServices

import com.google.firebase.firestore.FirebaseFirestore

import java.util.Locale

// DATA CLASS
data class BorewellData(

    val ownerName: String = "",

    val villageName: String = "",

    val depth: String = "",

    val borewellYear: String = "",

    val yield: String = "",

    val waterStatus: String = "",

    val latitude: String = "",

    val longitude: String = ""
)

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun BorewellScreen(

    onBackClick: () -> Unit
) {

    val context = LocalContext.current

    val activity = context as ComponentActivity

    val db = FirebaseFirestore.getInstance()

    val fusedLocationClient =

        LocationServices
            .getFusedLocationProviderClient(
                context
            )

    // FIREBASE LIST
    var borewellList by remember {

        mutableStateOf<List<BorewellData>>(emptyList())
    }

    // INPUTS
    var ownerName by remember {

        mutableStateOf("")
    }

    var villageName by remember {

        mutableStateOf("")
    }

    var depth by remember {

        mutableStateOf("")
    }

    var borewellYear by remember {

        mutableStateOf("")
    }

    var yield by remember {

        mutableStateOf("")
    }

    // ALERT
    var alertMessage by remember {

        mutableStateOf("")
    }

    // LOCATION
    var latitude by remember {

        mutableStateOf("")
    }

    var longitude by remember {

        mutableStateOf("")
    }

    // STATUS
    var waterStatus by remember {

        mutableStateOf("")
    }

    // LOCATION FUNCTION
    @SuppressLint("MissingPermission")

    fun getCurrentLocation() {

        if (

            ContextCompat.checkSelfPermission(

                context,

                Manifest.permission
                    .ACCESS_FINE_LOCATION

            ) == PackageManager.PERMISSION_GRANTED

        ) {

            fusedLocationClient.lastLocation

                .addOnSuccessListener {

                        location: Location? ->

                    if (location != null) {

                        latitude =
                            location.latitude.toString()

                        longitude =
                            location.longitude.toString()
                    }
                }

        } else {

            ActivityCompat.requestPermissions(

                activity,

                arrayOf(
                    Manifest.permission
                        .ACCESS_FINE_LOCATION
                ),

                100
            )
        }
    }

    // LOAD FIREBASE DATA
    fun loadBorewellData() {

        db.collection("borewells")

            .get()

            .addOnSuccessListener { result ->

                borewellList =

                    result.documents.map {

                        BorewellData(

                            ownerName =
                                it.getString("ownerName") ?: "",

                            villageName =
                                it.getString("villageName") ?: "",

                            depth =
                                it.getString("depth") ?: "",

                            borewellYear =
                                it.getString("borewellYear") ?: "",

                            yield =
                                it.getString("yield") ?: "",

                            waterStatus =
                                it.getString("waterStatus") ?: "",

                            latitude =
                                it.getString("latitude") ?: "",

                            longitude =
                                it.getString("longitude") ?: ""
                        )
                    }
            }
    }

    // INITIAL LOAD
    LaunchedEffect(Unit) {

        getCurrentLocation()

        loadBorewellData()
    }

    Scaffold(

        topBar = {

            TopAppBar(

                title = {

                    Text(

                        text =
                            "💧 Borewell Entry",

                        color = Color.White,

                        fontWeight =
                            FontWeight.Bold
                    )
                },

                colors =
                    TopAppBarDefaults
                        .topAppBarColors(

                            containerColor =
                                Color(0xFF1565C0)
                        )
            )
        }

    ) { paddingValues ->

        Column(

            modifier = Modifier

                .fillMaxSize()

                .background(

                    Brush.verticalGradient(

                        colors = listOf(

                            Color(0xFFE3F2FD),

                            Color.White
                        )
                    )
                )

                .padding(paddingValues)

                .padding(16.dp)

                .verticalScroll(
                    rememberScrollState()
                )
        ) {

            // TITLE
            Text(

                text =
                    "Groundwater Monitoring",

                fontSize = 28.sp,

                fontWeight = FontWeight.Bold,

                color = Color(0xFF0D47A1)
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(

                text =
                    "Add and manage borewell details",

                color = Color.DarkGray
            )

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            // FORM CARD
            Card(

                shape =
                    RoundedCornerShape(24.dp),

                elevation =
                    CardDefaults.cardElevation(
                        defaultElevation = 10.dp
                    )
            ) {

                Column(

                    modifier =
                        Modifier.padding(20.dp)
                ) {

                    // OWNER
                    OutlinedTextField(

                        value = ownerName,

                        onValueChange = {

                            ownerName = it
                        },

                        label = {

                            Text("Owner Name")
                        },

                        modifier =
                            Modifier.fillMaxWidth(),

                        shape =
                            RoundedCornerShape(16.dp)
                    )

                    Spacer(
                        modifier =
                            Modifier.height(16.dp)
                    )

                    // VILLAGE
                    OutlinedTextField(

                        value = villageName,

                        onValueChange = {

                            villageName = it
                        },

                        label = {

                            Text("Enter Village Name")
                        },

                        modifier =
                            Modifier.fillMaxWidth(),

                        shape =
                            RoundedCornerShape(16.dp)
                    )

                    Spacer(
                        modifier =
                            Modifier.height(16.dp)
                    )

                    // DEPTH
                    OutlinedTextField(

                        value = depth,

                        onValueChange = {

                            depth = it
                        },

                        label = {

                            Text("Borewell Depth")
                        },

                        modifier =
                            Modifier.fillMaxWidth(),

                        shape =
                            RoundedCornerShape(16.dp)
                    )

                    Spacer(
                        modifier =
                            Modifier.height(16.dp)
                    )

                    // YEAR
                    OutlinedTextField(

                        value = borewellYear,

                        onValueChange = {

                            borewellYear = it
                        },

                        label = {

                            Text("Borewell Year")
                        },

                        modifier =
                            Modifier.fillMaxWidth(),

                        shape =
                            RoundedCornerShape(16.dp)
                    )

                    Spacer(
                        modifier =
                            Modifier.height(16.dp)
                    )

                    // WATER YIELD
                    OutlinedTextField(

                        value = yield,

                        onValueChange = {

                            if (
                                it.all { char ->
                                    char.isDigit()
                                }
                            ) {

                                yield = it

                                val yieldValue =

                                    it.toIntOrNull() ?: 0

                                alertMessage = when {

                                    yieldValue >= 1000 ->

                                        "✅ Safe Water Level"

                                    yieldValue >= 500 ->

                                        "⚠️ Medium Water Level"

                                    yieldValue > 0 ->

                                        "🚨 Critical Water Level"

                                    else -> ""
                                }
                            }
                        },

                        label = {

                            Text("Water Yield")
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

                    // ALERT CARD
                    if (alertMessage.isNotEmpty()) {

                        Card(

                            colors =
                                CardDefaults.cardColors(

                                    containerColor = when {

                                        alertMessage.contains(
                                            "Safe"
                                        ) ->

                                            Color(0xFFC8E6C9)

                                        alertMessage.contains(
                                            "Medium"
                                        ) ->

                                            Color(0xFFFFF9C4)

                                        else ->

                                            Color(0xFFFFCDD2)
                                    }
                                )
                        ) {

                            Text(

                                text = alertMessage,

                                modifier =
                                    Modifier.padding(16.dp),

                                fontWeight =
                                    FontWeight.Bold
                            )
                        }
                    }

                    Spacer(
                        modifier =
                            Modifier.height(20.dp)
                    )

                    // LOCATION CARD
                    Card(

                        colors =
                            CardDefaults.cardColors(

                                containerColor =
                                    Color(0xFFE3F2FD)
                            )
                    ) {

                        Column(

                            modifier =
                                Modifier.padding(16.dp)
                        ) {

                            Text(
                                text =
                                    "📍 Latitude: $latitude"
                            )

                            Spacer(
                                modifier =
                                    Modifier.height(8.dp)
                            )

                            Text(
                                text =
                                    "📍 Longitude: $longitude"
                            )
                        }
                    }

                    Spacer(
                        modifier =
                            Modifier.height(24.dp)
                    )

                    // SAVE BUTTON
                    Button(

                        onClick = {

                            waterStatus = when {

                                yield.toIntOrNull() == null -> {

                                    "Unknown"
                                }

                                yield.toInt() >= 1000 -> {

                                    "Good Water Level 💧"
                                }

                                yield.toInt() >= 500 -> {

                                    "Medium Water Level ⚠️"
                                }

                                else -> {

                                    "Low Water Level ❌"
                                }
                            }

                            // GET LOCATION
                            try {

                                val geocoder = Geocoder(

                                    context,

                                    Locale.getDefault()
                                )

                                val addressList =

                                    geocoder.getFromLocationName(

                                        villageName,

                                        1
                                    )

                                if (!addressList.isNullOrEmpty()) {

                                    latitude =

                                        addressList[0]
                                            .latitude
                                            .toString()

                                    longitude =

                                        addressList[0]
                                            .longitude
                                            .toString()
                                }

                            } catch (e: Exception) {

                                e.printStackTrace()
                            }

                            // FIREBASE DATA
                            val borewellData = hashMapOf(

                                "ownerName" to ownerName,

                                "villageName" to villageName,

                                "depth" to depth,

                                "borewellYear" to borewellYear,

                                "yield" to yield,

                                "waterStatus" to waterStatus,

                                "latitude" to latitude,

                                "longitude" to longitude
                            )

                            // SAVE
                            db.collection("borewells")

                                .add(borewellData)

                                .addOnSuccessListener {

                                    Toast.makeText(

                                        context,

                                        "Data Saved Successfully",

                                        Toast.LENGTH_SHORT

                                    ).show()

                                    // CLEAR
                                    ownerName = ""
                                    villageName = ""
                                    depth = ""
                                    borewellYear = ""
                                    yield = ""

                                    // REFRESH
                                    loadBorewellData()
                                }

                                .addOnFailureListener {

                                    Toast.makeText(

                                        context,

                                        "Failed To Save Data",

                                        Toast.LENGTH_SHORT

                                    ).show()
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

                            text =
                                "Save Borewell Data",

                            fontSize = 18.sp
                        )
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            // SAVED DATA TITLE
            Text(

                text = "Saved Borewell Records",

                fontSize = 24.sp,

                fontWeight = FontWeight.Bold,

                color = Color(0xFF0D47A1)
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            // SHOW RECORDS
            borewellList.forEach { borewell ->

                Card(

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 14.dp),

                    shape =
                        RoundedCornerShape(22.dp),

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

                            text =
                                "👤 ${borewell.ownerName}",

                            fontWeight =
                                FontWeight.Bold,

                            fontSize = 20.sp
                        )

                        Spacer(
                            modifier =
                                Modifier.height(10.dp)
                        )

                        Text(
                            text =
                                "🏡 Village: ${borewell.villageName}"
                        )

                        Spacer(
                            modifier =
                                Modifier.height(6.dp)
                        )

                        Text(
                            text =
                                "📏 Depth: ${borewell.depth}"
                        )

                        Spacer(
                            modifier =
                                Modifier.height(6.dp)
                        )

                        Text(
                            text =
                                "💧 Yield: ${borewell.yield}"
                        )

                        Spacer(
                            modifier =
                                Modifier.height(6.dp)
                        )

                        Text(

                            text =
                                "🚨 Status: ${borewell.waterStatus}",

                            fontWeight =
                                FontWeight.Bold,

                            color = Color(0xFF1565C0)
                        )

                        Spacer(
                            modifier =
                                Modifier.height(18.dp)
                        )

                        // DELETE BUTTON
                        Button(

                            onClick = {

                                db.collection("borewells")

                                    .whereEqualTo(
                                        "ownerName",
                                        borewell.ownerName
                                    )

                                    .get()

                                    .addOnSuccessListener { documents ->

                                        for (document in documents) {

                                            db.collection("borewells")

                                                .document(document.id)

                                                .delete()
                                        }

                                        // REFRESH
                                        loadBorewellData()

                                        Toast.makeText(

                                            context,

                                            "Record Deleted",

                                            Toast.LENGTH_SHORT

                                        ).show()
                                    }
                            },

                            modifier = Modifier
                                .fillMaxWidth(),

                            colors =
                                ButtonDefaults.buttonColors(

                                    containerColor =
                                        Color.Red
                                ),

                            shape =
                                RoundedCornerShape(14.dp)
                        ) {

                            Text(

                                text =
                                    "🗑 Delete Record",

                                color = Color.White
                            )
                        }
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(40.dp)
            )
        }
    }
}