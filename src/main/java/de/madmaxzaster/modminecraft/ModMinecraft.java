package de.madmaxzaster.modminecraft;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModMinecraft implements ModInitializer {
    public static final String MOD_ID = "modminecraft";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("ModMinecraft loaded successfully!");
    }
}
