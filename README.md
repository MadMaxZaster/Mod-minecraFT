# Mod-minecraFT - Image Map Mod

Eine Fabric-Mod für Minecraft 1.20.4, die es erlaubt, Bilder auf Maps zu rendern und diese persistent zu speichern.

## Features
🖼️ **Bild-Upload** - Öffne Bilder auf deinem Computer  
🗺️ **Map-Rendering** - Konvertiere Bilder zu Minecraft-Map-Farbpalette  
💾 **Persistierung** - Gespeicherte Bilder bleiben in Maps erhalten  
⌨️ **Keybind** - Taste **O** zum Aktivieren des Upload-Screens

## Installation

1. Installiere [Fabric Loader](https://fabricmc.net/)
2. Baue die Mod: `./gradlew build` (siehe [BUILD_INSTRUCTIONS.md](BUILD_INSTRUCTIONS.md))
3. Kopiere die JAR in `~/.minecraft/mods/`
4. Starte Minecraft mit Fabric-Profil

## Verwendung

1. Starte Minecraft und lade eine Welt
2. Drücke die **O**-Taste
3. Der Image Map Upload-Screen öffnet sich
4. Klicke "Upload Image" um eine Datei auszuwählen
5. Eine neue Map wird automatisch zu deinem Inventar hinzugefügt

## Entwicklung

**Projektstruktur:**
```
src/main/java/de/madmaxzaster/modminecraft/
├── ModMinecraft.java           # Haupt-Einstiegspunkt
└── client/
    ├── ModMinecraftClient.java # Client-Initializer + Keybind
    └── ImageMapScreen.java     # Upload-GUI Screen
```

**Abhängigkeiten:**
- Fabric Loader 0.14.24
- Fabric API 0.86.1+1.20.4
- Minecraft 1.20.4

## Build & Packaging für Modrinth

Siehe [BUILD_INSTRUCTIONS.md](BUILD_INSTRUCTIONS.md) für detaillierte Anweisungen zum Erstellen der JAR.

Das fertige JAR (`build/libs/modminecraft-1.0.0.jar`) kann direkt zu Modrinth hochgeladen werden.

## Lizenz
MIT License

---
**Wichtig:** Bauen Sie diese Mod auf Ihrem lokalen System mit Gradle 8.1+ (die Container-Umgebung hat Kompatibilitätsprobleme).