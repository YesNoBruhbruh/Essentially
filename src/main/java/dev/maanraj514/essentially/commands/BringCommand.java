package dev.maanraj514.essentially.commands;

import dev.maanraj514.essentially.utils.Color;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BringCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) return true;
        Player p = (Player)sender;
        if (!p.hasPermission("Essentially.bring888888")) return true;

        if (args.length == 0) {
            p.sendMessage(Color.colorize("&c/bring player"));
            return true;
        }
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            p.sendMessage(Color.colorize("&cPlayer must exist"));
            return true;
        }
        Location pLoc = p.getLocation();
        p.sendMessage(Color.colorize("&aBringing " + "&d" + target.getName() + " &aTo you"));
        target.teleport(pLoc);
        return true;
    }
}
