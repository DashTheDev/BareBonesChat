package me.dash.bareboneschat.config;

import me.dash.bareboneschat.BareBonesChat;
import java.util.Set;

public class GlobalMessagesSection {

    private static final String SECTION_KEY = "Global_Messages.";
    private final BareBonesChat plugin;


    public GlobalMessagesSection(BareBonesChat plugin) {
        this.plugin = plugin;
    }




    public boolean isDefaultFormatEnabled() {
        return plugin.getConfig().getBoolean(SECTION_KEY + "Default_Format.Enabled");
    }




    public String getDefaultFormat() {
        return plugin.getConfig().getString(SECTION_KEY + "Default_Format.Format");
    }




    public boolean isPerGroupFormatsEnabled() {
        return plugin.getConfig().getBoolean(SECTION_KEY + "Per_Group_Formats.Enabled");
    }




    public Set<String> getGroupFormatKeys() {
        return plugin.getConfig().getConfigurationSection(SECTION_KEY + "Per_Group_Formats.Formats").getKeys(false);
    }




    public String getGroupFormat(String groupKey) {
        return plugin.getConfig().getString(SECTION_KEY + "Per_Group_Formats.Formats." + groupKey);
    }
}
