package avas.eventmgr.Commands;

import avas.eventmgr.Managers.KitManager;
import avas.eventmgr.Managers.LogManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Kit implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        KitManager.OpenKitScreen(Bukkit.getPlayer(sender.getName()));
        return true;
    }
}
