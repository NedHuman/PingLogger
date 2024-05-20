package com.heesesmp.pinglogger.listener;

import com.heesesmp.pinglogger.PingLogger;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

public class ServerListPingListener implements Listener {
    @EventHandler
    public void onPing(ServerListPingEvent event) {
        if(PingLogger.getInstance().getConfig().getBoolean("ping-broadcast")) {
            PingLogger.getInstance().getLogger().info(event.getAddress().getHostAddress() + " pinged the server");
        }
        if(PingLogger.getInstance().getConfig().getBoolean("ping-logs")) {
            LocalDate now = LocalDate.now();
            File file = new File(PingLogger.getInstance().getDataFolder().getAbsolutePath() + "/logs/" + now.getYear() + now.getMonthValue() + now.getDayOfMonth()+".txt");
            Bukkit.getScheduler().runTaskAsynchronously(PingLogger.getInstance(), () -> {
                try {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
                    writer.write(event.getAddress().getHostAddress() + ":" + new Date());
                    writer.newLine();
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
