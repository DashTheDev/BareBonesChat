package me.dash.bareboneschat.config;

import me.dash.bareboneschat.BareBonesChat;

public class ConfigManager {

    private final GlobalMessagesSection globalMessagesSection;
    private final PrivateMessagesSection privateMessagesSection;
    private final MessageSpySection messageSpySection;

    public ConfigManager(BareBonesChat plugin) {
        globalMessagesSection = new GlobalMessagesSection(plugin);
        privateMessagesSection = new PrivateMessagesSection(plugin);
        messageSpySection = new MessageSpySection(plugin);
    }




    public GlobalMessagesSection getGlobalMessagesSection() {
        return globalMessagesSection;
    }




    public PrivateMessagesSection getPrivateMessagesSection() {
        return privateMessagesSection;
    }




    public MessageSpySection getMessageSpySection() {
        return messageSpySection;
    }
}
