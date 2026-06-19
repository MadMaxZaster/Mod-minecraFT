# Mod-minecraFT Entwicklerdokumentation

## Architektur

```
ModInitializer (Server+Client)
    ↓
ClientModInitializer
    ├─→ ModMinecraftClient
    │   ├─→ KeyBinding Registration (GLFW.GLFW_KEY_O)
    │   └─→ ClientTickEvents Listener
    │       └─→ ImageMapScreen.show()
    │
    └─→ ImageMapScreen (extends Screen)
        ├─→ Buttons & UI Rendering
        └─→ uploadImage()
            └─→ NBT Data on Map Item
```

## Klassenübersicht

### ModMinecraft.java
**Rolle:** Server-seitige Mod-Initialisierung (Einstiegspunkt)

```java
@Override
public void onInitialize() {
    LOGGER.info("ModMinecraft initialized!");
}
```

**Verhältnisse:**
- Wird von Fabric beim Start aufgerufen
- Minimal gehalten (nur Logging)
- Client-Logik ist in `ModMinecraftClient` separiert

---

### ModMinecraftClient.java
**Rolle:** Client-seitige Initialisierung, Keybind-Registrierung

```java
@Environment(EnvType.CLIENT)
public class ModMinecraftClient implements ClientModInitializer {
    public static final KeyBinding OPEN_IMAGE_MAP_KEY = 
        KeyBindingHelper.registerKeyBinding(
            new KeyBinding(
                "key.modminecraft.open_image_map",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_O,  // ← O-Taste
                "category.modminecraft"
            )
        );

    @Override
    public void onInitializeClient() {
        // Keybind-Listener registrieren
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (OPEN_IMAGE_MAP_KEY.wasPressed()) {
                client.setScreen(new ImageMapScreen(null));
            }
        });
    }
}
```

**Wichtige Details:**
- `@Environment(EnvType.CLIENT)` - Nur auf dem Client-Side kompiliert
- `GLFW.GLFW_KEY_O` = O-Taste (GLFW-Standard-Tasten-Code)
- `ClientTickEvents.END_CLIENT_TICK` = Wird jeden Tick aufgerufen (nach Logik)
- `wasPressed()` = Gibt `true` beim **Drücken** zurück (nicht held)

**Erweiterbar:**
```java
// Zusätzliche Keybinds
public static final KeyBinding SAVE_MAP_KEY = KeyBindingHelper.registerKeyBinding(
    new KeyBinding("key.modminecraft.save_map", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_S, "category.modminecraft")
);
```

---

### ImageMapScreen.java
**Rolle:** Client-seitiger GUI-Screen für Bild-Upload

```java
@Environment(EnvType.CLIENT)
public class ImageMapScreen extends Screen {
    // Konstruktor mit parent-Screen (für Zurück-Navigation)
    public ImageMapScreen(Screen parent) {
        super(Text.literal("Image Map Tool"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        // Buttons werden hier erstellt (wird bei Größenänderung aufgerufen)
        this.uploadButton = ButtonWidget.builder(
            Text.literal("Upload Image"),
            button -> this.uploadImage()
        ).dimensions(centerX - 100, centerY - 30, 200, 20).build();
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // Hintergrund & Text zeichnen
        this.renderBackground(context);
        context.drawCenteredTextWithShadow(
            this.textRenderer,
            this.title,
            this.width / 2, 20, 0xFFFFFF
        );
        super.render(context, mouseX, mouseY, delta);
    }

    private void uploadImage() {
        // Bild verarbeiten & zur Map speichern
        ItemStack mapStack = new ItemStack(Items.FILLED_MAP);
        NbtCompound tag = new NbtCompound();
        tag.putInt("map", 0);
        tag.putString("imageFile", "imagemap_0");
        mapStack.setNbt(tag);
        
        // Zum Inventar hinzufügen
        if (!client.player.getInventory().insertStack(mapStack)) {
            client.player.dropItem(mapStack, false);
        }
    }
}
```

**Wichtige Methoden:**
- `init()` - Widgets initialisieren (wird bei Größenänderung aufgerufen)
- `render()` - Jeden Frame aufgerufen (zeichne UI)
- `close()` - Screen schließen/zurück gehen

