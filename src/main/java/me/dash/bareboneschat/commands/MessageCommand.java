package me.dash.bareboneschat.commands;

import me.dash.bareboneschat.BareBonesChat;
import me.dash.bareboneschat.PluginMessenger;
import me.dash.bareboneschat.events.PrivateMessageEvent;
import me.dash.bareboneschat.helpers.MessageHelper;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageCommand implements BareBonesChatCommand, CommandExecutor {

    private final PluginMessenger pluginMessenger;

    public MessageCommand(BareBonesChat plugin) {
        pluginMessenger = plugin.getMessenger();
    }

    @Override
    public String getDisplayName() {
        return "Message";
    }

    @Override
    public String getDescription() {
        return "Privately send a message to a player.";
    }

    @Override
    public String getUsage() {
        return BareBonesChatCommand.super.getUsage() + " <player> <message>";
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player senderPlayer) {
            if (!hasPermission(senderPlayer)) {
                pluginMessenger.sendPermissionError(senderPlayer);
                return true;
            }

            if (args.length == 0) {
                pluginMessenger.sendError(senderPlayer, "Please specify a username and a message.");
                return true;
            }

            if (args.length == 1) {
                pluginMessenger.sendError(senderPlayer, "Please specify a message.");
                return true;
            }

            Player recipientPlayer = Bukkit.getServer().getPlayerExact(args[0]);

            if (recipientPlayer == null) {
                pluginMessenger.sendError(senderPlayer, "Player could not be found.");
                return true;
            }

            String message = MessageHelper.buildMessageFromArgs(1, args);
            PrivateMessageEvent privateMessageEvent = new PrivateMessageEvent(senderPlayer, recipientPlayer, message);
            Bukkit.getServer().getPluginManager().callEvent(privateMessageEvent);
        }

        return true;
    }
}
