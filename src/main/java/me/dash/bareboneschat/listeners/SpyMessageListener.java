package me.dash.bareboneschat.listeners;

import me.dash.bareboneschat.BareBonesChat;
import me.dash.bareboneschat.config.MessageSpySection;
import me.dash.bareboneschat.data.DataManager;
import me.dash.bareboneschat.events.SpyMessageEvent;
import me.dash.bareboneschat.helpers.MessageHelper;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

public class SpyMessageListener implements Listener {

    private final MessageSpySection messageSpyConfigSection;
    private final DataManager dataManager;

    public SpyMessageListener(BareBonesChat plugin) {
        messageSpyConfigSection = plugin.getConfigManager().getMessageSpySection();
        dataManager = plugin.getDataManager();
    }




    @EventHandler
    public void onSpyMessage(SpyMessageEvent event) {
        String spyMessage = MessageHelper.replaceSpyMessagePlaceholders(messageSpyConfigSection.getSpyFormat(),
                event.getSenderPlayerName(), event.getRecipientPlayerName(), event.getMessage());

        Stream<UUID> keys = dataManager.getMessageSpyPlayers().entrySet().stream()
                .filter(val -> val.getValue())
                .map(Map.Entry::getKey);

        keys.forEach((UUID playerUUID) -> {
            Bukkit.getServer().getPlayer(playerUUID).sendMessage(spyMessage);
        });
    }
}
