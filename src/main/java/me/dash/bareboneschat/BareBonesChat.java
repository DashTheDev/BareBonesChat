package me.dash.bareboneschat;

import me.dash.bareboneschat.data.DataManager;
import me.dash.bareboneschat.registry.CommandRegistry;
import me.dash.bareboneschat.registry.ListenerRegistry;
import me.dash.bareboneschat.registry.VaultRegistry;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class BareBonesChat extends JavaPlugin {

    public Logger logger;
    public DataManager dataManager;
    public Permission permissionProvider;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        logger = getLogger();
        dataManager = new DataManager();

        permissionProvider = VaultRegistry.registerPermissionsProvider(this);
        ListenerRegistry.registerListeners(this);
        CommandRegistry.registerCommands(this);
    }

    @Override
    public void onDisable() {
    }
}
