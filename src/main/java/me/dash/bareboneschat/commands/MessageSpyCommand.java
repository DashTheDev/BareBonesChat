package me.dash.bareboneschat.commands;

import me.dash.bareboneschat.BareBonesChat;
import me.dash.bareboneschat.data.DataManager;
import me.dash.bareboneschat.helpers.AlertHelper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageSpyCommand implements CommandExecutor {

    private final DataManager dataManager;

    public MessageSpyCommand(BareBonesChat plugin) {
        dataManager = plugin.getDataManager();
    }




    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (!player.hasPermission("bbchat.messagespy")) {
                AlertHelper.sendPermissionError(player);
                return true;
            }

            Boolean isActive = dataManager.getMessageSpyPlayers().get(player.getUniqueId());

            if (isActive == null) {
                dataManager.getMessageSpyPlayers().put(player.getUniqueId(), true);
                AlertHelper.sendSuccess(player, "Message Spy enabled.");
            }
            else {
                dataManager.getMessageSpyPlayers().put(player.getUniqueId(), !isActive);

                if (!isActive) {
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
