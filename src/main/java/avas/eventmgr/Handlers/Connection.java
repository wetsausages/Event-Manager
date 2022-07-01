package avas.eventmgr.Handlers;

import avas.eventmgr.Managers.FightManager;
import avas.eventmgr.Managers.KitManager;
import avas.eventmgr.Managers.LogManager;
import avas.eventmgr.Managers.PlayerDataManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Connection implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        PlayerDataManager.createPlayer(p.getUniqueId());

        if(!p.isOp()) {
            p.setGameMode(GameMode.SPECTATOR);
            p.teleport(new Location(Bukkit.getWorld("world"), -0, 120, 0, 0, 0));
            KitManager.LoadKit(p);
            KitManager.Equip(p);
        }

        p.sendRawMessage("§6Welcome to the AVAS Events server.\nType /help for more information on how this works.");

        LogManager.PrintLn(p.getName(), "JOIN");
    }
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        ConfigurationSection playerData = PlayerDataManager.getPlayer(p.getUniqueId());
        if(playerData.getInt("pvp.fighting") == 1) FightManager.DisconnectHandler(p);
        if(!playerData.getString("pvp.challenger").equals("")) {
            Player c = Bukkit.getPlayer(playerData.getString("pvp.challenger"));
            ConfigurationSection challengerData = PlayerDataManager.getPlayer(c.getUniqueId());
            if(challengerData.getInt("pvp.ui") == 1) c.closeInventory();
            challengerData.set("pvp.challenger", "");
        }
        else if(playerData.getInt("pvp.ui") == 2) {
            KitManager.SaveKit(p);
        }

        playerData.set("pvp.ui", 0);
        playerData.set("pvp.challenger", "");

        PlayerDataManager.save();
        LogManager.PrintLn(p.getName(), "QUIT");
    }
}
