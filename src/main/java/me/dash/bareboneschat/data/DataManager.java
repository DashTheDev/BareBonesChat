package me.dash.bareboneschat.data;

import java.util.HashMap;
import java.util.UUID;

public class DataManager {
    private final HashMap<UUID, Boolean> messageSpyPlayers;
    private final HashMap<UUID, UUID> recipientSenderMap;

    public DataManager() {
        messageSpyPlayers = new HashMap<>();
        recipientSenderMap = new HashMap<>();
    }




    public HashMap<UUID, Boolean> getMessageSpyPlayers() {
        return messageSpyPlayers;
    }




    public HashMap<UUID, UUID> getRecipientSenderMap() {
        return recipientSenderMap;
    }
}
