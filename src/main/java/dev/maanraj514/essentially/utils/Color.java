package dev.maanraj514.essentially.utils;

import org.bukkit.ChatColor;

public class Color {

    public static String colorize(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static String stripColor(String msg) {
        return ChatColor.stripColor(msg);
    }

}
