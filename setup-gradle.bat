@echo off
REM Setup Gradle Wrapper für Mod-minecraFT (Windows)
REM Usage: setup-gradle.bat

setlocal enabledelayedexpansion

echo 🔧 Setting up Gradle Wrapper for Mod-minecraFT...

if not exist "build.gradle" (
    echo ❌ Error: build.gradle not found. Run from project root directory.
    exit /b 1
)

set GRADLE_VERSION=8.3
set GRADLE_ZIP=gradle-%GRADLE_VERSION%-bin.zip
set GRADLE_URL=https://services.gradle.org/distributions/%GRADLE_ZIP%
set GRADLE_DIR=gradle-%GRADLE_VERSION%

if not exist "%GRADLE_ZIP%" (
    echo 📥 Downloading Gradle %GRADLE_VERSION%...
    powershell -Command "(New-Object System.Net.ServicePointManager).SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072; (New-Object System.Net.WebClient).DownloadFile('%GRADLE_URL%', '%GRADLE_ZIP%')"
)

if exist "%GRADLE_ZIP%" (
    echo 📦 Extracting Gradle...
    powershell -Command "Expand-Archive '%GRADLE_ZIP%' -DestinationPath ."
    
    echo 🔗 Creating Gradle Wrapper...
    call "%GRADLE_DIR%\bin\gradle.bat" wrapper --gradle-version %GRADLE_VERSION%
    
    echo Cleaning up...
    rmdir /s /q "%GRADLE_DIR%"
    del "%GRADLE_ZIP%"
    echo ✅ Gradle Wrapper setup complete!
) else (
    echo ⚠️  Could not download Gradle. Please install Gradle 8.1+ manually.
    echo    Download from: https://gradle.org/releases/
)

echo.
echo Now run: gradlew.bat build
pause
