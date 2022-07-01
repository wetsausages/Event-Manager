package avas.eventmgr.Managers;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static org.bukkit.Bukkit.getServer;

public class FightManager {
    public static boolean[] cells = {false, false, false, false, false, false, false, false, false};
    public static String[][] fighters = {{"",""},{"",""},{"",""},{"",""},{"",""},{"",""},{"",""},{"",""},{"",""}};

    public static void CancelFight(Player sender) {
        ConfigurationSection senderData = PlayerDataManager.getPlayer(sender.getUniqueId());
        Player recipient = Bukkit.getPlayer(senderData.getString("pvp.challenger"));
        ConfigurationSection recipientData = PlayerDataManager.getPlayer(recipient.getUniqueId());
        if(recipientData.getInt("pvp.ui") > 0) recipient.closeInventory();

        senderData.set("pvp.challenger", "");
        recipientData.set("pvp.ui", 0);
        recipientData.set("pvp.challenger", "");

        sender.sendMessage("You have canceled your fight request to " + recipient.getName());
        recipient.sendMessage(sender.getName() + " has canceled their fight request.");
    }

    public static void RequestFight(Player sender, Player recipient) {
        ConfigurationSection senderData = PlayerDataManager.getPlayer(sender.getUniqueId());
        ConfigurationSection recipientData = PlayerDataManager.getPlayer(recipient.getUniqueId());
        if(recipientData.getInt("pvp.ui") > 0) {
            sender.sendMessage(recipient.getName() + " is currently busy.");
            return;
        }

        recipientData.set("pvp.ui", 1);
        recipientData.set("pvp.challenger", sender.getName());
        senderData.set("pvp.challenger", recipient.getName());
        PlayerDataManager.save();

        //Logging
        LogManager.PrintLn(sender.getName(), "TO    " + recipient.getName());
        LogManager.PrintLn(recipient.getName(), "FROM  " + sender.getName());

        //Send request
        sender.sendRawMessage("You have challenged §4" + recipient.getName() + "§r to a fight!");
        recipient.sendRawMessage("§4"+ sender.getName() + "§r has challenged you to a fight!");

        Inventory requestWindow = Bukkit.createInventory(null, 9, sender.getName() + " wants to fight!");
        ItemStack[] items = {
                new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1),
                new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1),
                new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1),
                new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1),
                new ItemStack(Material.PLAYER_HEAD, 1),
                new ItemStack(Material.RED_STAINED_GLASS_PANE, 1),
                new ItemStack(Material.RED_STAINED_GLASS_PANE, 1),
                new ItemStack(Material.RED_STAINED_GLASS_PANE, 1),
                new ItemStack(Material.RED_STAINED_GLASS_PANE, 1)};
        for(int i = 0; i < 9; i++) requestWindow.setItem(i, items[i]);
        recipient.openInventory(requestWindow);
    }

    public static void DeclineFight(Player sender, Player recipient) {
        ConfigurationSection senderData = PlayerDataManager.getPlayer(sender.getUniqueId());
        ConfigurationSection recipientData = PlayerDataManager.getPlayer(recipient.getUniqueId());

        sender.closeInventory();
        recipientData.set("pvp.challenger", "");
        senderData.set("pvp.challenger", "");
        PlayerDataManager.save();

        sender.sendMessage("You have declined the fight request from " + recipient.getName());
        recipient.sendMessage(sender.getName() + " has declined your fight request.");

    }

    public static void StartFight(Player sender, Player recipient) {
        //Logging
        LogManager.PrintLn(sender.getName(), "FIGHT " + recipient.getName() + " (" + sender.getAddress().getHostName() + " vs " + recipient.getAddress().getHostName() + ")");
        LogManager.PrintLn(recipient.getName(), "FIGHT " + sender.getName() + " (" + recipient.getAddress().getHostName() + " vs " + sender.getAddress().getHostName() + ")");

        //Find arena
        for(int i = 0; i < 8;i++) {
            if(cells[i]) continue;
            cells[i] = true;
            fighters[i][0] = sender.getName();
            fighters[i][1] = recipient.getName();
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.sendMessage(sender.getName() + " and " + recipient.getName() + " are about to fight in arena " + (i+1) + "!");
            }
            break;

            //Teleport players
        }
        //Prepare

        sender.sendMessage("§4You are about to fight " + recipient.getName() + "!");
        recipient.sendMessage("§4You are about to fight " + sender.getName() + "!");

        sender.setGameMode(GameMode.SURVIVAL);
        recipient.setGameMode(GameMode.SURVIVAL);

        sender.setHealth(20);
        recipient.setHealth(20);
        sender.setSaturation(20);
        recipient.setSaturation(20);

        KitManager.Equip(sender);
        KitManager.Equip(recipient);

        ConfigurationSection p1 = PlayerDataManager.getPlayer((sender.getName()));
        ConfigurationSection p2 = PlayerDataManager.getPlayer((recipient.getName()));

        //Start recordings
        getServer().dispatchCommand(getServer().getConsoleSender(), "replay start " + sender.getName() + String.valueOf(p1.getInt("pvp.kills") + (5 - p1.getInt("pvp.lives"))) + " " + sender.getName() + " " + recipient.getName());
        getServer().dispatchCommand(getServer().getConsoleSender(), "replay start " + recipient.getName() + String.valueOf(p1.getInt("pvp.kills") + (5 - p1.getInt("pvp.lives"))) + " " + recipient.getName() + " " + sender.getName());

        //Set 'fighting' flag
        p1.set("pvp.fighting", 1);
        p2.set("pvp.fighting", 1);
        PlayerDataManager.save();
        sender.closeInventory();
        recipient.closeInventory();
    }

    public static void EndFight(Player winner, Player loser) {
        //Logging
        LogManager.PrintLn(winner.getName(), "WIN   " + loser.getName());
        LogManager.PrintLn(loser.getName(), "LOSE  " + winner.getName());

        ConfigurationSection winnerData = PlayerDataManager.getPlayer(winner.getUniqueId());
        ConfigurationSection loserData = PlayerDataManager.getPlayer(loser.getUniqueId());

        getServer().dispatchCommand(getServer().getConsoleSender(), "replay stop " + winner.getName() + String.valueOf(winnerData.getInt("pvp.kills") + (5 - winnerData.getInt("pvp.lives"))));
        getServer().dispatchCommand(getServer().getConsoleSender(), "replay stop " + loser.getName() + String.valueOf(loserData.getInt("pvp.kills") + (5 - loserData.getInt("pvp.lives"))));

        KitManager.LoadKit(winner);
        KitManager.Equip(winner);

        winnerData.set("pvp.challenger", "");
        loserData.set("pvp.challenger", "");
        winnerData.set("pvp.fighting", 0);
        loserData.set("pvp.fighting", 0);
        int currentKills = winnerData.getInt("pvp.kills");
        winnerData.set("pvp.kills", currentKills+1);
        int currentLives = loserData.getInt("pvp.lives");
        loserData.set("pvp.lives", currentLives-1);
        PlayerDataManager.save();

        winner.setGameMode(GameMode.SPECTATOR);
        winner.teleport(new Location(Bukkit.getWorld("world"), 0, 120, 0, 0, 0));
        winner.sendMessage("You have defeated " + loser.getName() + "!");
        KitManager.Equip(winner);
        loser.sendMessage(winner.getName() + " has defeated you!");

        ResetArena(winner);
    }

    public static void ResetArena(Player p1) {
        for(int i = 0; i < 8;i++) {
            if(fighters[i][0] == p1.getName() || fighters[i][1] == p1.getName()) {
                cells[i] = false;
                fighters[i][0] = "";
                fighters[i][1] = "";
            }
        }
    }

    public static void DisconnectHandler(Player quitter) {
        Player winner;
        for(int i = 0; i < 8;i++) {
            if(fighters[i][0] == quitter.getName()) {
                winner = Bukkit.getPlayer(fighters[i][1]);
                EndFight(winner, quitter);
            }
            else if(fighters[i][1] == quitter.getName()) {
                winner = Bukkit.getPlayer(fighters[i][0]);
                EndFight(winner, quitter);
            }
        }
    }
}
