package me.dash.bareboneschat.listeners;

import me.dash.bareboneschat.BareBonesChat;
import me.dash.bareboneschat.data.DataManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    private final DataManager dataManager;

    public JoinListener(BareBonesChat plugin) {
        dataManager = plugin.getDataManager();
    }




    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (player.hasPermission("chatformatter.messagespy")) {
            dataManager.getMessageSpyPlayers().put(player.getUniqueId(), false);
        }
    }
}
