package me.dash.bareboneschat.commands;

import me.dash.bareboneschat.BareBonesChat;
import me.dash.bareboneschat.PluginMessenger;
import me.dash.bareboneschat.data.PlayerData;
import me.dash.bareboneschat.data.PlayerDataManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageSpyCommand implements BareBonesChatCommand, CommandExecutor {

    private final PlayerDataManager playerDataManager;
    private final PluginMessenger pluginMessenger;

    public MessageSpyCommand(BareBonesChat plugin) {
        playerDataManager = plugin.getPlayerDataManager();
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

            PlayerData playerData = playerDataManager.getPlayerData(player.getUniqueId());
            playerData.toggleMessageSpyEnabled();

            if (playerData.getMessageSpyEnabled()) {
                pluginMessenger.sendSuccess(player, "Message Spy enabled.");
            } else {
                pluginMessenger.sendError(player, "Message Spy disabled.");
            }
        }

        return true;
    }
}
