package me.dash.bareboneschat.listeners;

import me.dash.bareboneschat.BareBonesChat;
import me.dash.bareboneschat.data.PlayerData;
import me.dash.bareboneschat.data.PlayerDataManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    private final PlayerDataManager playerDataManager;

    public JoinListener(BareBonesChat plugin) {
        playerDataManager = plugin.getPlayerDataManager();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        PlayerData playerData = playerDataManager.getPlayerData(player.getUniqueId());

        if (!player.hasPermission("bbchat.messagespy") && playerData.getMessageSpyEnabled()) {
            playerData.setMessageSpyEnabled(false);
        }
    }
}
