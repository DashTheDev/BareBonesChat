package me.dash.bareboneschat.data;

import me.dash.bareboneschat.BareBonesChat;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

public class PlayerDataManager {

    private final File playerDataFile;
    private final FileConfiguration playerDataYml;
    private final HashMap<UUID, PlayerData> playerDataMap;

    public PlayerDataManager(BareBonesChat plugin) {
        this.playerDataFile = new File(plugin.getDataFolder().getAbsolutePath() + "/playerdata.yml");
        this.playerDataYml = YamlConfiguration.loadConfiguration(playerDataFile);
        this.playerDataMap = new HashMap<>();
    }

    public PlayerData getPlayerData(UUID playerUUID) {
        if (!playerDataMap.containsKey(playerUUID)) {
            PlayerData playerData = getPlayerDataFromYml(playerUUID);

            if (playerData == null) {
                playerData = new PlayerData();
                savePlayerDataToYml(playerUUID, playerData);
            }
            
            playerDataMap.put(playerUUID, playerData);
            return playerData;
        }

        return playerDataMap.get(playerUUID);
    }

    public void setPlayerData(UUID playerUUID, PlayerData playerData) {
        if (playerData == null) {
            PlayerData foundPlayerData = playerDataMap.get(playerUUID);
            
            if (foundPlayerData != null) {
                savePlayerDataToYml(playerUUID, foundPlayerData);
            }

            playerDataMap.remove(playerUUID);
        } else {
            playerDataMap.put(playerUUID, playerData);
        }
    }

    private PlayerData getPlayerDataFromYml(UUID playerUUID) {
        if (playerDataYml.contains(playerUUID.toString())) {
            PlayerData playerData = new PlayerData();
            playerData.setMessageSpyEnabled(playerDataYml.getBoolean(playerUUID + ".MessageSpyEnabled"));
            return playerData;
        } else {
            return null;
        }
    }
    
    private void savePlayerDataToYml(UUID playerUUID, PlayerData playerData) {
        playerDataYml.set(playerUUID + ".MessageSpyEnabled", playerData.getMessageSpyEnabled());

        try {
            playerDataYml.save(playerDataFile);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void saveAllPlayerDataToYml() {
        for (Map.Entry<UUID, PlayerData> entry : playerDataMap.entrySet()) {
            savePlayerDataToYml(entry.getKey(), entry.getValue());
        }
    }

    public Stream<UUID> getMessageSpyEnabledPlayerUUIDs() {
        return playerDataMap.entrySet().stream()
                .filter(val -> val.getValue().getMessageSpyEnabled())
                .map(Map.Entry::getKey);
    }
}
