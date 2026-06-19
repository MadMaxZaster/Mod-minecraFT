#!/bin/bash
# Setup Gradle Wrapper für Mod-minecraFT
# Verwendung: bash setup-gradle.sh

set -e

echo "🔧 Setting up Gradle Wrapper (v8.3) for Mod-minecraFT..."

# Download Gradle 8.3
GRADLE_VERSION="8.3"
GRADLE_ZIP="gradle-${GRADLE_VERSION}-bin.zip"
GRADLE_URL="https://services.gradle.org/distributions/${GRADLE_ZIP}"

# Verzeichnis-Check
if [ ! -f "build.gradle" ]; then
    echo "❌ Error: build.gradle not found. Run from project root directory."
    exit 1
fi

# Download & Extract
if [ ! -f "$GRADLE_ZIP" ]; then
    echo "📥 Downloading Gradle $GRADLE_VERSION..."
    wget -q "$GRADLE_URL" || curl -s -o "$GRADLE_ZIP" "$GRADLE_URL"
fi

if [ -f "$GRADLE_ZIP" ]; then
    echo "📦 Extracting Gradle..."
    unzip -q "$GRADLE_ZIP"
    
    echo "🔗 Creating Gradle Wrapper..."
    ./gradle-${GRADLE_VERSION}/bin/gradle wrapper --gradle-version ${GRADLE_VERSION}
    
    rm -rf "gradle-${GRADLE_VERSION}" "$GRADLE_ZIP"
    echo "✅ Gradle Wrapper setup complete!"
else
    echo "⚠️  Could not download Gradle. Please install Gradle 8.1+ manually."
    echo "   macOS:  brew install gradle@8"
    echo "   Linux:  apt install gradle (if available) or download from gradle.org"
fi

echo ""
echo "Now run: ./gradlew build"
