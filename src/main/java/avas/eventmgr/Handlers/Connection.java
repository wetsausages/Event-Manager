package avas.eventmgr.Handlers;

import avas.eventmgr.Managers.FightManager;
import avas.eventmgr.Managers.PlayerDataManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
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

        p.setGameMode(GameMode.SPECTATOR);
        p.teleport(new Location(Bukkit.getWorld("world"), -0, 120, 0, 0, 0));
        p.sendRawMessage("§6Welcome to the AVAS Events server.\nType /help for more information on how this works.");
    }
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        FightManager.DisconnectHandler(e.getPlayer());
    }
}
