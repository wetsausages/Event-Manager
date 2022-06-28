package avas.eventmgr.Managers;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class LogManager {
    public static File logsDir;

    public static void PrintLn (String playerName, String content) {
        try {
            File log = new File(logsDir.toString() + "/" + playerName + ".log");
            if(!log.exists()) {
                FileWriter create;
                create = new FileWriter(log);
                create.close();
            }

            String line = "[" + (System.currentTimeMillis()/1000) + "] " + content + "\n";
            Files.write(
                    Paths.get(log.toString()),
                    line.getBytes(),
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
