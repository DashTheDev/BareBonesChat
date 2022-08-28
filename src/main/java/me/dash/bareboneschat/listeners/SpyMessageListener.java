package me.dash.bareboneschat.listeners;

import me.dash.bareboneschat.BareBonesChat;
import me.dash.bareboneschat.config.MessageSpySection;
import me.dash.bareboneschat.data.PlayerDataManager;
import me.dash.bareboneschat.events.SpyMessageEvent;
import me.dash.bareboneschat.helpers.MessageHelper;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.UUID;

public class SpyMessageListener implements Listener {

    private final MessageSpySection messageSpyConfigSection;
    private final PlayerDataManager playerDataManager;

    public SpyMessageListener(BareBonesChat plugin) {
        messageSpyConfigSection = plugin.getConfigManager().getMessageSpySection();
        playerDataManager = plugin.getPlayerDataManager();
    }

    @EventHandler
    public void onSpyMessage(SpyMessageEvent event) {
        String spyMessage = MessageHelper.replaceSpyMessagePlaceholders(messageSpyConfigSection.getSpyFormat(),
                event.getSenderPlayerName(), event.getRecipientPlayerName(), event.getMessage());

        playerDataManager.getMessageSpyEnabledPlayerUUIDs().forEach((UUID playerUUID) -> {
            Player player = Bukkit.getServer().getPlayer(playerUUID);

            if (!player.hasPermission("bbchat.messagespy")) {
                playerDataManager.getPlayerData(playerUUID).setMessageSpyEnabled(false);
            } else {
                Bukkit.getServer().getPlayer(playerUUID).sendMessage(spyMessage);
            }
        });
    }
}
