package me.dash.bareboneschat.config;

import me.dash.bareboneschat.BareBonesChat;

public class MessageSpySection {

    private static final String SECTION_KEY = "Message_Spy.";
    private final BareBonesChat plugin;

    public MessageSpySection(BareBonesChat plugin) {
        this.plugin = plugin;
    }

    public boolean isEnabled() {
        return plugin.getConfig().getBoolean(SECTION_KEY + "Enabled");
    }

    public boolean getRememberToggle() {
        return plugin.getConfig().getBoolean(SECTION_KEY + "RememberToggle");
    }

    public String getSpyFormat() {
        return plugin.getConfig().getString(SECTION_KEY + "Spy_Format");
    }
}
