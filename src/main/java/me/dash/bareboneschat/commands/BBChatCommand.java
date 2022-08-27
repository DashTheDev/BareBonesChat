package me.dash.bareboneschat.commands;

import me.dash.bareboneschat.BareBonesChat;
import me.dash.bareboneschat.PluginMessenger;
import me.dash.bareboneschat.subcommands.BareBonesChatSubCommand;
import me.dash.bareboneschat.subcommands.HelpSubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.*;

public class BBChatCommand implements BareBonesChatCommand, CommandExecutor {

    private final HashMap<String, BareBonesChatSubCommand> subCommands;
    private final PluginMessenger pluginMessenger;

    public BBChatCommand(BareBonesChat plugin, HashMap<String, BareBonesChatCommand> commands) {
        subCommands = new HashMap<>();
        pluginMessenger = plugin.getMessenger();
        registerSubCommands(commands);
    }

    private void registerSubCommands(HashMap<String, BareBonesChatCommand> commands) {
        subCommands.put("help", new HelpSubCommand(commands, subCommands, pluginMessenger));
    }

    @Override
    public String getDisplayName() {
        return "BBChat";
    }

    @Override
    public String getDescription() {
        return "BareBonesChat commands.";
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (args.length == 0) {
                pluginMessenger.sendError(player, "Please specify a subcommand.");
                return true;
            }

            BareBonesChatSubCommand subCommand = subCommands.get(args[0]);

            if (subCommand == null) {
                pluginMessenger.sendError(player, "Invalid subcommand.");
                return true;
            }

            if (!subCommand.hasPermission(player)) {
                pluginMessenger.sendPermissionError(player);
                return true;
            }

            subCommand.executeCommand(sender, Arrays.copyOfRange(args, 1, args.length));
        }

        return true;
    }
}
