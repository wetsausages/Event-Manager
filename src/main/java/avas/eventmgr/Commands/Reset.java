package avas.eventmgr.Commands;

import avas.eventmgr.Managers.PlayerDataManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import sun.security.krb5.Config;

public class Reset implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.isOp()) return true;

        Player p = Bukkit.getPlayer(args[0]);
        ConfigurationSection playerData = PlayerDataManager.getPlayer(p.getUniqueId());
        playerData.set("pvp.fighting", 0);
        playerData.set("pvp.ui", 0);
        playerData.set("pvp.challenger", "");
        PlayerDataManager.save();

        sender.sendMessage(p.getName() + " flags have been reset.");
        return true;
    }
}
