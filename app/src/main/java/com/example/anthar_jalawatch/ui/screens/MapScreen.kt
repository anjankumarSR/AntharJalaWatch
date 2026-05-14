package com.example.anthar_jalawatch.ui.screens

import android.util.Log

import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.runtime.*

import androidx.compose.ui.Modifier

import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng

import com.google.firebase.firestore.FirebaseFirestore

import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

// DATA CLASS
data class MapBorewell(

    val ownerName: String = "",

    val villageName: String = "",

    val latitude: Double = 0.0,

    val longitude: Double = 0.0,

    val waterStatus: String = ""
)

@Composable
fun MapScreen() {

    // FIREBASE
    val db = FirebaseFirestore.getInstance()

    // MARKER LIST
    var borewellList by remember {

        mutableStateOf<List<MapBorewell>>(emptyList())
    }

    // DEFAULT LOCATION
    val defaultLocation = LatLng(
        12.9787303,
        77.5167995
    )

    // CAMERA
    val cameraPositionState =
        rememberCameraPositionState {

            position = CameraPosition.fromLatLngZoom(
                defaultLocation,
                7f
            )
        }

    // MAP UI SETTINGS
    val uiSettings = remember {

        MapUiSettings(

            zoomControlsEnabled = true,

            compassEnabled = true,

            myLocationButtonEnabled = true,

            mapToolbarEnabled = true,

            zoomGesturesEnabled = true,

            tiltGesturesEnabled = true,

            rotationGesturesEnabled = true,

            scrollGesturesEnabled = true
        )
    }

    // MAP PROPERTIES
    val properties = remember {

        MapProperties(

            isMyLocationEnabled = true
        )
    }

    // FETCH FIREBASE DATA
    LaunchedEffect(Unit) {

        db.collection("borewells")

            .get()

            .addOnSuccessListener { result ->

                val fetchedList =
                    mutableListOf<MapBorewell>()

                for (document in result.documents) {

                    val latString =
                        document.getString("latitude")

                    val lngString =
                        document.getString("longitude")

                    Log.d(
                        "MAP_DEBUG",
                        "Lat = $latString | Lng = $lngString"
                    )

                    val lat =
                        latString
                            ?.trim()
                            ?.toDoubleOrNull()

                    val lng =
                        lngString
                            ?.trim()
                            ?.toDoubleOrNull()

                    if (
                        lat != null &&
                        lng != null
                    ) {

                        fetchedList.add(

                            MapBorewell(

                                ownerName =
                                    document.getString(
                                        "ownerName"
                                    ) ?: "Borewell",

                                villageName =
                                    document.getString(
                                        "villageName"
                                    ) ?: "",

                                latitude = lat,

                                longitude = lng,

                                waterStatus =
                                    document.getString(
                                        "waterStatus"
                                    ) ?: ""
                            )
                        )
                    }
                }

                // UPDATE LIST
                borewellList = fetchedList

                // MOVE CAMERA TO FIRST MARKER
                if (borewellList.isNotEmpty()) {

                    val firstLocation = LatLng(

                        borewellList.first().latitude,

                        borewellList.first().longitude
                    )

                    cameraPositionState.position =

                        CameraPosition.fromLatLngZoom(

                            firstLocation,

                            7f
                        )
                }
            }
    }

    // GOOGLE MAP
    GoogleMap(

        modifier = Modifier.fillMaxSize(),

        cameraPositionState =
            cameraPositionState,

        uiSettings = uiSettings,

        properties = properties

    ) {

        // SHOW ALL MARKERS
        borewellList.forEachIndexed {

                index,
                borewell ->

            // MARKER COLOR
            val markerColor = when {

                borewell.waterStatus.contains(
                    "Good",
                    true
                ) -> BitmapDescriptorFactory.HUE_GREEN

                borewell.waterStatus.contains(
                    "Medium",
                    true
                ) -> BitmapDescriptorFactory.HUE_YELLOW

                else -> BitmapDescriptorFactory.HUE_RED
            }

            Marker(

                state = MarkerState(

                    position = LatLng(

                        borewell.latitude,

                        borewell.longitude
                    )
                ),

                title =
                    "${borewell.ownerName}",

                snippet =
                    "Status: ${borewell.waterStatus}",

                icon = BitmapDescriptorFactory
                    .defaultMarker(markerColor)
            )
        }
    }
}