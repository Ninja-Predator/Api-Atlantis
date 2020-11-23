package com.shineyue.listener;

import com.shineyue.event.GrassBlockEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;



public class GrassBreakListener implements Listener {
    @EventHandler
    public void onBreak(BlockBreakEvent e){
        if (e.getBlock().getType() == Material.GRASS){
            GrassBlockEvent event = new GrassBlockEvent(e.getBlock(),e.getPlayer());
            Bukkit.getPluginManager().callEvent(event);
        }
    }

    @EventHandler
    public void onBreakGrass(GrassBlockEvent e){
        e.setCancelled(false);
        e.getPlayer().sendMessage("这样不好");
    }
}