**Bild-Verarbeitung (TODO):**
```java
// Für echte Bild-zu-Map-Konvertierung:
private BufferedImage loadImageFile(File file) {
    // 1. Lade Bild
    // 2. Resize zu 128x128
    // 3. Konvertiere zu Minecraft-Farbpalette (256 Farben)
    // 4. Speichere als byte[] in NBT
}
```

---

## NBT-Persistierung

**Aktuell (vereinfacht):**
```java
NbtCompound tag = new NbtCompound();
tag.putInt("map", 0);
tag.putString("imageFile", "imagemap_0");
mapStack.setNbt(tag);
```

**Für echte Bilddaten (TODO):**
```java
NbtCompound imageData = new NbtCompound();
byte[] pixels = new byte[128 * 128];  // Bild in Graustufen/Farben
imageData.putByteArray("pixels", pixels);
imageData.putInt("width", 128);
imageData.putInt("height", 128);

tag.put("imageData", imageData);
mapStack.setNbt(tag);
```

---

## fabric.mod.json Entrypoints

```json
"entrypoints": {
    "main": ["de.madmaxzaster.modminecraft.ModMinecraft"],
    "client": ["de.madmaxzaster.modminecraft.client.ModMinecraftClient"]
}
```

**Erklärung:**
- `main` → Server & Client (dedicated server + client)
- `client` → Nur Client (wird auf Server nicht geladen)

---

## Build-Prozess

```
1. build.gradle Konfiguration lesen
   ├─ Gradle-Version: 8.1+ erforderlich
   ├─ Fabric Loom Plugin laden
   └─ Abhängigkeiten auflösen

2. Quellcode kompilieren
   ├─ Java → Bytecode
   ├─ Mappings anwenden (Yarn: code names lesbar)
   └─ Access Widener anwenden

3. Ressourcen verarbeiten
   ├─ fabric.mod.json (${version} ersetzen)
   └─ Lang-Dateien (i18n)

4. JAR packen
   ├─ Compiled classes
   ├─ Ressourcen
   └─ Metadaten

5. Output: build/libs/modminecraft-1.0.0.jar ✅
```

---

## Erweiterungsmöglichkeiten

### 1. Richtige Datei-Auswahl
```java
// Ersetze uploadImage() mit:
FileDialog dialog = new FileDialog(null, "Select Image", FileDialog.LOAD);
dialog.setFile("*.png;*.jpg");
dialog.setVisible(true);

String filePath = dialog.getFile();
if (filePath != null) {
    BufferedImage image = ImageIO.read(new File(filePath));
    // Verarbeite image...
}
```

### 2. Bild-zu-Map-Konvertierung
```java
private byte[] convertImageToMapPalette(BufferedImage image) {
    byte[] mapData = new byte[image.getWidth() * image.getHeight()];
    
    for (int i = 0; i < mapData.length; i++) {
        int rgb = image.getRGB(i % image.getWidth(), i / image.getWidth());
        int closest = findClosestMapColor(rgb);  // Finde nächste Map-Farbe
        mapData[i] = (byte) closest;
    }
    
    return mapData;
}
```

### 3. Persistierung beim Speichern
```java
// Client-seitig: Map mit Bild-Daten markieren
mapItem.setNbt(imageDataTag);

// Server-seitig (TODO): Speichere permanente Referenz
// - In Playerdata
// - In World-Ordner
// - In Datenbankfile
```

### 4. Rendering auf Map
```java
// MapDecorationRegistry für Custom-Dekoration oder
// ClientTickEvents.END_RENDER_WORLD → CustomMaps zeichnen
```

---

## Debugging-Tipps

**Console-Output:**
```java
ModMinecraft.LOGGER.info("Message");
ModMinecraft.LOGGER.error("Error:", exception);
```

**In-Game-Anzeige:**
```java
client.player.sendMessage(Text.literal("Hello"), false);
```

**Breakpoints (wenn mit IDE debuggt):**
- VSCode: Debugger-Extension für Java
- IntelliJ: Eingebauter Debugger

---

## Lizenz & Credits
MIT License - Frei verwendbar & veränderbar
