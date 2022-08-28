package me.dash.bareboneschat.commands;

import me.dash.bareboneschat.BareBonesChat;
import me.dash.bareboneschat.PluginMessenger;
import me.dash.bareboneschat.data.PlayerDataManager;
import me.dash.bareboneschat.events.PrivateMessageEvent;
import me.dash.bareboneschat.helpers.MessageHelper;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ReplyCommand implements BareBonesChatCommand, CommandExecutor {

    private final PlayerDataManager playerDataManager;
    private final PluginMessenger pluginMessenger;

    public ReplyCommand(BareBonesChat plugin) {
        playerDataManager = plugin.getPlayerDataManager();
        pluginMessenger = plugin.getMessenger();
    }

    @Override
    public String getDisplayName() {
        return "Reply";
    }

    @Override
    public String getDescription() {
        return "Reply to a private message.";
    }

    @Override
    public String getUsage() {
        return BareBonesChatCommand.super.getUsage() + " <message>";
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player senderPlayer) {
            if (!hasPermission(senderPlayer)) {
                pluginMessenger.sendPermissionError(senderPlayer);
                return true;
            }

            UUID recipientPlayerUUID = playerDataManager.getPlayerData(senderPlayer.getUniqueId()).getLastMessagePlayerUUID();

            if (recipientPlayerUUID == null) {
                pluginMessenger.sendError(senderPlayer, "You have not been messaged.");
                return true;
            }

            if (args.length == 0) {
                pluginMessenger.sendError(senderPlayer, "Please specify a message");
                return true;
            }

            Player recipientPlayer = Bukkit.getServer().getPlayer(recipientPlayerUUID);

            if (recipientPlayer == null) {
                pluginMessenger.sendError(senderPlayer, "Player could not be found.");
                return true;
            }

            String message = MessageHelper.buildMessageFromArgs(0, args);
            PrivateMessageEvent privateMessageEvent = new PrivateMessageEvent(senderPlayer, recipientPlayer, message);
            Bukkit.getServer().getPluginManager().callEvent(privateMessageEvent);
        }

        return true;
    }
}
