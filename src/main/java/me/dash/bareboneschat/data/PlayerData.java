package me.dash.bareboneschat.data;

import java.util.HashSet;
import java.util.UUID;

public class PlayerData {
    private UUID lastMessagePlayerUUID;
    private final HashSet<UUID> blockedPlayerUUIDs;
    private boolean messageSpyEnabled;

    public PlayerData() {
        this.lastMessagePlayerUUID = null;
        this.blockedPlayerUUIDs = new HashSet<>();
        this.messageSpyEnabled = false;
    }

    public UUID getLastMessagePlayerUUID() {
        return lastMessagePlayerUUID;
    }

    public void setLastMessagePlayerUUID(UUID lastMessagePlayerUUID) {
        this.lastMessagePlayerUUID = lastMessagePlayerUUID;
    }

    public HashSet<UUID> getBlockedPlayerUUIDs() {
        return blockedPlayerUUIDs;
    }

    public boolean getMessageSpyEnabled() {
        return messageSpyEnabled;
    }

    public void setMessageSpyEnabled(boolean value) {
        this.messageSpyEnabled = value;
    }

    public void toggleMessageSpyEnabled() {
        messageSpyEnabled = !messageSpyEnabled;
    }
}
