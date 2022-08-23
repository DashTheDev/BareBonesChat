package me.dash.bareboneschat.registry;

import me.dash.bareboneschat.BareBonesChat;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.plugin.RegisteredServiceProvider;

public final class VaultRegistry {
    private VaultRegistry() {
        throw new UnsupportedOperationException("Registry class cannot be instantiated.");
    }




    public static Permission registerPermissionsProvider(BareBonesChat plugin) {
        try {
            if (plugin.getServer().getPluginManager().getPlugin("Vault") == null) {
                throw new Exception();
            }

            RegisteredServiceProvider<Permission> rsp = plugin.getServer().getServicesManager().getRegistration(Permission.class);
            Permission permissionProvider = rsp.getProvider();

            if (permissionProvider == null) {
                throw new Exception();
            }

            return permissionProvider;
        }
        catch (Exception e) {
            plugin.logger.info("Vault not found, some permission based functionality may not work!");
            return null;
        }
    }
}
