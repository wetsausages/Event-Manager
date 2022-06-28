package avas.eventmgr.Managers;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class FightManager {
    public static boolean[] cells = {false, false, false, false, false, false, false, false, false};
    public static String[][] fighters = {{"",""},{"",""},{"",""},{"",""},{"",""},{"",""},{"",""},{"",""},{"",""}};

    public static void RequestFight(Player sender, Player recipient) {
        //Logging
        LogManager.PrintLn(sender.getName(), "TO    " + recipient.getName());
        LogManager.PrintLn(recipient.getName(), "FROM  " + sender.getName());

        //Send request
        TextComponent accept = new TextComponent("Click §ahere§r to accept");
        accept.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/startfight " + sender.getName() + " " + recipient.getName() + " a b"));
        sender.sendRawMessage("You have challenged §4" + recipient.getName() + "§r to a fight!");
        recipient.sendRawMessage("§4"+ sender.getName() + "§r has challenged you to a fight!");
        recipient.sendMessage(accept);
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

        //Set 'fighting' flag
        ConfigurationSection p1 = PlayerDataManager.getPlayer((sender.getName()));
        ConfigurationSection p2 = PlayerDataManager.getPlayer((recipient.getName()));
        p1.set("pvp.fighting", 1);
        p2.set("pvp.fighting", 1);
        PlayerDataManager.save();
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

    public static void EndFight(Player winner, Player loser) {
        //Logging
        LogManager.PrintLn(winner.getName(), "WIN   " + loser.getName());
        LogManager.PrintLn(loser.getName(), "LOSE  " + winner.getName());

        ConfigurationSection winnerData = PlayerDataManager.getPlayer(winner.getUniqueId());
        ConfigurationSection loserData = PlayerDataManager.getPlayer(loser.getUniqueId());
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
}
