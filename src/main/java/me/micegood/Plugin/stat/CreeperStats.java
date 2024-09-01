package me.micegood.Plugin.stat;

import me.micegood.Plugin.Plugin;
import me.micegood.Plugin.hologram.Hologram;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;


public class CreeperStats implements Listener {

    public static final CreeperStats instance = new CreeperStats();

    public Map<UUID, Integer> stat = new HashMap<>();
    public Hologram hologra = new Hologram(
            new Location(Bukkit.getWorld("world"), 25, 73, 32),
            Arrays.asList("Топ 10 Убийств Криперов")
    );

    @EventHandler
    public void onDamage(EntityDamageByBlockEvent event) {
        if (event.getEntity() instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) event.getEntity();

            if(livingEntity.getHealth() <= event.getDamage()) {
                if (event.getDamager() instanceof Player) {
                    Player player = (Player) event.getDamager();

                    if (stat.containsKey(player.getUniqueId())) {
                        stat.put(player.getUniqueId(), stat.get(player.getUniqueId()) + 1);
                    }
                    else {
                        stat.put(player.getUniqueId(), 1);
                    }
                }
            }
        }
    }

    public void startStatUpdate() {
        hologra.createSpinningBlockOnTop(Material.CREEPER_HEAD);
        new BukkitRunnable() {
            @Override
            public void run() {
                List<UUID> top = new ArrayList<>();
                List<String> resultLines = new ArrayList<>();

                resultLines.add("Топ 10 Убийств Криперов");

                for(int i = 0; i < 10; i++) {
                    UUID maxPlayer = null;
                    int maxKills = 0;

                    for(UUID uuid: stat.keySet()) {
                        if(stat.get(uuid) > maxKills && !top.contains(uuid)) {
                            maxPlayer = uuid;
                            maxKills = stat.get(uuid);
                        }
                    }
                    if (maxPlayer != null) {
                        top.add(maxPlayer);

                        resultLines.add(Bukkit.getPlayer(maxPlayer).getName() + " - " + stat.get(maxPlayer));
                    }
                }
                hologra.setText(resultLines);
            }
        }.runTaskTimer(Plugin.instance, 0L, 1200L);
    }

}
