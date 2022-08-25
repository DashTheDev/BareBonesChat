package me.dash.bareboneschat.listeners;

import me.dash.bareboneschat.BareBonesChat;
import me.dash.bareboneschat.data.DataManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener {

    private final DataManager dataManager;

    public QuitListener(BareBonesChat plugin) {
        dataManager = plugin.getDataManager();
    }




    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        dataManager.getRecipientSenderMap().remove(player.getUniqueId());
        dataManager.getMessageSpyPlayers().remove(player.getUniqueId());
    }
}
