package avas.eventmgr.Handlers;

import avas.eventmgr.Managers.FightManager;
import com.destroystokyo.paper.event.player.PlayerPostRespawnEvent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class Players implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player deadPlayer = e.getPlayer();
        try {
            Player killer = deadPlayer.getKiller();
            FightManager.EndFight(killer, deadPlayer);
        } catch(Exception ex) {
            return;
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerPostRespawnEvent e) {
        Player p = e.getPlayer();
        p.setGameMode(GameMode.SPECTATOR);
        p.teleport(new Location(Bukkit.getWorld("world"), -0, 120, 0, 0, 0));
    }
}
