package dev.maanraj514.essentially.commands;

import dev.maanraj514.essentially.utils.Color;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class FlyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) return true;
        Player p = (Player)sender;
        if (!p.hasPermission("Essentially.fly")) return true;

        if (args.length == 0){
            if (!p.getAllowFlight()){
                p.setAllowFlight(true);
                p.sendMessage(Color.colorize("&aAllowed flight for " + "&eYourself"));
            }else{
                p.setAllowFlight(false);
                p.sendMessage(Color.colorize("&cUnAllowed flight for " + "&eYourself"));
            }
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null){
            p.sendMessage(Color.colorize("&cPlayer must exist!"));
            return true;
        }
        if (!target.isOnline()){
            p.sendMessage(Color.colorize("&cThat player is offline"));
            return true;
        }
        if (target.isDead()) {
            p.sendMessage(Color.colorize("&cThat player is dead"));
            return true;
        }
        if (!target.getAllowFlight()){
            target.setAllowFlight(true);
            p.sendMessage(Color.colorize("&aAllowed flight for " + "&e" + target.getName()));
        }else{
            target.setAllowFlight(false);
            p.sendMessage(Color.colorize("&cUnAllowed flight for " + "&e" + target.getName()));
        }
        return true;
    }
}
