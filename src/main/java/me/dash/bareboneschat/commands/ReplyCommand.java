package me.dash.bareboneschat.commands;

import me.dash.bareboneschat.BareBonesChat;
import me.dash.bareboneschat.events.PrivateMessageEvent;
import me.dash.bareboneschat.helpers.AlertHelper;
import me.dash.bareboneschat.helpers.MessageHelper;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ReplyCommand implements CommandExecutor {
    private final BareBonesChat plugin;

    public ReplyCommand(BareBonesChat plugin) {
        this.plugin = plugin;
    }




    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player senderPlayer) {
            if (!senderPlayer.hasPermission("chatformatter.reply")) {
                AlertHelper.sendPermissionError(senderPlayer);
                return true;
            }

            UUID recipientPlayerUUID = plugin.dataManager.recipientSenderMap.get(senderPlayer.getUniqueId());

            if (recipientPlayerUUID == null) {
                AlertHelper.sendError(senderPlayer, "You have not been messaged.");
                return true;
            }

            if (args.length == 0) {
                AlertHelper.sendError(senderPlayer, "Please specify a message");
                return true;
            }

            Player recipientPlayer = Bukkit.getServer().getPlayer(recipientPlayerUUID);

            if (recipientPlayer == null) {
                AlertHelper.sendError(senderPlayer, "Player could not be found.");
                return true;
            }

            String message = MessageHelper.buildMessageFromArgs(0, args);
            PrivateMessageEvent privateMessageEvent = new PrivateMessageEvent(senderPlayer, recipientPlayer, message);
            Bukkit.getServer().getPluginManager().callEvent(privateMessageEvent);
        }

        return true;
    }
}
