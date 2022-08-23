package me.dash.bareboneschat.data;

import java.util.HashMap;
import java.util.UUID;

public class DataManager {
    public HashMap<UUID, Boolean> messageSpyPlayers;
    public HashMap<UUID, UUID> recipientSenderMap;

    public DataManager() {
        messageSpyPlayers = new HashMap<>();
        recipientSenderMap = new HashMap<>();
    }
}
