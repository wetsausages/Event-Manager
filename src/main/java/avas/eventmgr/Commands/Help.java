package avas.eventmgr.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Help implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("\n§l§nTournament Information§r\n§6/fight [player]§r - Challenge someone to a fight. If they accept, you will be teleported to an arena.");
        sender.sendMessage("§6/stats§r - Shows your current kill count and remaining lives.");
        sender.sendMessage("§6/kit§r - Modify your kit.");
        sender.sendMessage("\nYou have §45 lives and 24 hours§r to get as many §4kills§r as you can. Top 3 most kills at the end of the tournament gets prizes.");
        sender.sendMessage("Logging out or disconnecting during a fight will result in a loss. Fight logs are stored and winners will be vetted for alt farming.");
        return true;
    }
}
