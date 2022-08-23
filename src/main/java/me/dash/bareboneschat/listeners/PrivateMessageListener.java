package me.dash.bareboneschat.listeners;

import me.dash.bareboneschat.BareBonesChat;
import me.dash.bareboneschat.events.PrivateMessageEvent;
import me.dash.bareboneschat.helpers.MessageHelper;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

public class PrivateMessageListener implements Listener {
    private final ConfigurationSection privateMessagesSection;
    private final ConfigurationSection messageSpySection;
    private final BareBonesChat plugin;

    public PrivateMessageListener(BareBonesChat plugin) {
        FileConfiguration config = plugin.getConfig();
        this.privateMessagesSection = config.getConfigurationSection("Private_Messages");
        this.messageSpySection = config.getConfigurationSection("Message_Spy");
        this.plugin = plugin;
    }




    @EventHandler
    public void onPrivateMessage(PrivateMessageEvent event) {
        Player senderPlayer = event.getSenderPlayer();
        Player recipientPlayer = event.getRecipientPlayer();
        String message = event.getMessage();

        String senderMessage = MessageHelper.replaceMessagePlaceholders(privateMessagesSection.getString("Sender_Format"), recipientPlayer.getDisplayName(), message);
        String recipientMessage = MessageHelper.replaceMessagePlaceholders(privateMessagesSection.getString("Recipient_Format"), senderPlayer.getDisplayName(), message);

        senderPlayer.sendMessage(senderMessage);
        recipientPlayer.sendMessage(recipientMessage);

        plugin.dataManager.recipientSenderMap.put(recipientPlayer.getUniqueId(), senderPlayer.getUniqueId());

        String spyMessage = MessageHelper.replaceSpyMessagePlaceholders(messageSpySection.getString("Spy_Format"),
                senderPlayer.getDisplayName(), recipientPlayer.getDisplayName(), message);

        Stream<UUID> keys = plugin.dataManager.messageSpyPlayers.entrySet().stream()
                .filter(val -> val.getValue() == true)
                .map(Map.Entry::getKey);

        keys.forEach((UUID playerUUID) -> {
            Bukkit.getServer().getPlayer(playerUUID).sendMessage(spyMessage);
        });
    }
}
