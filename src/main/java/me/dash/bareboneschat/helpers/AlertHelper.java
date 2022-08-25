package me.dash.bareboneschat.helpers;

import me.dash.bareboneschat.enums.AlertType;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class AlertHelper {
    private static final String PLUGIN_PREFIX = ChatColor.translateAlternateColorCodes('&', "&8&l[&b&lBBChat&8&l] ");

    private AlertHelper() {
        throw new UnsupportedOperationException("Helper class cannot be instantiated.");
    }




    public static void sendSuccess(Player player, String message) {
        player.spigot().sendMessage(generateAlert(AlertType.SUCCESS, message));
    }




    public static void sendError(Player player, String message) {
        player.spigot().sendMessage(generateAlert(AlertType.ERROR, message));
    }




    public static void sendInfo(Player player, String message) {
        player.spigot().sendMessage(generateAlert(AlertType.INFO, message));
    }




    public static void sendPermissionError(Player player) {
        player.spigot().sendMessage(generateAlert(AlertType.ERROR, "You do not have permission to run that command."));
    }




    private static TextComponent generateAlert(AlertType alertType, String message) {
        ChatColor chatColor = switch (alertType) {
            case SUCCESS -> ChatColor.GREEN;
            case ERROR -> ChatColor.RED;
            case INFO -> ChatColor.BLUE;
        };

        TextComponent mainComponent = new TextComponent();
        TextComponent messageComponent = new TextComponent(chatColor + message);

        TextComponent prefixComponent = new TextComponent(PLUGIN_PREFIX);
        prefixComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Click me!")));
        prefixComponent.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.google.com"));

        mainComponent.addExtra(prefixComponent);
        mainComponent.addExtra(messageComponent);
        return mainComponent;
    }
}
