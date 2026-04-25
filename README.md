MAD302-ASSIGNMENT03-JennyferParmar

Student Information
- Name: Jennyfer Parmar  
- Student ID: A00201240  
- Course: MAD302-01  
- Assignment: 3  

Project Title
Smart Utility App

Description
Smart Utility App is an Android application that demonstrates async data fetching, camera permission handling, and image capture functionality. The app uses Kotlin coroutines to simulate data loading and provides a clean UI with proper navigation between screens.

Features
- Async data fetching using Coroutines
- Loading state using ProgressBar
- Camera permission handling
- Capture image using device camera
- Navigation between MainActivity and DetailsActivity
- Basic error handling (permission denied, capture failure)
- Secure data handling (no sensitive data stored)

App Flow
1. App starts with loading indicator (ProgressBar)
2. After loading, user sees "Open Camera" button
3. On click:
   - Camera permission is requested
   - If granted → camera opens
4. Image is captured
5. User is navigated to DetailsActivity
6. Captured image is displayed

Technologies Used
- Kotlin
- Android SDK
- ViewBinding
- Coroutines

Permissions Used
- Camera Permission

Project Structure
- MainActivity.kt → Handles UI, async loading, permission, and camera
- DetailsActivity.kt → Displays captured image
- activity_main.xml → Main screen UI
- activity_details.xml → Result screen UI


- Minimum 3 pull requests
- Proper code documentation added
