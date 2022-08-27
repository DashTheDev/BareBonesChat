package me.dash.bareboneschat.helpers;

import org.bukkit.ChatColor;

public final class MessageHelper {
    private MessageHelper() {
        throw new UnsupportedOperationException("Helper class cannot be instantiated.");
    }




    public static String buildMessageFromArgs(int startIndex, String[] args) {
        StringBuilder messageStringBuilder = new StringBuilder();

        for (int i = startIndex; i < args.length; i++) {
            messageStringBuilder.append(args[i] + " ");
        }

        return messageStringBuilder.toString();
    }




    public static String replaceMessagePlaceholders(String format, String playerName, String message) {
        return ChatColor.translateAlternateColorCodes('&', format.replace("<PLAYER>", playerName)
                .replace("<MESSAGE>", message));
    }




    public static String replaceSpyMessagePlaceholders(String format, String playerOneName, String playerTwoName, String message) {
        return ChatColor.translateAlternateColorCodes('&', format.replace("<PLAYER_ONE>", playerOneName)
                .replace("<PLAYER_TWO>", playerTwoName)
                .replace("<MESSAGE>", message));
    }
}
