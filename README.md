# GifSearcher
To build this project in debug mode with gradle you need to open terminal and follow these steps:

navigate to path\to\project_name
run gradlew.bat(or gradlew.sh if you use Linux/Mac) assembleDebug

This creates an apk named module_name-debug.apk in project_name\module_name\build\outputs\apk

To run yourapp on the emulator you need to create an Android Virtual Device using Android Studio.
After that you can start the Android Emulator as follows:

navigate to \path\to\android\sdk\tools
run emulator -avd avd_name
adb install path\to\app.apk
