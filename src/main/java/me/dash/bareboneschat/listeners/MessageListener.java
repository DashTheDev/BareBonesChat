package me.dash.bareboneschat.listeners;

import me.dash.bareboneschat.BareBonesChat;
import me.dash.bareboneschat.config.GlobalMessagesSection;
import me.dash.bareboneschat.helpers.MessageHelper;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class MessageListener implements Listener {
    private final GlobalMessagesSection globalMessagesConfigSection;
    private final Permission permissionProvider;

    public MessageListener(BareBonesChat plugin) {
        globalMessagesConfigSection = plugin.getConfigManager().getGlobalMessagesSection();
        permissionProvider = plugin.getPermissionProvider();
    }




    @EventHandler
    public void onPlayerMessage(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        boolean hasGroupPermission = false;

        System.out.println(globalMessagesConfigSection);
        System.out.println(permissionProvider);
        for (String key : globalMessagesConfigSection.getGroupFormatKeys()) {
            if ((permissionProvider != null && permissionProvider.playerInGroup(player, key.toLowerCase())) ||
                    player.hasPermission("group." + key.toLowerCase())) {
                event.setFormat(MessageHelper.replaceMessagePlaceholders(
                        globalMessagesConfigSection.getGroupFormat(key), player.getDisplayName(), event.getMessage()));

                hasGroupPermission = true;
                break;
            }
        }
        System.out.println("testa");
        if (!hasGroupPermission && globalMessagesConfigSection.isDefaultFormatEnabled()) {
            event.setFormat(MessageHelper.replaceMessagePlaceholders(
                    globalMessagesConfigSection.getDefaultFormat(), player.getDisplayName(), event.getMessage()));
        }
    }
}