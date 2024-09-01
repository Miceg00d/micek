package me.micegood.Plugin.command;

import me.micegood.Plugin.hologram.Hologram;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class HologramCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        new Hologram(
                player.getLocation(),
                Arrays.asList(
                        "&9Братья как дела",
                        "&9Таганка",
                        " ",
                        "Швалюги")
        );
        return false;
    }
}
