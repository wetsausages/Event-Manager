package avas.eventmgr.Commands;

import avas.eventmgr.Managers.PlayerDataManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;

public class Stats implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ConfigurationSection playerData = PlayerDataManager.getPlayer(Bukkit.getPlayer(sender.getName()).getUniqueId());
        if (playerData.getInt("pvp.fighting") == 1) {
            sender.sendMessage("Check after the fight.");
            return true;
        }

        sender.sendMessage("\n" + sender.getName() + "'s stats:");
        sender.sendMessage("§2Kills§r: " + playerData.getInt("pvp.kills"));
        sender.sendMessage("§4Lives§r: " + playerData.getInt("pvp.lives"));
        return true;
    }
}
