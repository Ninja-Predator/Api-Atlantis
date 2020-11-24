package com.shineyue.listener;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Bukkit.getLogger;

public class EntityDieListener implements Listener {
    static List<EntityType> friendlyAnimals = new ArrayList<>();
    static {
        friendlyAnimals.add(EntityType.CHICKEN);
        friendlyAnimals.add(EntityType.PIG);
        friendlyAnimals.add(EntityType.SHEEP);
        friendlyAnimals.add(EntityType.HORSE);
        friendlyAnimals.add(EntityType.COW);
    }
    @EventHandler
    public void entityDie(EntityDeathEvent e){
        Entity deadEntity = e.getEntity();
        EntityType deadEntityType = deadEntity.getType();
        LivingEntity spawnEntity;
        List<ItemStack> drops;

        if (deadEntityType == EntityType.ZOMBIE){
            if (Math.random()>0&&Math.random()<=0.08){
                spawnEntity = (LivingEntity) deadEntity.getWorld().spawnEntity(deadEntity.getLocation(), EntityType.GIANT);
                spawnEntity.setMaxHealth(1000);
                spawnEntity.setHealth(1000);
            }
        }else if (deadEntityType == EntityType.SKELETON){
            deadEntity.getWorld().spawnEntity(deadEntity.getLocation(), EntityType.SILVERFISH);
        }else if (friendlyAnimals.contains(deadEntityType)){
            if (Math.random()>0.75){
                spawnEntity = (LivingEntity) deadEntity.getWorld().spawnEntity(deadEntity.getLocation(), EntityType.BLAZE);
                spawnEntity.setMaxHealth(70);
                spawnEntity.setHealth(70);
            }
        }else if (deadEntityType == EntityType.SILVERFISH){
            if (Math.random()>0.85){
                deadEntity.getWorld().dropItem(deadEntity.getLocation(),new ItemStack(Material.FIRE));
            }
        }
    }
}
