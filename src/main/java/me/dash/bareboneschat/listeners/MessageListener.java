package me.dash.bareboneschat.listeners;

import me.dash.bareboneschat.BareBonesChat;
import me.dash.bareboneschat.helpers.MessageHelper;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class MessageListener implements Listener {
    private final ConfigurationSection groupFormatsSection;
    private final ConfigurationSection defaultFormatSection;
    private final BareBonesChat plugin;

    public MessageListener(BareBonesChat plugin) {
        groupFormatsSection = plugin.getConfig().getConfigurationSection("Global_Messages.Per_Group_Formats.Formats");
        defaultFormatSection = plugin.getConfig().getConfigurationSection("Global_Messages.Default_Format");
        this.plugin = plugin;
    }




    @EventHandler
    public void onPlayerMessage(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        boolean hasGroupPermission = false;

        for (String key : groupFormatsSection.getKeys(false)) {
            if ((plugin.permissionProvider != null && plugin.permissionProvider.playerInGroup(player, key.toLowerCase())) ||
                    player.hasPermission("group." + key.toLowerCase())) {
                event.setFormat(MessageHelper.replaceMessagePlaceholders(
                        groupFormatsSection.getString(key), player.getDisplayName(), event.getMessage()));

                hasGroupPermission = true;
                break;
            }
        }

        if (!hasGroupPermission && defaultFormatSection.getBoolean("Enabled")) {
            event.setFormat(MessageHelper.replaceMessagePlaceholders(
                    defaultFormatSection.getString("Format"), player.getDisplayName(), event.getMessage()));
        }
    }
}