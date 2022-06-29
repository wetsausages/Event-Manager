package avas.eventmgr.Commands;

import avas.eventmgr.Managers.FightManager;
import avas.eventmgr.Managers.PlayerDataManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;

public class StartFight implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length < 4) {
            sender.sendMessage("You do not have permission.");
            return true;
        }

        ConfigurationSection playerData1 = PlayerDataManager.getPlayer(Bukkit.getPlayer(args[1]).getUniqueId());
        if (playerData1.getInt("pvp.fighting") == 1) {
            sender.sendMessage("This shouldn't happen, so it won't.");
            return true;
        }

        FightManager.StartFight(Bukkit.getPlayer(args[0]), Bukkit.getPlayer(args[1]));
        return true;
    }
}
