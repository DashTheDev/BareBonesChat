package me.dash.bareboneschat;

import me.dash.bareboneschat.enums.AlertType;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class PluginMessenger {

    private static final String PLUGIN_PREFIX = "§3§l[§b§lBBChat§3§l] ";
    private TextComponent pluginPrefixComponent;

    public PluginMessenger(BareBonesChat plugin) {
        TextComponent prefixComponent = new TextComponent(PLUGIN_PREFIX);
        prefixComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(ChatColor.AQUA + "§l" + "BareBonesChat " + ChatColor.GRAY + "(v" + plugin.getDescription().getVersion() + ")" + "§r\n" + "A lightweight chat plugin.")));
        prefixComponent.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/DashTheDev/BareBonesChat"));
        pluginPrefixComponent = prefixComponent;
    }

    public TextComponent getPluginPrefixComponent() {
        return pluginPrefixComponent;
    }

    public void sendSuccess(Player player, String message) {
        player.spigot().sendMessage(generateAlert(AlertType.SUCCESS, message));
    }

    public void sendError(Player player, String message) {
        player.spigot().sendMessage(generateAlert(AlertType.ERROR, message));
    }

    public void sendInfo(Player player, String message) {
        player.spigot().sendMessage(generateAlert(AlertType.INFO, message));
    }

    public void sendPermissionError(Player player) {
        player.spigot().sendMessage(generateAlert(AlertType.ERROR, "You do not have permission to run that command."));
    }

    private TextComponent generateAlert(AlertType alertType, String message) {
        ChatColor chatColor = switch (alertType) {
            case SUCCESS -> ChatColor.GREEN;
            case ERROR -> ChatColor.RED;
            case INFO -> ChatColor.BLUE;
        };

        TextComponent mainComponent = new TextComponent();
        TextComponent messageComponent = new TextComponent(chatColor + message);

        mainComponent.addExtra(pluginPrefixComponent);
        mainComponent.addExtra(messageComponent);
        return mainComponent;
    }
}
