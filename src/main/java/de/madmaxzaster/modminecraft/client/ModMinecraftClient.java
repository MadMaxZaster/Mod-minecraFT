package de.madmaxzaster.modminecraft.client;

import de.madmaxzaster.modminecraft.ModMinecraft;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class ModMinecraftClient implements ClientModInitializer {
    public static final KeyBinding OPEN_IMAGE_MAP_KEY = KeyBindingHelper.registerKeyBinding(
        new KeyBinding(
            "key.modminecraft.open_image_map",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_O,
            "category.modminecraft"
        )
    );

    @Override
    public void onInitializeClient() {
        ModMinecraft.LOGGER.info("ModMinecraft client initialized!");

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (OPEN_IMAGE_MAP_KEY.wasPressed()) {
                client.setScreen(new ImageMapScreen(null));
            }
        });
    }
}
