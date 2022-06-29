package avas.eventmgr.Handlers;

import avas.eventmgr.Managers.KitManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class Inventory implements Listener {

    @EventHandler
    public static void onInventoryClick(final InventoryClickEvent e) {
        final ItemStack clickedItem = e.getCurrentItem();
        final Player p = (Player) e.getWhoClicked();
        if (clickedItem == null || clickedItem.getType().isAir()) {
            p.sendMessage("Error: null selection");
            return;
        }

        if (e.getRawSlot() == 0) KitManager.DefaultKit(p);
        else if (e.getRawSlot() == 8) KitManager.ClearKit(p);
        else if (e.getRawSlot() < 18) KitManager.TakeItem(p, e.getRawSlot());
        else KitManager.RemoveItem(p, e.getRawSlot());
        p.sendMessage("You clicked at slot " + e.getRawSlot());
    }
}
