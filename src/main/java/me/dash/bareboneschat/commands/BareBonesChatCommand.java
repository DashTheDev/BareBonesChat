package me.dash.bareboneschat.commands;

import org.bukkit.entity.Player;

public interface BareBonesChatCommand {

    String getDisplayName();

    String getDescription();

    default String getName() {
        return getDisplayName().toLowerCase().replaceAll("\\s+","");
    }

    default String getUsage() {
        return "/" + getName();
    }

    default boolean hasPermission(Player player) {
        return player.hasPermission("bbchat." + getName());
    }
}
