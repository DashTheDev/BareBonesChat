package me.dash.bareboneschat.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PrivateMessageEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private Player senderPlayer;
    private Player recipientPlayer;
    private String message;

    public PrivateMessageEvent(Player senderPlayer, Player recipientPlayer, String message) {
        this.senderPlayer = senderPlayer;
        this.recipientPlayer = recipientPlayer;
        this.message = message;
    }

    public Player getSenderPlayer() {
        return senderPlayer;
    }

    public Player getRecipientPlayer() {
        return recipientPlayer;
    }

    public String getMessage() {
        return message;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}
