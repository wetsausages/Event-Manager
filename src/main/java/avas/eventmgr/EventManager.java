package avas.eventmgr;

import avas.eventmgr.Commands.Fight;
import avas.eventmgr.Commands.Help;
import avas.eventmgr.Commands.StartFight;
import avas.eventmgr.Commands.Stats;
import avas.eventmgr.Handlers.Connection;
import avas.eventmgr.Handlers.Players;
import avas.eventmgr.Managers.LogManager;
import avas.eventmgr.Managers.PlayerDataManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

//TODO: Kits and tp coords when map is made

public final class EventManager extends JavaPlugin implements Listener {

    public static EventManager INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;

        if(!getDataFolder().exists()) {
            File dataFolders = new File(getDataFolder() + "/logs");
            dataFolders.mkdirs();
        }

        LogManager.logsDir = new File(getDataFolder() + "/logs");
        PlayerDataManager.load();

        loadEvents(new Connection(), new Players());

        loadCommand("help", new Help());
        loadCommand("stats", new Stats());
        loadCommand("fight", new Fight());
        loadCommand("startfight", new StartFight());
    }

    private void loadEvents(Listener... listeners) {
        for (Listener listener : listeners) getServer().getPluginManager().registerEvents(listener, this);
    }

    private void loadCommand(String commandString, CommandExecutor executor) {
        PluginCommand command = getCommand(commandString);
        if (command == null) return;
        command.setExecutor(executor);
    }
}