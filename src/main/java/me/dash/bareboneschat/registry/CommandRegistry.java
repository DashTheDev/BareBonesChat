package me.dash.bareboneschat.registry;

import me.dash.bareboneschat.BareBonesChat;
import me.dash.bareboneschat.commands.*;
import org.bukkit.command.CommandExecutor;

import java.util.HashMap;

public final class CommandRegistry {
    private CommandRegistry() {
        throw new UnsupportedOperationException("Registry class cannot be instantiated.");
    }




    public static void registerCommands(BareBonesChat plugin) {
        HashMap<String, BareBonesChatCommand> commands = new HashMap<>();
        commands.put("message", new MessageCommand(plugin));
        commands.put("reply", new ReplyCommand(plugin));
        commands.put("messagespy", new MessageSpyCommand(plugin));
        commands.put("bbchat", new BBChatCommand(plugin, commands));

        for (BareBonesChatCommand command : commands.values()) {
            plugin.getCommand(command.getName()).setExecutor((CommandExecutor)command);
        }
    }
}
