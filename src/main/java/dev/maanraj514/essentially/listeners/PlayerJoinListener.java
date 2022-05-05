package dev.maanraj514.essentially.listeners;

import dev.maanraj514.essentially.Essentially;
import dev.maanraj514.essentially.utils.Color;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private final Essentially plugin;

    public PlayerJoinListener(Essentially plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        if (plugin.getConfig().getBoolean("announce-join")) {
            if (!p.hasPermission("Essentially.admin")) {
                event.setJoinMessage(Color.colorize("&aThe player " + "&d" + p.getName() + " &aHas joined!"));
            } else {
                event.setJoinMessage(Color.colorize("&c&lThe player " + "&d&l" + p.getName() + " &a&lHas joined!"));
            }
        }
    }
}