package dev.maanraj514.essentially.commands;

import dev.maanraj514.essentially.utils.Color;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class HealCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) return true;
        Player p = (Player)sender;
        if (!p.hasPermission("Essentially.heal")) return true;

        if (args.length == 0) {
            p.sendMessage(Color.colorize("&eHealed YOU!"));
            p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
            return true;
        }
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            p.sendMessage(Color.colorize("&cPlayer must exist!"));
            return true;
        }
        if (target.isDead()) {
            p.sendMessage(Color.colorize("&cPlayer must not be dead!"));
            return true;
        }
        target.setHealth(target.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
        p.sendMessage(Color.colorize("&eHealed " + "&d" + target.getName()));
        target.sendMessage(Color.colorize("&eYou got healed by " + "&d" + p.getName()));
        return true;
    }
}
