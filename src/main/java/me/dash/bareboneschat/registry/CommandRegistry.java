package me.dash.bareboneschat.registry;

import me.dash.bareboneschat.BareBonesChat;
import me.dash.bareboneschat.commands.MessageCommand;
import me.dash.bareboneschat.commands.MessageSpyCommand;
import me.dash.bareboneschat.commands.ReplyCommand;

public final class CommandRegistry {
    private CommandRegistry() {
        throw new UnsupportedOperationException("Registry class cannot be instantiated.");
    }




    public static void registerCommands(BareBonesChat plugin) {
        plugin.getCommand("message").setExecutor(new MessageCommand());
        plugin.getCommand("reply").setExecutor(new ReplyCommand(plugin));
        plugin.getCommand("messagespy").setExecutor(new MessageSpyCommand(plugin));
    }
}
