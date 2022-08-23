package me.dash.bareboneschat.listeners;

import me.dash.bareboneschat.BareBonesChat;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
    private final BareBonesChat plugin;

    public JoinListener(BareBonesChat plugin) {
        this.plugin = plugin;
    }




    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (player.hasPermission("chatformatter.messagespy")) {
            plugin.dataManager.messageSpyPlayers.put(player.getUniqueId(), false);
        }
    }
}
