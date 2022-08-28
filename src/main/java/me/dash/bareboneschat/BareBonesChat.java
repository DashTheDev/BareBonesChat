package me.dash.bareboneschat;

import me.dash.bareboneschat.config.ConfigManager;
import me.dash.bareboneschat.data.PlayerDataManager;
import me.dash.bareboneschat.registry.CommandRegistry;
import me.dash.bareboneschat.registry.ListenerRegistry;
import me.dash.bareboneschat.registry.VaultRegistry;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.plugin.java.JavaPlugin;

public final class BareBonesChat extends JavaPlugin {

    private PluginMessenger messenger;
    private ConfigManager configManager;
    private PlayerDataManager playerDataManager;
    private Permission permissionProvider;

    public PluginMessenger getMessenger() {
        return messenger;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public PlayerDataManager getPlayerDataManager() {
        return playerDataManager;
    }

    public Permission getPermissionProvider() {
        return permissionProvider;
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();

        messenger = new PluginMessenger(this);
        configManager = new ConfigManager(this);
        playerDataManager = new PlayerDataManager(this);

        permissionProvider = VaultRegistry.registerPermissionsProvider(this);
        ListenerRegistry.registerListeners(this);
        CommandRegistry.registerCommands(this);
    }

    @Override
    public void onDisable() {
        playerDataManager.saveAllPlayerDataToYml();
    }
}
