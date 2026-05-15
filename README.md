# 🌊 AntharJalaWatch

> Smart Groundwater Monitoring Android Application using Kotlin, Firebase, and Google Maps

---

# 📖 About The Project

AntharJalaWatch is a smart Android application developed for groundwater monitoring and borewell management.  
The application helps users track groundwater conditions, manage borewell records, visualize water stress on live maps, and receive recharge recommendations with smart alerts.  
This project aims to support sustainable water management using modern Android technologies and real-time cloud integration.

---

# ❗ Problem Statement

Groundwater monitoring and borewell management are difficult using traditional methods.  
Many areas face water scarcity due to lack of real-time monitoring, poor groundwater tracking, and inefficient recharge management.

AntharJalaWatch provides a digital solution to:

- Monitor groundwater conditions
- Manage borewell data
- View groundwater stress on maps
- Get recharge suggestions
- Receive smart alerts for water conditions

---

# ✨ Features

## 📱 Authentication System

- Phone OTP Login
- Email Authentication
- Secure Firebase Authentication

---

## 📊 Groundwater Dashboard

- Displays total borewells
- Safe / Medium / Critical water status
- Live monitoring dashboard

---

## 💧 Borewell Monitoring

- Add borewell details
- Store owner information
- Save village data
- Record borewell depth
- Record water yield

---

## 🗺️ Water Stress Map

- Live map integration
- Borewell location markers
- Groundwater condition visualization

---

## ♻️ Recharge Recommendations

- Smart recharge suggestions
- Rainwater harvesting guidance
- Recharge pit recommendations

---

## 🚨 Smart Alerts

- Groundwater condition alerts
- Suggested actions for users
- Safety notifications

---

## ☁️ Firebase Integration

- Cloud Firestore database
- Authentication services
- Real-time data storage

---

# 📱 Application Screens

---

# 🔹 Login Screen

![Login Screen](screenshots/login_screen.png)

---

# 🔹 Phone OTP Authentication

![Phone Login](screenshots/phone_login_screen.png)

---

# 🔹 Dashboard Screen

![Dashboard](screenshots/dashboard_screen.png)

---

# 🔹 Borewell Entry Screen

![Borewell Entry](screenshots/borewell_entry_screen.png)

---

# 🔹 Saved Borewell Records

![Saved Records](screenshots/saved_records_screen.png)

---

# 🔹 Water Stress Map

![Map Screen](screenshots/map_screen.png)

---

# 🔹 Recharge Recommendation Screen

![Recharge Screen](screenshots/recharge_screen.png)

---

# 🔹 Smart Alerts Screen

![Alerts Screen](screenshots/alerts_screen.png)

---

# 🛠️ Technologies Used

| Technology | Purpose |
|---|---|
| Kotlin | Android Development |
| Jetpack Compose | Modern UI Design |
| Firebase Authentication | Secure Login |
| Firebase Firestore | Cloud Database |
| Google Maps API | Live Maps |
| Android Studio | Development Environment |
| Material Design 3 | UI Components |

---

# 📂 Full Project Structure

