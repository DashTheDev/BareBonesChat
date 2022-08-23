package me.dash.bareboneschat.listeners;

import me.dash.bareboneschat.BareBonesChat;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener {
    private final BareBonesChat plugin;

    public QuitListener(BareBonesChat plugin) {
        this.plugin = plugin;
    }




    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        plugin.dataManager.recipientSenderMap.remove(player.getUniqueId());
        plugin.dataManager.messageSpyPlayers.remove(player.getUniqueId());
    }
}
