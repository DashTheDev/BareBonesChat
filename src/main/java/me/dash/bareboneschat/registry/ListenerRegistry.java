package me.dash.bareboneschat.registry;

import me.dash.bareboneschat.BareBonesChat;
import me.dash.bareboneschat.listeners.JoinListener;
import me.dash.bareboneschat.listeners.MessageListener;
import me.dash.bareboneschat.listeners.PrivateMessageListener;
import me.dash.bareboneschat.listeners.QuitListener;
import org.bukkit.plugin.PluginManager;

public class ListenerRegistry {
    private ListenerRegistry() {
        throw new UnsupportedOperationException("Registry class cannot be instantiated.");
    }




    public static void registerListeners(BareBonesChat plugin) {
        PluginManager pluginManager = plugin.getServer().getPluginManager();
        pluginManager.registerEvents(new MessageListener(plugin), plugin);
        pluginManager.registerEvents(new QuitListener(plugin), plugin);
        pluginManager.registerEvents(new JoinListener(plugin), plugin);
        pluginManager.registerEvents(new PrivateMessageListener(plugin), plugin);
    }
}
