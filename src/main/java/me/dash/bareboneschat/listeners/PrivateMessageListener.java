package me.dash.bareboneschat.listeners;

import me.dash.bareboneschat.BareBonesChat;
import me.dash.bareboneschat.config.MessageSpySection;
import me.dash.bareboneschat.config.PrivateMessagesSection;
import me.dash.bareboneschat.data.DataManager;
import me.dash.bareboneschat.events.PrivateMessageEvent;
import me.dash.bareboneschat.events.SpyMessageEvent;
import me.dash.bareboneschat.helpers.MessageHelper;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PrivateMessageListener implements Listener {

    private final PrivateMessagesSection privateMessagesConfigSection;
    private final MessageSpySection messageSpyConfigSection;
    private final DataManager dataManager;

    public PrivateMessageListener(BareBonesChat plugin) {
        privateMessagesConfigSection = plugin.getConfigManager().getPrivateMessagesSection();
        messageSpyConfigSection = plugin.getConfigManager().getMessageSpySection();
        dataManager = plugin.getDataManager();
    }




    @EventHandler
    public void onPrivateMessage(PrivateMessageEvent event) {
        Player senderPlayer = event.getSenderPlayer();
        Player recipientPlayer = event.getRecipientPlayer();
        String message = event.getMessage();

        String senderMessage = MessageHelper.replaceMessagePlaceholders(
                privateMessagesConfigSection.getSenderFormat(),
                recipientPlayer.getDisplayName(),
                message);

        String recipientMessage = MessageHelper.replaceMessagePlaceholders(
                privateMessagesConfigSection.getRecipientFormat(),
                senderPlayer.getDisplayName(),
                message);

        senderPlayer.sendMessage(senderMessage);
        recipientPlayer.sendMessage(recipientMessage);

        dataManager.getRecipientSenderMap().put(recipientPlayer.getUniqueId(), senderPlayer.getUniqueId());

        if (messageSpyConfigSection.isEnabled()) {
            SpyMessageEvent spyMessageEvent = new SpyMessageEvent(senderPlayer.getDisplayName(), recipientPlayer.getDisplayName(), message);
            Bukkit.getServer().getPluginManager().callEvent(spyMessageEvent);
        }
    }
}
