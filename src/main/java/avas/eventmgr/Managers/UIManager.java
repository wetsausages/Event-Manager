package avas.eventmgr.Managers;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Objects;

public class UIManager {

    public static void Kit (InventoryClickEvent e, Player p) {
        if (e.getRawSlot() == 0) KitManager.LoadKit(p);
        else if (e.getRawSlot() == 8) KitManager.ClearKit(p);
        else if (e.getRawSlot() < 18) KitManager.TakeItem(p, e.getRawSlot());
        else KitManager.RemoveItem(p, e.getRawSlot());
    }

    public static void FightRequest (InventoryClickEvent e, Player p) {
        ConfigurationSection playerData = PlayerDataManager.getPlayer(p.getUniqueId());
        if (e.getRawSlot() < 4) FightManager.StartFight(p, Objects.requireNonNull(Bukkit.getPlayer(Objects.requireNonNull(playerData.getString("pvp.challenger")))));
        else if (e.getRawSlot() > 4) FightManager.DeclineFight(p,Objects.requireNonNull(Bukkit.getPlayer(Objects.requireNonNull(playerData.getString("pvp.challenger")))));
    }
}
