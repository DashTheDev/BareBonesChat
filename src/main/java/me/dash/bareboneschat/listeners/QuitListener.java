package me.dash.bareboneschat.listeners;

import me.dash.bareboneschat.BareBonesChat;
import me.dash.bareboneschat.data.PlayerDataManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener {

    private final PlayerDataManager playerDataManager;

    public QuitListener(BareBonesChat plugin) {
        playerDataManager = plugin.getPlayerDataManager();
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        playerDataManager.setPlayerData(event.getPlayer().getUniqueId(), null);
    }
}
