package me.dash.bareboneschat.subcommands;

import me.dash.bareboneschat.commands.BareBonesChatCommand;
import org.bukkit.command.CommandSender;

public interface BareBonesChatSubCommand extends BareBonesChatCommand {

    boolean executeCommand(CommandSender sender, String[] args);

    @Override
    default String getUsage() {
        return "/bbchat " + getName();
    }
}
