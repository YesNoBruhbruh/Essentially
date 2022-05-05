package dev.maanraj514.essentially.commands;

import dev.maanraj514.essentially.utils.Color;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class RepairCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) return true;
        Player p = (Player)sender;

        if (args.length == 0) {
            p.sendMessage(Color.colorize("repairing all the items in your inventory"));
            for (ItemStack item : p.getInventory().getContents()){
                if (item != null) {
                    short maxDurability = item.getType().getMaxDurability();
                    item.setDurability(maxDurability);
                }
            }
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
        p.sendMessage(Color.colorize("repairing all the items in " + "&7" + target.getDisplayName() + "'s " + "&7inventory"));
        for (ItemStack item : p.getInventory().getContents()){
            if (item != null) {
                short maxDurability = item.getType().getMaxDurability();
                item.setDurability(maxDurability);
            }
        }
        return true;
    }
}
