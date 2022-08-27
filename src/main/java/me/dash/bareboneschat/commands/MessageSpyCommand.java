package me.dash.bareboneschat.commands;

import me.dash.bareboneschat.BareBonesChat;
import me.dash.bareboneschat.PluginMessenger;
import me.dash.bareboneschat.data.DataManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageSpyCommand implements BareBonesChatCommand, CommandExecutor {

    private final DataManager dataManager;
    private final PluginMessenger pluginMessenger;

    public MessageSpyCommand(BareBonesChat plugin) {
        dataManager = plugin.getDataManager();
        pluginMessenger = plugin.getMessenger();
    }

    @Override
    public String getDisplayName() {
        return "Message Spy";
    }

    @Override
    public String getDescription() {
        return "Toggle the ability to see private other players' messages.";
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (!hasPermission(player)) {
                pluginMessenger.sendPermissionError(player);
                return true;
            }

            Boolean isActive = dataManager.getMessageSpyPlayers().get(player.getUniqueId());

            if (isActive == null) {
                dataManager.getMessageSpyPlayers().put(player.getUniqueId(), true);
                pluginMessenger.sendSuccess(player, "Message Spy enabled.");
            }
            else {
                dataManager.getMessageSpyPlayers().put(player.getUniqueId(), !isActive);

                if (!isActive) {
                    pluginMessenger.sendSuccess(player, "Message Spy enabled.");
                }
                else {
                    pluginMessenger.sendError(player, "Message Spy disabled.");
                }
            }
        }

        return true;
    }
}
