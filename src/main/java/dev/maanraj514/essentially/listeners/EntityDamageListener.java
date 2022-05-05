package dev.maanraj514.essentially.listeners;

import dev.maanraj514.essentially.Essentially;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamageListener implements Listener {
    private final Essentially plugin;

    public EntityDamageListener(Essentially plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onDmg(EntityDamageEvent event) {
        if (plugin.getGodModePlayers().contains(event.getEntity().getUniqueId())){
            event.setCancelled(true);
        }
    }
}
