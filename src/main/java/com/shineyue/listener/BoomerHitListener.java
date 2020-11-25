package com.shineyue.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.metadata.FixedMetadataValue;

public class BoomerHitListener implements Listener {
    @EventHandler
    public void onHit(ProjectileHitEvent e){
        if (e.getEntity().hasMetadata("boom")){
            if (e.getEntity().getMetadata("boom").get(0).asBoolean()) {
//                e.getEntity().getWorld().strikeLightning(e.getEntity().getLocation());
                e.getEntity().remove();
            }
        }
    }
}
