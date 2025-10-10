package me.dexrn.rrdiscordbridge.extensions;

import com.google.gson.annotations.Expose;
import me.dexrn.rrdiscordbridge.extension.config.options.AbstractExtensionOptions;

public class ExampleBridgeExtensionOptions extends AbstractExtensionOptions {
    /** Whether to change the message coming from Minecraft */
    @Expose() public boolean changeMessage = true;
}
