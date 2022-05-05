package dev.maanraj514.essentially.commands;

import dev.maanraj514.essentially.utils.Color;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PingCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) return true;
        Player p = (Player)sender;
        if (!p.hasPermission("Essentially.ping")) return true;

        if (args.length == 0) {
            p.sendMessage(Color.colorize("&eYour ping is " + "&d" + p.getPing()));
            return true;
        }
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null){
            p.sendMessage(Color.colorize("&Player must exist!"));
            return true;
        }
        if (!target.isOnline()){
            p.sendMessage(Color.colorize("&cThat player is offline"));
            return true;
        }
        p.sendMessage(Color.colorize("&e" + target.getName() + "&e's" + " &aping is " + "&d" + target.getPing()));
        return true;
    }
}
