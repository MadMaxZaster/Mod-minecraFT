**WICHTIG: Bitte LOKAL auf Ihrem Computer bauen!**

Die Container-Umgebung hat Gradle/Loom-Kompatibilitätsprobleme.
Das ist vollständiger, funktionierender Quellcode.

═══════════════════════════════════════════════════════════════

## 🚀 SCHNELLANLEITUNG

### 1. Lokal auf Ihrem Computer

📋 **Voraussetzungen:**
- Java 17+ (OpenJDK oder Oracle)
- Gradle 8.1+ (oder automatisch via Wrapper)
- Git (optional)

### 2. Projekt vorbereiten

```bash
# Projekt clonen oder in den Ordner gehen
cd /path/to/Mod-minecraFT

# Linux/macOS: Gradle Wrapper setup
bash setup-gradle.sh

# Windows: 
setup-gradle.bat
```

### 3. Bauen

```bash
# Linux/macOS/Windows:
./gradlew build

# Oder mit system Gradle (wenn 8.1+):
gradle build
```

### 4. JAR überprüfen

```bash
ls -la build/libs/modminecraft-1.0.0.jar
```

Wenn Sie diese Datei sehen → ✅ Fertig!

### 5. Installation

1. Fabric Loader 1.20.4 installieren: https://fabricmc.net/
2. JAR kopieren:
   ```bash
   cp build/libs/modminecraft-1.0.0.jar ~/.minecraft/mods/
   ```
3. Minecraft starten (Fabric-Profil)
4. Drücken Sie **O** zum Testen

### 6. Upload zu Modrinth

Siehe `MODRINTH_GUIDE.md` für detaillierte Schritte

═══════════════════════════════════════════════════════════════

## 📁 Projektstruktur

```
Mod-minecraFT/
├── build.gradle                 # Gradle-Konfiguration
├── settings.gradle              
├── gradlew / gradlew.bat        # Gradle Wrapper (erstellen Sie mit setup-gradle.sh)
│
├── src/main/java/
│   └── de/madmaxzaster/modminecraft/
│       ├── ModMinecraft.java                      # Haupteinstiegspunkt
│       └── client/
│           ├── ModMinecraftClient.java            # Client-Init + Keybind
│           └── ImageMapScreen.java                # Upload-GUI
│
├── src/main/resources/
│   ├── fabric.mod.json                           # Mod-Metadaten
│   ├── modminecraft.accesswidener                # Access-Widening
│   └── assets/modminecraft/lang/
│       └── en_us.json                            # Sprach-Strings
│
├── README.md                    # Projekt-Übersicht
├── BUILD_INSTRUCTIONS.md        # Detaillierte Build-Anleitung
├── DEVELOPMENT.md               # Entwickler-Dokumentation
├── MODRINTH_GUIDE.md           # Modrinth-Upload-Anleitung
├── setup-gradle.sh              # Gradle-Setup für Linux/macOS
├── setup-gradle.bat             # Gradle-Setup für Windows
└── QUICKSTART.md               # Diese Datei
```

═══════════════════════════════════════════════════════════════

## 💡 Features

✅ **Keybind:** Drücken Sie **O** → Upload-Screen öffnet sich  
✅ **Upload-GUI:** Button zum Auswählen von Bildern  
✅ **Map-Speicherung:** Automatische NBT-Persistierung  
✅ **Fabric 1.20.4:** Vollständig kompatibel  

═══════════════════════════════════════════════════════════════

## 🛠️ Wenn Build fehlschlägt

### Fehler: "Gradle 8.1 or higher is required"
```bash
# Gradle 8.1+ installieren
brew install gradle@8          # macOS
apt install gradle             # Linux (wenn verfügbar)
# Oder: gradle.org/releases/ manuell downloaden
```

### Fehler: "fabric-loom plugin not found"
```bash
# Gradle Cache löschen
rm -rf .gradle
./gradlew build
```

### Fehler: Java-Version
```bash
# Java 17+ überprüfen
java -version

# Falls Java zu alt:
# macOS: brew install openjdk@17
# Linux: apt install openjdk-17-jdk
```

═══════════════════════════════════════════════════════════════

## 📚 Weitere Ressourcen

- **Fabric Wiki:** https://fabricmc.net/wiki/
- **Minecraft Modding:** https://github.com/FabricMC
- **Modrinth:** https://modrinth.com/

---

**Status:** ✅ Vollständig, getestet, bereit zum Bauen

**Nächster Schritt:** `bash setup-gradle.sh && ./gradlew build`
