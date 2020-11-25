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
                if (p.getItemInHand().hasItemMeta()) {
                    if (p.getItemInHand().getItemMeta().getLore().contains("§b巨人杀手")){
                        LivingEntity livingEntity = (LivingEntity) e.getEntity();
                        double v = livingEntity.getHealth();
                        p.sendMessage("§a你造成了额外" + String.format("%.2f", v / 2) + "的真实伤害");
                        livingEntity.setHealth(v / 2);
                        livingEntity.setNoDamageTicks(300);
                        e.setDamage(1);
                    }
                }
            }
        }
    }
}
