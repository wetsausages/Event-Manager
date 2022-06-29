package avas.eventmgr.Managers;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class KitManager {

    static Inventory inv = Bukkit.createInventory(null, 18, "Kit - (use /kit help)");
    static ItemStack[] items = {
            new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1),
            new ItemStack(Material.NETHERITE_SWORD, 1),
            new ItemStack(Material.NETHERITE_HELMET, 1),
            new ItemStack(Material.NETHERITE_CHESTPLATE, 1),
            new ItemStack(Material.NETHERITE_LEGGINGS, 1),
            new ItemStack(Material.NETHERITE_BOOTS, 1),
            new ItemStack(Material.NETHERITE_PICKAXE, 1),
            new ItemStack(Material.BOW, 1),
            new ItemStack(Material.RED_STAINED_GLASS_PANE, 1),

            new ItemStack(Material.TOTEM_OF_UNDYING, 1),
            new ItemStack(Material.END_CRYSTAL, 64),
            new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 64),
            new ItemStack(Material.EXPERIENCE_BOTTLE, 64),
            new ItemStack(Material.ENDER_PEARL, 16),
            new ItemStack(Material.ENDER_CHEST, 64),
            new ItemStack(Material.OBSIDIAN, 64),
            new ItemStack(Material.CHORUS_FRUIT, 64),
            new ItemStack(Material.ARROW, 64)
    };

    static ItemStack[] defKit = {
            new ItemStack(Material.NETHERITE_SWORD, 1),
            new ItemStack(Material.OBSIDIAN, 64),
            new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 64),
            new ItemStack(Material.ENDER_PEARL, 16),
            new ItemStack(Material.CHORUS_FRUIT, 64),
            new ItemStack(Material.END_CRYSTAL, 64),
            new ItemStack(Material.TOTEM_OF_UNDYING, 1),
            new ItemStack(Material.BOW, 1),
            new ItemStack(Material.ARROW, 64),

            new ItemStack(Material.NETHERITE_HELMET, 1),
            new ItemStack(Material.NETHERITE_CHESTPLATE, 1),
            new ItemStack(Material.NETHERITE_LEGGINGS, 1),
            new ItemStack(Material.NETHERITE_BOOTS, 1),
            new ItemStack(Material.NETHERITE_PICKAXE, 1),
            new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 64),
            new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 64),
            new ItemStack(Material.END_CRYSTAL, 64),
            new ItemStack(Material.END_CRYSTAL, 64),

            new ItemStack(Material.TOTEM_OF_UNDYING, 1),
            new ItemStack(Material.TOTEM_OF_UNDYING, 1),
            new ItemStack(Material.TOTEM_OF_UNDYING, 1),
            new ItemStack(Material.TOTEM_OF_UNDYING, 1),
            new ItemStack(Material.ENDER_CHEST, 64),
            new ItemStack(Material.EXPERIENCE_BOTTLE, 64),
            new ItemStack(Material.EXPERIENCE_BOTTLE, 64),
            new ItemStack(Material.END_CRYSTAL, 64),
            new ItemStack(Material.END_CRYSTAL, 64),

            new ItemStack(Material.TOTEM_OF_UNDYING, 1),
            new ItemStack(Material.TOTEM_OF_UNDYING, 1),
            new ItemStack(Material.TOTEM_OF_UNDYING, 1),
            new ItemStack(Material.TOTEM_OF_UNDYING, 1),
            new ItemStack(Material.NETHERITE_SWORD, 1),
            new ItemStack(Material.CHORUS_FRUIT, 64),
            new ItemStack(Material.ENDER_PEARL, 16),
            new ItemStack(Material.END_CRYSTAL, 64),
            new ItemStack(Material.END_CRYSTAL, 64)};

    public static void OpenKitScreen(Player sender) {
        for(int i = 0; i < 18; i++) inv.setItem(i, items[i]);
        sender.openInventory(inv);
    }

    public static void TakeItem(Player sender, int slot) {
        sender.getInventory().addItem(items[slot]);
    }

    public static void RemoveItem(Player sender, int slot) {
        if(slot < 45) sender.getInventory().setItem(slot-9, new ItemStack(Material.AIR)); //backpack
        else sender.getInventory().setItem(slot-45, new ItemStack(Material.AIR)); //hotbar
    }

    public static void DefaultKit(Player sender) {
        for(int i = 0; i < 36; i++) sender.getInventory().setItem(i, defKit[i]);
    }

    public static void ClearKit(Player sender) {
        for(int  i = 0; i < 36; i++) {
            sender.getInventory().setItem(i, new ItemStack(Material.AIR));
        }
    }

    public static void Equip(Player sender) {
        sender.getInventory().setHelmet(new ItemStack(Material.NETHERITE_HELMET, 1));
        sender.getInventory().setChestplate(new ItemStack(Material.NETHERITE_CHESTPLATE, 1));
        sender.getInventory().setLeggings(new ItemStack(Material.NETHERITE_LEGGINGS, 1));
        sender.getInventory().setBoots(new ItemStack(Material.NETHERITE_BOOTS, 1));
    }
}
