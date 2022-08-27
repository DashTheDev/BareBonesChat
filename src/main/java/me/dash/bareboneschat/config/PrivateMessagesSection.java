package me.dash.bareboneschat.config;

import me.dash.bareboneschat.BareBonesChat;

public class PrivateMessagesSection {

    private static final String SECTION_KEY = "Private_Messages.";
    private final BareBonesChat plugin;

    public PrivateMessagesSection(BareBonesChat plugin) {
        this.plugin = plugin;
    }

    public boolean isEnabled() {
        return plugin.getConfig().getBoolean(SECTION_KEY + "Enabled");
    }

    public String getSenderFormat() {
        return plugin.getConfig().getString(SECTION_KEY + "Sender_Format");
    }

    public String getRecipientFormat() {
        return plugin.getConfig().getString(SECTION_KEY + "Recipient_Format");
    }
}
