# Mod-minecraFT - Build Anleitung

## Anforderungen
- **Java 17+** (OpenJDK oder Oracle JDK)
- **Gradle 8.1+** (automatisch via Gradle Wrapper)

## Build durchführen

### Option 1: Mit Gradle Wrapper (empfohlen)
```bash
cd /workspaces/Mod-minecraFT
./gradlew build
```

### Option 2: Mit System Gradle (wenn Gradle 8.1+ installiert)
```bash
gradle build
```

## Output
Das kompilierte JAR wird hier erstellt:
```
build/libs/modminecraft-1.0.0.jar
```

## Installation in Minecraft
1. Installieren Sie [Fabric Loader 1.20.4](https://fabricmc.net/use/installer/) 
2. Kopieren Sie das JAR in Ihren Mods-Ordner:
   - **Windows**: `%APPDATA%\.minecraft\mods\`
   - **macOS**: `~/Library/Application Support/minecraft/mods/`
   - **Linux**: `~/.minecraft/mods/`
3. Starten Sie Minecraft mit dem Fabric-Profil

## Verwendung
- **Taste O drücken** → Opens Image Map Upload-Screen
- **Upload Button** → Erstellat eine Map mit Bilddaten im Inventar

## Troubleshooting

### "Gradle 8.1 or higher is required"
→ Installieren Sie Gradle 8.1+:
```bash
brew install gradle@8  # macOS
```

### Build-Fehler
→ Löschen Sie den Cache:
```bash
rm -rf .gradle build
./gradlew build
```

## Features
✓ Keybind: O-Taste zum Öffnen des Screens
✓ Upload-GUI für Bild-Auswahl
✓ NBT-basierte Persistierung auf Maps
✓ Fabric 1.20.4 kompatibel

---
**Hinweis**: Diesen Quellcode auf Ihrem lokalen System mit Gradle 8.1+ bauen.
