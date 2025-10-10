package me.dexrn.rrdiscordbridge.extensions;

import com.vdurmont.semver4j.Semver;
import me.dexrn.rrdiscordbridge.RRDiscordBridge;
import me.dexrn.rrdiscordbridge.extension.AbstractBridgeExtension;
import me.dexrn.rrdiscordbridge.extension.ExtensionPriority;
import me.dexrn.rrdiscordbridge.extension.config.ExtensionConfig;
import me.dexrn.rrdiscordbridge.extension.event.events.ExtensionEvents;
import me.dexrn.rrdiscordbridge.extension.event.events.chat.DiscordChatEvent;
import me.dexrn.rrdiscordbridge.extension.event.events.chat.MinecraftChatEvent;
import me.dexrn.rrdiscordbridge.extension.event.registry.ExtensionEventRegistry;
import net.dv8tion.jda.api.entities.emoji.Emoji;

import javax.annotation.Nullable;
import java.io.IOException;

public class ExampleBridgeExtension extends AbstractBridgeExtension {
    public ExtensionConfig config;

    /** Initializes the config */
    public ExampleBridgeExtension() throws IOException {
        // Options are wrapped by a config class, which handles things such as I/O
        config = new ExtensionConfig(new ExampleBridgeExtensionOptions(), this.getVersion(), this.getName()).load();
    }

    @Override
    public void onRegister(RRDiscordBridge inst) {
        RRDiscordBridge.logger.info("I have been registered!");

        // now you have the instance, so do whatever you want with it
        // store the instance in a field if used outside onRegister
        inst.getServer().broadcastMessage("Hello, world!");
    }

    @Override
    public void onEnable() {
        // prints "It works:tm:"
        RRDiscordBridge.logger.info("It works:tm:");

        ExtensionEventRegistry.getInstance()
                .register(this, ExtensionEvents.MINECRAFT_CHAT, this::onMinecraftChat);
        ExtensionEventRegistry.getInstance()
                .register(this, ExtensionEvents.DISCORD_CHAT, this::onDiscordChat);
    }

    @Override
    public void onDisable() {
        // unregister handlers when disabling
        ExtensionEventRegistry.getInstance().unregisterAll(this);
    }

    public void onMinecraftChat(MinecraftChatEvent ev) {
        ExampleBridgeExtensionOptions options = (ExampleBridgeExtensionOptions)(config.options);

        // Changes the message that goes to Discord, but not Minecraft
        if (options.changeMessage)
            ev.setMessage("This message was changed by ExampleExtension.");
    }

    public void onDiscordChat(DiscordChatEvent ev) {
        // Interacts with the Discord message
        ev.getMessage().addReaction(Emoji.fromUnicode("👋")).complete();
    }

    @Override
    public String getName() {
        return "ExampleBridgeExtension";
    }

    @Override
    public String getAuthor() {
        return "DexrnZacAttack";
    }

    @Override
    public Semver getCompatibleVersion() {
        return new Semver("3.0.0");
    }

    @Nullable
    @Override
    public ExtensionConfig getConfig() {
        return config;
    }

    @Override
    public Semver getVersion() {
        return new Semver("1.1.0");
    }

    @Override
    public ExtensionPriority getPriority() {
        return ExtensionPriority.HIGH;
    }
}