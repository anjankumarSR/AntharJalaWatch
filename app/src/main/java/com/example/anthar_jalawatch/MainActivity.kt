package com.example.anthar_jalawatch

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Modifier

import com.example.anthar_jalawatch.ui.screens.AlertScreen
import com.example.anthar_jalawatch.ui.screens.AuthChoiceScreen
import com.example.anthar_jalawatch.ui.screens.BorewellScreen
import com.example.anthar_jalawatch.ui.screens.DashboardScreen
import com.example.anthar_jalawatch.ui.screens.EmailLoginScreen
import com.example.anthar_jalawatch.ui.screens.GoogleSignInScreen
import com.example.anthar_jalawatch.ui.screens.MapScreen
import com.example.anthar_jalawatch.ui.screens.PhoneLoginScreen
import com.example.anthar_jalawatch.ui.screens.RechargeScreen
import com.example.anthar_jalawatch.ui.screens.RegisterScreen
import com.example.anthar_jalawatch.ui.screens.SplashScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {

            // SPLASH STATE
            var showSplash by remember {

                mutableStateOf(true)
            }

            // LOGIN STATE
            var isLoggedIn by remember {

                mutableStateOf(false)
            }

            // AUTH SCREEN STATE
            var authScreen by remember {

                mutableStateOf("choice")
            }

            // CURRENT APP SCREEN
            var currentScreen by remember {

                mutableStateOf("dashboard")
            }

            // SPLASH SCREEN
            if (showSplash) {

                SplashScreen(

                    onSplashFinished = {

                        showSplash = false
                    }
                )
            }

            // LOGIN FLOW
            else if (!isLoggedIn) {

                when (authScreen) {

                    // AUTH CHOICE
                    "choice" -> {

                        AuthChoiceScreen(

                            onPhoneClick = {

                                authScreen =
                                    "phone"
                            },

                            onEmailClick = {

                                authScreen =
                                    "email"
                            }
                        )
                    }

                    // PHONE LOGIN
                    "phone" -> {

                        PhoneLoginScreen(

                            onLoginSuccess = {

                                isLoggedIn = true
                            }
                        )
                    }

                    // EMAIL LOGIN
                    "email" -> {

                        EmailLoginScreen(

                            onLoginSuccess = {

                                isLoggedIn = true
                            },

                            onCreateAccountClick = {

                                authScreen =
                                    "register"
                            },

                            onBackClick = {

                                authScreen =
                                    "choice"
                            },

                            onGoogleClick = {

                                authScreen =
                                    "google"
                            }
                        )
                    }

                    // REGISTER SCREEN
                    "register" -> {

                        RegisterScreen(

                            onRegisterSuccess = {

                                isLoggedIn = true
                            },

                            onBackClick = {

                                authScreen =
                                    "email"
                            }
                        )
                    }

                    // GOOGLE LOGIN
                    "google" -> {

                        GoogleSignInScreen(

                            onLoginSuccess = {

                                isLoggedIn = true
                            }
                        )
                    }
                }
            }

            // MAIN APP
            else {

                Scaffold(

                    // BOTTOM NAVIGATION
                    bottomBar = {

                        NavigationBar {

                            // HOME
                            NavigationBarItem(

                                selected =
                                    currentScreen == "dashboard",

                                onClick = {

                                    currentScreen =
                                        "dashboard"
                                },

                                icon = {

                                    Text("🏠")
                                },

                                label = {

                                    Text("Home")
                                }
                            )

                            // BOREWELL
                            NavigationBarItem(

                                selected =
                                    currentScreen == "borewell",

                                onClick = {

                                    currentScreen =
                                        "borewell"
                                },

                                icon = {

                                    Text("💧")
                                },

                                label = {

                                    Text("Borewell")
                                }
                            )

                            // MAP
                            NavigationBarItem(

                                selected =
                                    currentScreen == "map",

                                onClick = {

                                    currentScreen =
                                        "map"
                                },

                                icon = {

                                    Text("🗺️")
                                },

                                label = {

                                    Text("Map")
                                }
                            )

                            // RECHARGE
                            NavigationBarItem(

                                selected =
                                    currentScreen == "recharge",

                                onClick = {

                                    currentScreen =
                                        "recharge"
                                },

                                icon = {

                                    Text("♻️")
                                },

                                label = {

                                    Text("Recharge")
                                }
                            )

                            // ALERTS
                            NavigationBarItem(

                                selected =
                                    currentScreen == "alerts",

                                onClick = {

                                    currentScreen =
                                        "alerts"
                                },

                                icon = {

                                    Text("🚨")
                                },

                                label = {

                                    Text("Alerts")
                                }
                            )
                        }
                    }

                ) { paddingValues ->

                    when (currentScreen) {

                        // DASHBOARD
                        "dashboard" -> {

                            Box(
                                modifier =
                                    Modifier.padding(
                                        paddingValues
                                    )
                            ) {

                                DashboardScreen(

                                    onBorewellClick = {

                                        currentScreen =
                                            "borewell"
                                    },

                                    onWaterMapClick = {

                                        currentScreen =
                                            "map"
                                    },

                                    onRechargeClick = {

                                        currentScreen =
                                            "recharge"
                                    },

                                    onAlertClick = {

                                        currentScreen =
                                            "alerts"
                                    }
                                )
                            }
                        }

                        // BOREWELL SCREEN
                        "borewell" -> {

                            Box(
                                modifier =
                                    Modifier.padding(
                                        paddingValues
                                    )
                            ) {

                                BorewellScreen(

                                    onBackClick = {

                                        currentScreen =
                                            "dashboard"
                                    }
                                )
                            }
                        }

                        // MAP SCREEN
                        "map" -> {

                            MapScreen()
                        }

                        // RECHARGE SCREEN
                        "recharge" -> {

                            Box(
                                modifier =
                                    Modifier.padding(
                                        paddingValues
                                    )
                            ) {

                                RechargeScreen(

                                    onBackClick = {

                                        currentScreen =
                                            "dashboard"
                                    }
                                )
                            }
                        }

                        // ALERT SCREEN
                        "alerts" -> {

                            AlertScreen()
                        }
                    }
                }
            }
        }
    }
}