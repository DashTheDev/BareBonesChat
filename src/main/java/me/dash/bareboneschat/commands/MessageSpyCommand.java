package me.dash.bareboneschat.commands;

import me.dash.bareboneschat.BareBonesChat;
import me.dash.bareboneschat.helpers.AlertHelper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageSpyCommand implements CommandExecutor {
    private final BareBonesChat plugin;

    public MessageSpyCommand(BareBonesChat plugin) {
        this.plugin = plugin;
    }




    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (!player.hasPermission("chatformatter.messagespy")) {
                AlertHelper.sendPermissionError(player);
                return true;
            }

            Boolean isActive = plugin.dataManager.messageSpyPlayers.get(player.getUniqueId());

            if (isActive == null) {
                plugin.dataManager.messageSpyPlayers.put(player.getUniqueId(), true);
                AlertHelper.sendSuccess(player, "Message Spy enabled.");
            }
            else {
                plugin.dataManager.messageSpyPlayers.put(player.getUniqueId(), !isActive);

                if (!isActive == true) {
                    AlertHelper.sendSuccess(player, "Message Spy enabled.");
                }
                else {
                    AlertHelper.sendError(player, "Message Spy disabled.");
                }
            }
        }

        return true;
    }
}
