package avas.eventmgr.Managers;

import avas.eventmgr.EventManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.UUID;

public class PlayerDataManager {
    public static final File FILE = new File(EventManager.INSTANCE.getDataFolder(), "players.yml");
    public static FileConfiguration CONFIG;

    public static void createPlayer(UUID uuid) {
        ConfigurationSection player = getPlayer(uuid);
        if(player.contains("username")) return;
        player.set("username", Bukkit.getPlayer(uuid).getName());
        player.set("pvp.kills", 0);
        player.set("pvp.lives", 5);
        player.set("pvp.fighting", 0);
        player.set("pvp.ui", 0);
        player.set("pvp.challenger", "");
        player.set("pvp.kit", "0,5,8,12,13,6,14,11,7,11,11,11,11,11,7,7,7,7,11,11,11,11,7,7,7,7,7,15,8,8,9,9,9,9,9,9");

        save();
    }

    public static ConfigurationSection getPlayer(UUID uuid) {
        ConfigurationSection section = CONFIG.getConfigurationSection(uuid.toString());
        if (section == null) CONFIG.createSection(uuid.toString());

        return CONFIG.getConfigurationSection(uuid.toString());
    }

    public static ConfigurationSection getPlayer(String username) {
        if(Bukkit.getServer().getOnlinePlayers().contains(username)) {
            return getPlayer(Bukkit.getPlayer(username).getUniqueId());
        }
        return getPlayer(Bukkit.getOfflinePlayer(username).getUniqueId());
    }
    public static void load() {
        if (!FILE.exists()) {
            try {
                FILE.createNewFile();
                CONFIG = YamlConfiguration.loadConfiguration(FILE);
                CONFIG.load(new InputStreamReader(PlayerDataManager.class.getClassLoader().getResourceAsStream("players.yml")));
            }
            catch (IOException | InvalidConfigurationException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                CONFIG = YamlConfiguration.loadConfiguration(FILE);
            }
            catch(Exception e) {
                Bukkit.getLogger().info("&cAn error occurred loading player data.");
                return;
            }
        }

        save();
    }

    public static void save() {
        try {
            CONFIG.save(FILE);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