```text
AntharJalaWatch/
│
├── .gradle/
├── .idea/
│
├── app/
│   │
│   ├── build/
│   │
│   ├── src/
│   │   │
│   │   ├── androidTest/
│   │   │
│   │   ├── main/
│   │   │   │
│   │   │   ├── java/
│   │   │   │   └── com/example/anthar_jalawatch/
│   │   │   │       │
│   │   │   │       ├── ui/
│   │   │   │       │
│   │   │   │       ├── MainActivity.kt
│   │   │   │       ├── RegisterScreen.kt
│   │   │   │       ├── AuthChoiceScreen.kt
│   │   │   │       ├── GoogleSignInScreen.kt
│   │   │   │       ├── DashboardScreen.kt
│   │   │   │       ├── BorewellScreen.kt
│   │   │   │       ├── MapScreen.kt
│   │   │   │       ├── RechargeScreen.kt
│   │   │   │       ├── AlertsScreen.kt
│   │   │   │       └── FirebaseUtils.kt
│   │   │   │
│   │   │   ├── res/
│   │   │   │   │
│   │   │   │   ├── drawable/
│   │   │   │   ├── layout/
│   │   │   │   ├── mipmap/
│   │   │   │   ├── values/
│   │   │   │   └── xml/
│   │   │   │
│   │   │   └── AndroidManifest.xml
│   │   │
│   │   └── test/
│   │
│   ├── .gitignore
│   ├── build.gradle.kts
│   ├── google-services.json
│   └── proguard-rules.pro
│
├── build/
├── gradle/
│
├── screenshots/
│   ├── login_screen.png
│   ├── phone_login_screen.png
│   ├── dashboard_screen.png
│   ├── borewell_entry_screen.png
│   ├── saved_records_screen.png
│   ├── map_screen.png
│   ├── recharge_screen.png
│   └── alerts_screen.png
│
├── .gitignore
├── build.gradle.kts
├── gradle.properties
├── gradlew
├── gradlew.bat
├── local.properties
├── settings.gradle.kts
└── README.md
```

---

# 🚀 Installation & Setup

## 📌 Prerequisites

Before running the project, make sure you have:

- Android Studio
- Android SDK
- Firebase Account
- Google Maps API Key
- Internet Connection

---

# ⚙️ Step 1 — Clone Repository

```bash
git clone https://github.com/anjankumarSR/AntharJalaWatch.git
```

---

# ⚙️ Step 2 — Open in Android Studio

- Open Android Studio
- Click Open Project
- Select AntharJalaWatch folder

---

# ⚙️ Step 3 — Sync Gradle

Allow Gradle sync to complete.

---

# ⚙️ Step 4 — Connect Firebase

Add your Firebase configuration file:

```text
google-services.json
```

inside:

```text
app/
```

---

# ⚙️ Step 5 — Enable Firebase Services

Enable:

- Firebase Authentication
- Firebase Firestore

inside Firebase Console.

---

# ⚙️ Step 6 — Add Google Maps API Key

Open:

```text
AndroidManifest.xml
```

Add your API key:

```xml
<meta-data
    android:name="com.google.android.geo.API_KEY"
    android:value="YOUR_API_KEY"/>
```

---

# ⚙️ Step 7 — Run The Application

Click ▶ Run in Android Studio.

---

# 📦 Dependencies

## Firebase

- Firebase Authentication
- Firebase Firestore

## Android

- Jetpack Compose
- Material3
- Navigation Compose

## Maps

- Google Maps SDK

---

# 🔒 Permissions Used

```xml
<uses-permission android:name="android.permission.INTERNET"/>

<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>

<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>
```

---

# 📊 Application Workflow

1. User logs in
2. Dashboard loads groundwater status
3. User adds borewell details
4. Data stored in Firebase
5. Map visualizes groundwater condition
6. Recharge recommendations generated
7. Alerts shown based on water level

---

# 🌍 Real-World Applications

- Groundwater Monitoring
- Smart Village Systems
- Water Resource Management
- Borewell Tracking
- Environmental Monitoring
- Sustainable Water Management

---

# 🚀 Future Improvements

- IoT Sensor Integration
- AI-based Water Prediction
- Real-time Water Analytics
- Government Dashboard
- Multi-user System
- Cloud Backup
- Rainfall Prediction Integration

---

# 🧠 Learning Outcomes

Through this project, the following concepts were learned:

- Android App Development
- Firebase Integration
- Jetpack Compose UI
- Google Maps Integration
- Authentication Systems
- Cloud Database Handling
- Real-time Monitoring Systems

---

# 👨‍💻 Developer

## Anjan Kumar S R

Electronics and Communication Engineering Student  
Passionate about Android Development and Smart Monitoring Solutions.

GitHub Profile:

https://github.com/anjankumarSR

---

# 🤝 Contribution

Contributions and suggestions are welcome.

---

# 📄 License

This project is developed for educational and internship purposes only.

---