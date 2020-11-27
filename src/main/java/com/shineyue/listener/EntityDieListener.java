package com.shineyue.listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

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
    public void entityDie(EntityDeathEvent e) {
        Entity deadEntity = e.getEntity();
        EntityType deadEntityType = deadEntity.getType();
        LivingEntity spawnEntity;
        List<ItemStack> drops;
        ItemStack drop;

        if (deadEntity.hasMetadata("deathExplosion")) {
            deadEntity.getWorld().createExplosion(deadEntity.getLocation(), 0.5f);
        }
        if (deadEntityType == EntityType.ZOMBIE) {
            deadEntity.getWorld().dropItem(deadEntity.getLocation(), new ItemStack(Material.GOLD_BLOCK,4));
            if (Math.random() > 0 && Math.random() <= 0.08) {
                spawnEntity = (LivingEntity) deadEntity.getWorld().spawnEntity(deadEntity.getLocation(), EntityType.GIANT);
                spawnEntity.setMaxHealth(1000);
                spawnEntity.setHealth(1000);
            }
        } else if (deadEntityType == EntityType.SKELETON) {
            spawnEntity = (LivingEntity) deadEntity.getWorld().spawnEntity(deadEntity.getLocation(), EntityType.SILVERFISH);
            spawnEntity.setMaxHealth(1);
            spawnEntity.setHealth(1);
        } else if (friendlyAnimals.contains(deadEntityType)) {
            if(e.getEntity().getLastDamageCause().getCause()==EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
                if (Math.random() > 0.85) {
                    spawnEntity = (LivingEntity) deadEntity.getWorld().spawnEntity(deadEntity.getLocation(), EntityType.BLAZE);
                    spawnEntity.setMaxHealth(15);
                    spawnEntity.setHealth(15);
                    spawnEntity.setCustomName("复仇者");
                    spawnEntity.setCustomNameVisible(true);
                }
            }
        } else if (deadEntityType == EntityType.SILVERFISH) {
            if (Math.random() > 0.75) {
                deadEntity.getWorld().dropItem(deadEntity.getLocation(), new ItemStack(Material.FIRE));
            }
        } else if (deadEntityType == EntityType.CREEPER) {
            deadEntity.getWorld().createExplosion(deadEntity.getLocation(), 0.5f);
            spawnEntity = (LivingEntity) deadEntity.getWorld().spawnEntity(deadEntity.getLocation(), EntityType.BAT);
            spawnEntity.setMetadata("deathExplosion", new FixedMetadataValue(Bukkit
                    .getPluginManager().getPlugin("MinecraftModDemo"), true));
        } else if (deadEntityType == EntityType.SPIDER) {
            for (int i = 1; Math.random() < 0.6 / i; i *= 6) {
                deadEntity.getWorld().spawnEntity(deadEntity.getLocation(), EntityType.CAVE_SPIDER);
            }
        } else if (deadEntityType == EntityType.CAVE_SPIDER) {
            if (Math.random() > 0.75) {
                deadEntity.getWorld().spawnEntity(deadEntity.getLocation(), EntityType.CAVE_SPIDER);
            }
        } else if (deadEntityType == EntityType.GIANT) {
            drops = new ArrayList<>();
            drops.add(new ItemStack(Material.ROTTEN_FLESH, 64));
            drops.add(new ItemStack(Material.GOLD_BLOCK, 64));
            for (ItemStack itemStack : drops) {
                deadEntity.getWorld().dropItem(deadEntity.getLocation(), itemStack);
            }
        }
    }
}
