package avas.eventmgr.Handlers;

import avas.eventmgr.Managers.FightManager;
import avas.eventmgr.Managers.KitManager;
import avas.eventmgr.Managers.PlayerDataManager;
import com.destroystokyo.paper.event.player.PlayerPostRespawnEvent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Objects;

public class Players implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player deadPlayer = e.getPlayer();
        ConfigurationSection playerData = PlayerDataManager.getPlayer(e.getPlayer().getUniqueId());
        if(playerData.getInt("pvp.fighting") == 0) return;
        Player killer = Bukkit.getPlayer((playerData.getString("pvp.challenger")));
        FightManager.EndFight(killer, deadPlayer);
    }

    @EventHandler
    public void onPlayerRespawn(PlayerPostRespawnEvent e) {
        Player p = e.getPlayer();
        p.setGameMode(GameMode.SPECTATOR);
        KitManager.LoadKit(p);
        KitManager.Equip(p);
        p.teleport(new Location(Bukkit.getWorld("world"), 0, 120, 0, 0, 0));
    }
}
