package avas.eventmgr.Commands;

import avas.eventmgr.Managers.PlayerDataManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;

public class Stats implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ConfigurationSection player = PlayerDataManager.getPlayer(sender.getName());
        sender.sendMessage("\n" + sender.getName() + "'s stats:");
        sender.sendMessage("§2Kills§r: " + player.getInt("pvp.kills"));
        sender.sendMessage("§4Lives§r: " + player.getInt("pvp.lives"));
        return true;
    }
}
