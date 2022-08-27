package me.dash.bareboneschat;

import me.dash.bareboneschat.config.ConfigManager;
import me.dash.bareboneschat.data.DataManager;
import me.dash.bareboneschat.registry.CommandRegistry;
import me.dash.bareboneschat.registry.ListenerRegistry;
import me.dash.bareboneschat.registry.VaultRegistry;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.plugin.java.JavaPlugin;

public final class BareBonesChat extends JavaPlugin {

    private PluginMessenger messenger;
    private ConfigManager configManager;
    private DataManager dataManager;
    private Permission permissionProvider;

    public PluginMessenger getMessenger() {
        return messenger;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public Permission getPermissionProvider() {
        return permissionProvider;
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();

        messenger = new PluginMessenger(this);
        configManager = new ConfigManager(this);
        dataManager = new DataManager();

        permissionProvider = VaultRegistry.registerPermissionsProvider(this);
        ListenerRegistry.registerListeners(this);
        CommandRegistry.registerCommands(this);
    }

    @Override
    public void onDisable() {
    }
}
