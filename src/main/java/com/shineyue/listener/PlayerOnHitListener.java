package com.shineyue.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.Random;

import static java.lang.Math.sqrt;
import static org.bukkit.Bukkit.getLogger;

public class PlayerOnHitListener implements Listener {
    @EventHandler
    public void onHit(EntityDamageByEntityEvent e){
        if (e.getDamager().hasMetadata("boom")){
            if (e.getDamager().getMetadata("boom").get(0).asBoolean()){
                LivingEntity entity = (LivingEntity) e.getEntity();
                e.getDamager().removeMetadata("boom",Bukkit.getPluginManager().getPlugin("MinecraftModDemo"));
                entity.setNoDamageTicks(0);
                circleArrow(entity);
            }
        } if (e.getDamager().getType()==EntityType.ZOMBIE){
            if (!e.getDamager().hasMetadata("boom")&&Math.random()>0&&Math.random()<=0.1&&e.getEntityType()==EntityType.PLAYER){
                Player p = (Player)e.getEntity();
                p.sendMessage("当心，有僵尸要自爆了！");
                e.getDamager().setMetadata("boom",new FixedMetadataValue(Bukkit
                        .getPluginManager().getPlugin("MinecraftModDemo"),true));
            }else if (Math.random()>0.7 && e.getEntityType()== EntityType.PLAYER){
                Player p = (Player)e.getEntity();
                p.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER,5,30));
                p.sendMessage("你感到一阵饥饿！");
            }else if (Math.random()>0.6 &&Math.random()<0.63 && e.getEntityType()== EntityType.PLAYER){
                Player p = (Player)e.getEntity();
                p.setMetadata("boom",new FixedMetadataValue(Bukkit
                        .getPluginManager().getPlugin("MinecraftModDemo"),true));
                p.sendMessage("当心！你被僵尸挂上了炸弹！");
            }
        }
    }



    private void circleArrow(LivingEntity player) {
        Location eyeLocation = player.getEyeLocation();
        double xVector, zVector;
        Random rand = new Random();
        Vector v = new Vector(0, -5, 0);
        for (int i = 0; i < 40; i++) {
            xVector = rand.nextDouble() * 2 - 1;
            double sqrt = sqrt(1 - Math.pow(xVector, 2));
            if (rand.nextBoolean()) {
                zVector = sqrt;
            } else {
                zVector = -sqrt;
            }
            player.getWorld().createExplosion(player.getLocation(), 0.2f, true);
            Arrow spawnArrow = player.getWorld().spawnArrow(eyeLocation.add(xVector, 80, zVector),v, 0, 0);
            spawnArrow.setVelocity(v);
            spawnArrow.setCustomNameVisible(true);
            eyeLocation.add(-xVector, -75, -zVector);
        }
    }
}
