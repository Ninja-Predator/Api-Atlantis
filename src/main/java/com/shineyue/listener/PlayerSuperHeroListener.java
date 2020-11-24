package com.shineyue.listener;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerSuperHeroListener implements Listener {
    @EventHandler
    public void onHit(EntityDamageByEntityEvent e){
        if (e.getDamager().getType()== EntityType.PLAYER){
            if (((Player)e.getDamager()).getName().equals("M_Lizi")){
                Player p=(Player)e.getDamager();
                if (p.getItemInHand().getItemMeta().getLore().contains("§b巨人杀手")){
                    e.setCancelled(true);
                    LivingEntity livingEntity=(LivingEntity)e.getEntity();
                    double v = livingEntity.getHealth();
                    livingEntity.setHealth(v/2);
                    p.sendMessage("§a你对"+e.getEntity().getName()+"造成了"+v/2+"的真实伤害");
                }
            }
        }
    }
}
