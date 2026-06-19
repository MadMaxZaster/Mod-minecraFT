# Modrinth Upload Anleitung

## JAR erfolgreich gebaut?

Nach dem Ausführen von `./gradlew build` sollte die JAR hier zu finden sein:
```
build/libs/modminecraft-1.0.0.jar
```

Wenn Sie diese Datei sehen, ist die Mod bereit zum Upload!

## Vor dem Upload: Checkliste

- [ ] `./gradlew build` erfolgreich ausgeführt
- [ ] `build/libs/modminecraft-1.0.0.jar` existiert
- [ ] Die JAR lädt ordnungsgemäß in Minecraft

## Modrinth Upload Steps

### 1. Modrinth-Account erstellen
Besuchen Sie https://modrinth.com und erstellen Sie einen Account

### 2. New Project erstellen
- **Project Name:** Mod-minecraFT
- **Project Type:** Mod
- **Categories:** Utility, Client-side
- **License:** MIT
- **Minecraft Versions:** 1.20.4
- **Loaders:** Fabric

### 3. JAR hochladen
1. Gehen Sie zu "Versions"
2. Klicken Sie "Upload Version"
3. Wählen Sie `build/libs/modminecraft-1.0.0.jar`
4. Setzen Sie Version auf `1.0.0`
5. Wählen Sie Minecraft-Version `1.20.4`
6. Wählen Sie Loader `Fabric`

### 4. Beschreibung hinzufügen
```
Mod-minecraFT - Image Map Renderer

Upload Bilder und rendern Sie sie auf Minecraft-Maps!

Features:
- Drücken Sie 'O' um den Upload-Screen zu öffnen
- Laden Sie Bilder hoch
- Maps werden automatisch in Ihrem Inventar erstellt
- Bilder bleiben persistent auf den Maps

Installation:
1. Fabric Loader 1.20.4 installieren
2. JAR in ~/.minecraft/mods/ kopieren
3. Spielen!
```

### 5. Veröffentlichen
Klicken Sie "Publish" und voilà!

---

## JAR-Validierung

### Kommandozeile überprüfen
```bash
# Inhalt der JAR prüfen
unzip -l build/libs/modminecraft-1.0.0.jar | grep -E "(\.class|fabric\.mod\.json)"
```

**Erwartet:**
```
de/madmaxzaster/modminecraft/ModMinecraft.class
de/madmaxzaster/modminecraft/client/ModMinecraftClient.class
de/madmaxzaster/modminecraft/client/ImageMapScreen.class
fabric.mod.json
```

### Im Minecraft Launcher
1. Fabric-Profil auswählen
2. JAR in `~/.minecraft/mods/` kopieren
3. Launcher starten
4. Auf Launchprotokolle prüfen (sollte keine Fehler haben)

---

## Troubleshooting beim Upload

**"Invalid artifact type"**
→ Sicherstellen, dass die Datei mit `.jar` endet und keine Textdatei ist

**"Missing dependencies"**
→ Fabric API muss in `build.gradle` als `modImplementation` definiert sein (✓ bereits konfiguriert)

**"Version already exists"**
→ Versionnummer erhöhen (z.B. 1.0.1 statt 1.0.0)

---

## Versionierung

Erhöhen Sie für zukünftige Versionen die Versionsnummer in `build.gradle`:
```gradle
version = '1.0.1'  // Changed from 1.0.0
```

Dann:
```bash
./gradlew clean build
# Neue JAR: build/libs/modminecraft-1.0.1.jar
```

Auf Modrinth hochladen und Changelog hinzufügen!

---

**Support:** Bei Fragen zu Modrinth, siehe https://docs.modrinth.com/guides/publishing/
