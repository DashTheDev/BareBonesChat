package me.dash.bareboneschat.subcommands;

import me.dash.bareboneschat.PluginMessenger;
import me.dash.bareboneschat.commands.BareBonesChatCommand;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class HelpSubCommand implements BareBonesChatSubCommand {

    private final HashMap<String, BareBonesChatCommand> commands;
    private final HashMap<String, BareBonesChatSubCommand> subCommands;
    private final PluginMessenger pluginMessenger;

    public HelpSubCommand(HashMap<String, BareBonesChatCommand> commands, HashMap<String, BareBonesChatSubCommand> subCommands, PluginMessenger pluginMessenger) {
        this.commands = commands;
        this.subCommands = subCommands;
        this.pluginMessenger = pluginMessenger;
    }

    @Override
    public String getDisplayName() {
        return "Help";
    }

    @Override
    public String getDescription() {
        return "Not done yet.";
    }

    @Override
    public String getUsage() {
        return BareBonesChatSubCommand.super.getUsage() + " [command]";
    }

    @Override
    public boolean hasPermission(Player player) {
        return player.hasPermission("bbchat." + getName());
    }

    @Override
    public boolean executeCommand(CommandSender sender, String[] args) {
        Player player = (Player)sender;
        player.spigot().sendMessage(generateHelp());
        return true;
    }

    private TextComponent generateHelp() {
        TextComponent mainComponent = new TextComponent();
        TextComponent preHelpComponent = new TextComponent("Available commands.");
        mainComponent.addExtra(pluginMessenger.getPluginPrefixComponent());
        mainComponent.addExtra(preHelpComponent);

        for (BareBonesChatCommand command: commands.values()) {
            addCommandHelp(mainComponent, command.getDisplayName(), command.getDescription(), command.getUsage());
        }

        for (BareBonesChatSubCommand subCommand: subCommands.values()) {
            addCommandHelp(mainComponent, subCommand.getDisplayName(), subCommand.getDescription(), subCommand.getUsage());
        }

        return mainComponent;
    }

    private void addCommandHelp(TextComponent mainComponent, String name, String description, String usage) {
        TextComponent prefixComponent = new TextComponent(ChatColor.DARK_AQUA + "\n§l- ");

        TextComponent usageComponent = new TextComponent(usage);
        usageComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(ChatColor.AQUA + "§l" + name + "§r\n" + description)));
        usageComponent.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, usage));

        mainComponent.addExtra(prefixComponent);
        mainComponent.addExtra(usageComponent);
    }
}
