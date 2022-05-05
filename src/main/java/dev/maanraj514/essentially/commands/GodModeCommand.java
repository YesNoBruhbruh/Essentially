package dev.maanraj514.essentially.commands;

import dev.maanraj514.essentially.Essentially;
import dev.maanraj514.essentially.utils.Color;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GodModeCommand implements CommandExecutor {
    private final Essentially plugin;

    public GodModeCommand(Essentially plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) return true;
        Player p = (Player)sender;
        if (!p.hasPermission("Essentially.godmode")) return true;

        if (args.length == 0){
            if (!plugin.getGodModePlayers().contains(p.getUniqueId())){
                p.sendMessage(Color.colorize("&aEnabled GodMode"));
                plugin.getGodModePlayers().add(p.getUniqueId());
            }else{
                p.sendMessage(Color.colorize("&cUnEnabled GodMode"));
                plugin.getGodModePlayers().remove(p.getUniqueId());
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
        if (!plugin.getGodModePlayers().contains(target.getUniqueId())){
            p.sendMessage(Color.colorize("&aAllowed godmode for " + "&e" + target.getName()));
            plugin.getGodModePlayers().add(target.getUniqueId());
        }else{
            p.sendMessage(Color.colorize("&cUnAllowed godmode for " + "&e" + target.getName()));
            plugin.getGodModePlayers().remove(target.getUniqueId());
        }
        return true;
    }
}
