package avas.eventmgr.Managers;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class KitManager {

    static Inventory inv = Bukkit.createInventory(null, 18, "Kit - (use /help)");
    static ItemStack[] items = {
            new ItemStack(Material.NETHERITE_SWORD, 1),         //0
            new ItemStack(Material.NETHERITE_HELMET, 1),        //1
            new ItemStack(Material.NETHERITE_CHESTPLATE, 1),    //2
            new ItemStack(Material.NETHERITE_LEGGINGS, 1),      //3
            new ItemStack(Material.NETHERITE_BOOTS, 1),         //4
            new ItemStack(Material.NETHERITE_PICKAXE, 1),       //5
            new ItemStack(Material.BOW, 1),                     //6

            new ItemStack(Material.END_CRYSTAL, 64),            //7
            new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 64), //8
            new ItemStack(Material.TOTEM_OF_UNDYING, 1),        //9
            new ItemStack(Material.ENDER_PEARL, 16),            //10
            new ItemStack(Material.EXPERIENCE_BOTTLE, 64),      //11
            new ItemStack(Material.OBSIDIAN, 64),               //12
            new ItemStack(Material.ENDER_CHEST, 64),            //13
            new ItemStack(Material.CHORUS_FRUIT, 64),           //14
            new ItemStack(Material.ARROW, 64),                  //15
            new ItemStack(Material.AIR, 64)                  //16
    };

    static ItemStack[] defKit = {
            new ItemStack(Material.NETHERITE_SWORD, 1),
            new ItemStack(Material.NETHERITE_PICKAXE, 1),
            new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 64),
            new ItemStack(Material.OBSIDIAN, 64),
            new ItemStack(Material.ENDER_CHEST, 64),
            new ItemStack(Material.BOW, 1),
            new ItemStack(Material.CHORUS_FRUIT, 64),
            new ItemStack(Material.EXPERIENCE_BOTTLE, 64),
            new ItemStack(Material.END_CRYSTAL, 64),

            new ItemStack(Material.EXPERIENCE_BOTTLE, 64),
            new ItemStack(Material.EXPERIENCE_BOTTLE, 64),
            new ItemStack(Material.EXPERIENCE_BOTTLE, 64),
            new ItemStack(Material.EXPERIENCE_BOTTLE, 64),
            new ItemStack(Material.EXPERIENCE_BOTTLE, 64),
            new ItemStack(Material.END_CRYSTAL, 64),
            new ItemStack(Material.END_CRYSTAL, 64),
            new ItemStack(Material.END_CRYSTAL, 64),
            new ItemStack(Material.END_CRYSTAL, 64),

            new ItemStack(Material.EXPERIENCE_BOTTLE, 64),
            new ItemStack(Material.EXPERIENCE_BOTTLE, 64),
            new ItemStack(Material.EXPERIENCE_BOTTLE, 64),
            new ItemStack(Material.EXPERIENCE_BOTTLE, 64),
            new ItemStack(Material.END_CRYSTAL, 64),
            new ItemStack(Material.END_CRYSTAL, 64),
            new ItemStack(Material.END_CRYSTAL, 64),
            new ItemStack(Material.END_CRYSTAL, 64),
            new ItemStack(Material.END_CRYSTAL, 64),

            new ItemStack(Material.ARROW, 64),
            new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 64),
            new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 64),
            new ItemStack(Material.TOTEM_OF_UNDYING, 1),
            new ItemStack(Material.TOTEM_OF_UNDYING, 1),
            new ItemStack(Material.TOTEM_OF_UNDYING, 1),
            new ItemStack(Material.TOTEM_OF_UNDYING, 1),
            new ItemStack(Material.TOTEM_OF_UNDYING, 1),
            new ItemStack(Material.TOTEM_OF_UNDYING, 1)};

    public static ItemStack[] GetItems() {
        ItemStack[] enchantedItems = new ItemStack[17];

        ItemStack swordItem = items[0];
        ItemMeta swordMeta = swordItem.getItemMeta();
        swordMeta.addEnchant(Enchantment.MENDING, 1, true);
        swordMeta.addEnchant(Enchantment.DAMAGE_ALL, 5, true);
        swordMeta.addEnchant(Enchantment.DURABILITY,3 , true);
        swordItem.setItemMeta(swordMeta);
        enchantedItems[0] = swordItem;

        ItemStack helmetItem = items[1];
        ItemMeta helmetMeta = helmetItem.getItemMeta();
        helmetMeta.addEnchant(Enchantment.MENDING, 1, true);
        helmetMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
        helmetMeta.addEnchant(Enchantment.DURABILITY,3 , true);
        helmetItem.setItemMeta(helmetMeta);
        enchantedItems[1] = helmetItem;

        ItemStack chestItem = items[2];
        ItemMeta chestMeta = chestItem.getItemMeta();
        chestMeta.addEnchant(Enchantment.MENDING, 1, true);
        chestMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
        chestMeta.addEnchant(Enchantment.DURABILITY,3 , true);
        chestItem.setItemMeta(chestMeta);
        enchantedItems[2] = chestItem;

        ItemStack legsItem = items[3];
        ItemMeta legsMeta = legsItem.getItemMeta();
        legsMeta.addEnchant(Enchantment.MENDING, 1, true);
        legsMeta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 4, true);
        legsMeta.addEnchant(Enchantment.DURABILITY,3 , true);
        legsItem.setItemMeta(legsMeta);
        enchantedItems[3] = legsItem;

        ItemStack bootsItem = items[4];
        ItemMeta bootsMeta = bootsItem.getItemMeta();
        bootsMeta.addEnchant(Enchantment.MENDING, 1, true);
        bootsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
        bootsMeta.addEnchant(Enchantment.DURABILITY,3 , true);
        bootsItem.setItemMeta(bootsMeta);
        enchantedItems[4] = bootsItem;

        ItemStack pickItem = items[5];
        ItemMeta pickMeta = pickItem.getItemMeta();
        pickMeta.addEnchant(Enchantment.MENDING, 1, true);
        pickMeta.addEnchant(Enchantment.DIG_SPEED, 5, true);
        pickMeta.addEnchant(Enchantment.DURABILITY,3 , true);
        pickItem.setItemMeta(pickMeta);
        enchantedItems[5] = pickItem;

        ItemStack bowItem = items[6];
        ItemMeta bowMeta = bowItem.getItemMeta();
        bowMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
        bowMeta.addEnchant(Enchantment.DURABILITY,3 , true);
        bowItem.setItemMeta(bowMeta);
        enchantedItems[6] = bowItem;

        for(int i = 7; i < 17; i++) {
            enchantedItems[i] = items[i];
        }

        return enchantedItems;
    }

    public static void OpenKitScreen(Player sender) {
        ConfigurationSection senderData = PlayerDataManager.getPlayer(sender.getUniqueId());
        senderData.set("pvp.ui", 2);
        PlayerDataManager.save();

        ItemStack defPaneItem = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
        ItemMeta defPaneMeta = defPaneItem.getItemMeta();
        defPaneMeta.setDisplayName("§r§lDefault Kit");
        defPaneItem.setItemMeta(defPaneMeta);

        ItemStack resPaneItem = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta resPaneMeta = resPaneItem.getItemMeta();
        resPaneMeta.setDisplayName("§r§lUndo changes");
        resPaneItem.setItemMeta(resPaneMeta);

        ItemStack[] enchantedItems = GetItems();
        inv.setItem(0, defPaneItem);
        for(int i = 1; i < 8; i++) inv.setItem(i, enchantedItems[i-1]);
        inv.setItem(8, resPaneItem);
        for(int i = 9; i < 18; i++) inv.setItem(i, enchantedItems[i-2]);
        sender.openInventory(inv);
    }

    public static void TakeItem(Player sender, int slot) {
        if(slot < 8) sender.getInventory().addItem(items[slot-1]);
        else sender.getInventory().addItem(items[slot-2]);
    }

    public static void RemoveItem(Player sender, int slot) {
        if(slot < 45) sender.getInventory().setItem(slot-9, new ItemStack(Material.AIR)); //backpack
        else sender.getInventory().setItem(slot-45, new ItemStack(Material.AIR)); //hotbar
    }

    public static void SaveKit(Player player) {
        ItemStack[] invenItems = player.getInventory().getContents();
        ItemStack[] kitItems = GetItems();
        String kitString = "";

        for (int i = 0; i < 36; i++) {
            if(invenItems[i] == null) {
                kitString += 16 + ",";
                continue;
            }
            for (int k = 0; k < 16; k++) {
                if(invenItems[i].getType() == kitItems[k].getType()) {
                    kitString += k + ",";
                }
            }
        }

        ConfigurationSection playerData = PlayerDataManager.getPlayer(player.getUniqueId());
        playerData.set("pvp.kit", kitString);
    }

    public static void LoadKit(Player player) {
        ConfigurationSection playerData = PlayerDataManager.getPlayer(player.getUniqueId());
        String[] ids = playerData.getString("pvp.kit").split(",");
        ItemStack[] kitItems = GetItems();
        for(int i = 0; i < 36; i++) {
            player.getInventory().setItem(i, kitItems[Integer.parseInt(ids[i])]);
        }
    }

    public static void ClearKit(Player sender) {
        for(int  i = 0; i < 36; i++) {
            sender.getInventory().setItem(i, new ItemStack(Material.AIR));
        }
    }

    public static void DefaultKit(Player sender) {
        //Enchant default kit items
        ItemStack sword = defKit[0];
        ItemMeta swordMeta = sword.getItemMeta();
        swordMeta.addEnchant(Enchantment.MENDING, 1, true);
        swordMeta.addEnchant(Enchantment.DAMAGE_ALL, 5, true);
        swordMeta.addEnchant(Enchantment.DURABILITY,3 , true);
        sword.setItemMeta(swordMeta);
        defKit[0] = sword;

        ItemStack pick = defKit[1];
        ItemMeta pickMeta = pick.getItemMeta();
        pickMeta.addEnchant(Enchantment.MENDING, 1, true);
        pickMeta.addEnchant(Enchantment.DIG_SPEED, 5, true);
        pickMeta.addEnchant(Enchantment.DURABILITY,3 , true);
        pick.setItemMeta(pickMeta);
        defKit[1] = pick;

        ItemStack bow = defKit[5];
        ItemMeta bowMeta = bow.getItemMeta();
        bowMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
        bowMeta.addEnchant(Enchantment.DURABILITY,3 , true);
        bow.setItemMeta(bowMeta);
        defKit[5] = bow;

        for(int i = 0; i < 36; i++) {
            sender.getInventory().setItem(i, defKit[i]);
        }
    }

    public static void Equip(Player sender) {
        
        ItemStack helmetItem = new ItemStack(Material.NETHERITE_HELMET, 1);
        ItemMeta helmetMeta = helmetItem.getItemMeta();
        helmetMeta.addEnchant(Enchantment.MENDING, 1, true);
        helmetMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
        helmetMeta.addEnchant(Enchantment.DURABILITY,3 , true);
        helmetItem.setItemMeta(helmetMeta);

        ItemStack chestItem = new ItemStack(Material.NETHERITE_CHESTPLATE, 1);
        ItemMeta chestMeta = chestItem.getItemMeta();
        chestMeta.addEnchant(Enchantment.MENDING, 1, true);
        chestMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
        chestMeta.addEnchant(Enchantment.DURABILITY,3 , true);
        chestItem.setItemMeta(chestMeta);

        ItemStack legsItem = new ItemStack(Material.NETHERITE_LEGGINGS, 1);
        ItemMeta legsMeta = legsItem.getItemMeta();
        legsMeta.addEnchant(Enchantment.MENDING, 1, true);
        legsMeta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 4, true);
        legsMeta.addEnchant(Enchantment.DURABILITY,3 , true);
        legsItem.setItemMeta(legsMeta);

        ItemStack bootsItem = new ItemStack(Material.NETHERITE_BOOTS, 1);
        ItemMeta bootsMeta = bootsItem.getItemMeta();
        bootsMeta.addEnchant(Enchantment.MENDING, 1, true);
        bootsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
        bootsMeta.addEnchant(Enchantment.DURABILITY,3 , true);
        bootsItem.setItemMeta(bootsMeta);

        sender.getInventory().setHelmet(helmetItem);
        sender.getInventory().setChestplate(chestItem);
        sender.getInventory().setLeggings(legsItem);
        sender.getInventory().setBoots(bootsItem);
    }
}
