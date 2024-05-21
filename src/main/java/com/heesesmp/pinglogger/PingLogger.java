package com.heesesmp.pinglogger;

import com.heesesmp.pinglogger.listener.ServerListPingListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class PingLogger extends JavaPlugin {
    private static PingLogger instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new ServerListPingListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static PingLogger getInstance() {
        return instance;
    }
}
