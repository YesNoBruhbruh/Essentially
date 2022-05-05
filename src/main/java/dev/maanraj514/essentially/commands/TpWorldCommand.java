package dev.maanraj514.essentially.commands;

import dev.maanraj514.essentially.utils.Color;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TpWorldCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) return true;
        Player p = (Player)sender;
        if (!p.hasPermission("Essentially.tpworld")) return true;

        if (args.length == 0) {
            p.sendMessage(Color.colorize("&c/tpworld <world> or &c/tpworld <player> <world>"));
            return true;
        }
        Player target = (Player) sender;
        World targetWorld;

        String lookup = args[0];

        if (Bukkit.getPlayer(lookup) == null) {
            // So we know that arg 0 is a world name.
            targetWorld = Bukkit.getWorld(lookup);
        } else {
            // So we know that arg 0 is a player, aka the target of this command.
            target = Bukkit.getPlayer(lookup);

            // We are try-catching because a possibly arrayoutofbounds exception.
            try {
                targetWorld = Bukkit.getWorld(args[1]);
            } catch (Exception e) {
                p.sendMessage(Color.colorize("&c/tpworld <world> or &c/tpworld <player> <world>"));
                return true;
            }
        }
        if (target == null || targetWorld == null) {
            p.sendMessage(Color.colorize("&cThe player or world must exist!"));
            p.sendMessage(Color.colorize("&c/tpworld <world> or &c/tpworld <player> <world>"));
            return true;
        }
        Location loc = targetWorld.getSpawnLocation();
        target.teleport(loc);
        return true;
    }

    @Override
    public List onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String alias, @NotNull String[] args) {
        if (cmd.getName().equalsIgnoreCase("tpworld")) {
            if (!sender.hasPermission("Essentially.tpworld")) return Collections.EMPTY_LIST;
            final List<String> oneArgList = new ArrayList<>();
            final List<String> completions = new ArrayList<>();

            // Adds all worlds to the list
            for (World w : Bukkit.getWorlds()){
                oneArgList.add(w.getName());
            }
            if (args.length == 1){
                return null;
                // Returns null to get all online players
            }
            if (args.length == 2){
                StringUtil.copyPartialMatches(args[1], oneArgList, completions);
            }
            Collections.sort(completions);
            return completions;
        }
        return Collections.EMPTY_LIST;
    }
}
