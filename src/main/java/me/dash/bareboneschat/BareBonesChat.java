package me.dash.bareboneschat;

import me.dash.bareboneschat.config.ConfigManager;
import me.dash.bareboneschat.data.DataManager;
import me.dash.bareboneschat.registry.CommandRegistry;
import me.dash.bareboneschat.registry.ListenerRegistry;
import me.dash.bareboneschat.registry.VaultRegistry;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.plugin.java.JavaPlugin;

public final class BareBonesChat extends JavaPlugin {

    //region Private Variables

    private ConfigManager configManager;
    private DataManager dataManager;
    private Permission permissionProvider;

    //endregion

    //region Variable Getters

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public Permission getPermissionProvider() {
        return permissionProvider;
    }

    //endregion

    @Override
    public void onEnable() {
        saveDefaultConfig();

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
