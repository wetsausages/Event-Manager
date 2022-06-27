package avas.eventmgr.Commands;

import avas.eventmgr.Managers.FightManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StartFight implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FightManager.StartFight(Bukkit.getPlayer(args[0]), Bukkit.getPlayer(sender.getName()));
        return true;
    }
}
