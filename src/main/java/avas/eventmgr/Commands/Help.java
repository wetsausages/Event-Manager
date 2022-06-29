package avas.eventmgr.Commands;

import avas.eventmgr.Managers.KitManager;
import avas.eventmgr.Managers.LogManager;
import avas.eventmgr.Managers.PlayerDataManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;

public class Help implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ConfigurationSection senderData = PlayerDataManager.getPlayer(Bukkit.getPlayer(sender.getName()).getUniqueId());
        if (senderData.getInt("pvp.fighting") == 1) {
            sender.sendMessage("Nobody can help you now.");
            return true;
        }

        sender.sendMessage("\n§l§nTournament Information§r\n§6/fight [player]§r - Challenge someone to a fight. If they accept, you will be teleported to an arena.");
        sender.sendMessage("§6/stats§r - Shows your current kill count and remaining lives.");
        sender.sendMessage("§6/kit§r - Modify your kit and regear after fights.");
        sender.sendMessage("\nYou have §45 lives and 24 hours§r to get as many §4kills§r as you can. Top 3 most kills at the end of the tournament gets prizes.");
        sender.sendMessage("Logging out or disconnecting during a fight will result in a loss. Fight logs are stored and winners will be vetted for alt farming.");

        sender.sendMessage("\n§l§n/Kit Information§r\n-Click §atop 2 rows to add the item§r to your inventory below.\n-Click the items in your §cinventory and hotbar to delete§r them.\n-The §agreen pane§r restores the default kit. The §cred pane§r §cdeletes§r your kit.\n \nYou get a default kit automatically when you join. Saving kits will come in v2, meaning you have to restock before each fight.");

        return true;
    }
}
