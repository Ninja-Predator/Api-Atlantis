package com.shineyue.listener;

import com.shineyue.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.Metadatable;
import org.bukkit.plugin.Plugin;


import static org.bukkit.Bukkit.getLogger;


public class SkeletonShootListener implements Listener {
    private static com.shineyue.Main main;

    @EventHandler
    public void onShoot(EntityShootBowEvent e){
        if (e.getEntity().getType()==EntityType.SKELETON){
            if(Math.random()>0&&Math.random()<=0.3) {
                e.getProjectile().setMetadata("boom",new FixedMetadataValue(Bukkit
                        .getPluginManager().getPlugin("MinecraftModDemo"),true));
            }else {
                Entity signalSpawn = e.getEntity().getWorld().spawnEntity(e
                        .getProjectile().getLocation(), EntityType.ENDER_SIGNAL);
                signalSpawn.setCustomName("蛇了蛇了");
                signalSpawn.setTicksLived(10);
                signalSpawn.setCustomNameVisible(true);
                e.getProjectile().setPassenger(signalSpawn);
                e.getProjectile().setMetadata("boom",new FixedMetadataValue(Bukkit
                        .getPluginManager().getPlugin("MinecraftModDemo"),false));
            }
//            getLogger().info(e.getEntity().getType().name()+"蛇了"+ e.getProjectile().getMetadata("boom").get(0).asBoolean());
        }
    }
}
