# Ario Touch Multiplier

A lightweight, native Android utility tool by **Ario Labs** designed for gaming and automation assistance:

- Multiplies single screen taps into multi-touch inputs instantly via Android's Accessibility service API.
- Controlled completely via physical hardware volume keys (Volume Up to activate, Volume Down to pause).
- Zero background lag with ultra-low memory consumption.
- Status bar persistent notification indicator for active operational monitoring.

No bloated UI or unnecessary background tracking — clean, native, and efficient.

---

## Installation & Setup

### 1) Download APK
You can download the pre-compiled APK directly from the **GitHub Actions / Releases** tab of this repository.

### 2) Grant Accessibility Permission
1. Install and open the app.
2. Tap the settings button to navigate to system **Accessibility Settings**.
3. Locate **Ario Touch Multiplier** and toggle it **ON**.

---

## Usage Guide

1. Open your game or target app.
2. Press **Volume Up** on your device to engage the 10x multiplier mode (a persistent notification will reflect the active status).
3. Tap anywhere on the screen; inputs will be dynamically multiplied.
4. Press **Volume Down** at any time to pause the service.
5. To fully disable or close the app, simply toggle it off inside Android's Accessibility settings.

---

## Building from Source

If you prefer compiling the source code manually using the terminal command-line tools:

```bash
# Clone the repository
git clone [https://github.com/your-username/ario-touch-multiplier.git](https://github.com/your-username/ario-touch-multiplier.git)
cd ario-touch-multiplier

# Build debug APK (Windows)
gradlew.bat assembleDebug

# Build debug APK (Linux / macOS)
./gradlew assembleDebug