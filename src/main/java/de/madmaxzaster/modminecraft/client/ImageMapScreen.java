package de.madmaxzaster.modminecraft.client;

import de.madmaxzaster.modminecraft.ModMinecraft;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import java.io.File;
import java.nio.file.Files;

@Environment(EnvType.CLIENT)
public class ImageMapScreen extends Screen {
    private final Screen parent;
    private ButtonWidget uploadButton;
    private ButtonWidget closeButton;
    private String statusText = "Press 'Upload' to select an image...";

    public ImageMapScreen(Screen parent) {
        super(Text.literal("Image Map Tool"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int centerY = this.height / 2;

        this.uploadButton = ButtonWidget.builder(
            Text.literal("Upload Image"),
            button -> this.uploadImage()
        ).dimensions(centerX - 100, centerY - 30, 200, 20).build();

        this.closeButton = ButtonWidget.builder(
            Text.literal("Close"),
            button -> this.close()
        ).dimensions(centerX - 100, centerY + 20, 200, 20).build();

        this.addDrawableChild(this.uploadButton);
        this.addDrawableChild(this.closeButton);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context);
        context.drawCenteredTextWithShadow(
            this.textRenderer,
            this.title,
            this.width / 2,
            20,
            0xFFFFFF
        );
        context.drawCenteredTextWithShadow(
            this.textRenderer,
            Text.literal(this.statusText),
            this.width / 2,
            this.height / 2 - 70,
            0xAAAAAA
        );
        super.render(context, mouseX, mouseY, delta);
    }

    private void uploadImage() {
        try {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client.player == null) return;

            // Erstelle eine Map mit dem Bild-Daten
            ItemStack mapStack = new ItemStack(Items.FILLED_MAP);
            NbtCompound tag = new NbtCompound();
            tag.putInt("map", 0);
            tag.putString("imageFile", "imagemap_0");
            mapStack.setNbt(tag);

            // Speichere das Image-Item im Player-Inventar
            if (!client.player.getInventory().insertStack(mapStack)) {
                client.player.dropItem(mapStack, false);
            }

            this.statusText = "Map added to inventory!";
            ModMinecraft.LOGGER.info("Image map created and added to inventory");
        } catch (Exception e) {
            this.statusText = "Error: " + e.getMessage();
            ModMinecraft.LOGGER.error("Error creating map", e);
        }
    }

    @Override
    public void close() {
        this.client.setScreen(this.parent);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }
}
