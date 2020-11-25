package com.shineyue.listener;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityResistanceListener implements Listener {
    @EventHandler
    public void onShot(EntityDamageByEntityEvent e) {
        if (e.getEntityType() == EntityType.SKELETON) {
            if (e.getDamager() instanceof Projectile) {
                e.setCancelled(true);
            }
        } else if (e.getEntityType() == EntityType.SILVERFISH) {
            if (!(e.getDamager() instanceof Projectile)) {
                e.setCancelled(true);
            }
        }
    }
}
