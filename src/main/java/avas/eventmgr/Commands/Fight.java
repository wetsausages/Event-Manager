package avas.eventmgr.Commands;

import avas.eventmgr.Managers.FightManager;
import avas.eventmgr.Managers.PlayerDataManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.Objects;

public class Fight implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //Validation
        if(args.length < 1) {
            sender.sendMessage("Invalid syntax: /fight [player]");
            return true;
        }
        if(sender == Bukkit.getPlayer(args[0])) {
            sender.sendMessage("Stop hitting yourself.");
            return true;
        }
        if(sender instanceof ConsoleCommandSender) {
            Bukkit.getLogger().info("Command unavailable from console.");
            return true;
        }

        boolean online = false;
        for (Player player : Bukkit.getOnlinePlayers()) {
            if(player == Bukkit.getPlayer(args[0])) online = true;
        }
        if(!online) {
            sender.sendMessage(args[0] + " is not online.");
            return true;
        }

        ConfigurationSection senderData = PlayerDataManager.getPlayer(Bukkit.getPlayer(sender.getName()).getUniqueId());
        if(senderData.getInt("pvp.lives") < 1) {
            sender.sendMessage("You don't have any remaining lives!");
            return true;
        }
        if(senderData.getInt("pvp.fighting") == 1) {
            sender.sendMessage("You're already in a fight!");
            return true;
        }
        ConfigurationSection recipientData = PlayerDataManager.getPlayer(Bukkit.getPlayer(Bukkit.getPlayer(args[0]).getName()).getUniqueId());
        if(recipientData.getInt("pvp.lives") < 1) {
            sender.sendMessage(args[0] + " doesn't have any remaining lives!");
            return true;
        }
        if(recipientData.getInt("pvp.fighting") == 1) {
            sender.sendMessage(args[0] + " is already in a fight!");
            return true;
        }

        //Run it
        FightManager.RequestFight(Objects.requireNonNull(Bukkit.getPlayer(sender.getName())), Objects.requireNonNull(Bukkit.getPlayer(args[0])));
        return true;
    }
}
