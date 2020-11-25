package com.shineyue.listener;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BreakListener implements Listener {
    @EventHandler
    public void onBreak(BlockBreakEvent e){
        if (e.getBlock().getLightLevel()<2){
            if (e.getPlayer()!=null){
                if(Math.random()>0&&Math.random()<=0.02) {
                    LivingEntity spawnEntity = (LivingEntity) e.getPlayer().getWorld().spawnEntity(e.getPlayer().getLocation(),EntityType.GIANT);
                    spawnEntity.setMaxHealth(1000);
                    spawnEntity.setHealth(1000);
                }
            }
        }
    }
}
