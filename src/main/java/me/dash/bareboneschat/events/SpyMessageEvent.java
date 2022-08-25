package me.dash.bareboneschat.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class SpyMessageEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private final String senderPlayerName;
    private final String recipientPlayerName;
    private final String message;

    public SpyMessageEvent(String senderPlayerName, String recipientPlayerName, String message) {
        this.senderPlayerName = senderPlayerName;
        this.recipientPlayerName = recipientPlayerName;
        this.message = message;
    }

    public String getSenderPlayerName() {
        return senderPlayerName;
    }

    public String getRecipientPlayerName() {
        return recipientPlayerName;
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
