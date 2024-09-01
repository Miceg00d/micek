package me.micegood.Plugin;

import me.micegood.Plugin.command.HologramCMD;
import me.micegood.Plugin.stat.CreeperStats;
import org.bukkit.plugin.java.JavaPlugin;

public final class Plugin extends JavaPlugin {

    public static Plugin instance;

    @Override
    public void onEnable() {
        instance = this;

        getCommand("spawnHologram").setExecutor(new HologramCMD());

        CreeperStats.instance.startStatUpdate();
        getServer().getPluginManager().registerEvents(CreeperStats.instance, this);
    }
        // Plugin startup logi\
    @Override
    public void onDisable() {


        // Plugin shutdown logic
    }
}
