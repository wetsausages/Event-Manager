package avas.eventmgr.Handlers;

import avas.eventmgr.Managers.KitManager;
import avas.eventmgr.Managers.PlayerDataManager;
import avas.eventmgr.Managers.UIManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

public class Inventory implements Listener {

    @EventHandler
    public static void onInventoryClick(InventoryClickEvent e) {
        ItemStack clickedItem = e.getCurrentItem();
        Player p = (Player) e.getWhoClicked();
        if (clickedItem == null || clickedItem.getType().isAir()) return;

        ConfigurationSection playerData = PlayerDataManager.getPlayer(p.getUniqueId());
        if(playerData.getInt("pvp.ui") == 1) UIManager.FightRequest(e, p);
        if(playerData.getInt("pvp.ui") == 2) UIManager.Kit(e, p);
    }

    @EventHandler
    public static void onInventoryClose(InventoryCloseEvent e) {
        Player p = Bukkit.getPlayer(e.getPlayer().getUniqueId());
        ConfigurationSection playerData = PlayerDataManager.getPlayer(p.getUniqueId());
        if(playerData.getInt("pvp.fighting") == 1) return;
        if(playerData.getInt("pvp.ui") == 1) {
            ConfigurationSection challengerData = PlayerDataManager.getPlayer(playerData.getString("pvp.challenger"));
            playerData.set("pvp.challenger", "");
            challengerData.set("pvp.challenger", "");
        } else if (playerData.getInt("pvp.ui") == 2) {
            KitManager.SaveKit(p);
        }
        playerData.set("pvp.ui", 0);
        PlayerDataManager.save();
    }
}