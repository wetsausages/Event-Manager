package avas.eventmgr.Commands;

import avas.eventmgr.Managers.KitManager;
import avas.eventmgr.Managers.LogManager;
import avas.eventmgr.Managers.PlayerDataManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class Kit implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ConfigurationSection playerData = PlayerDataManager.getPlayer(Bukkit.getPlayer(sender.getName()).getUniqueId());
        if (playerData.getInt("pvp.fighting") == 1) {
            sender.sendMessage("Regear after the fight.");
            return true;
        }

        KitManager.OpenKitScreen(Bukkit.getPlayer(sender.getName()));
        return true;
    }
}
