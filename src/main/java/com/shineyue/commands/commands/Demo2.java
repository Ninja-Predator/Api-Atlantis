package com.shineyue.commands.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.sqrt;

public class Demo2 implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                circleArrow(player);
            } else {
                Player bPlayer = Bukkit.getPlayer(args[0]);
                circleArrow(bPlayer);
            }
        }
        return false;
    }

    private void circleArrow(Player player) {
        player.setNoDamageTicks(100);
        Location eyeLocation = player.getEyeLocation();
        Entity spawnEntity = player.getWorld().spawnEntity(player.getEyeLocation(), EntityType.FIREWORK);
        spawnEntity.setCustomName("一只穿云箭");
        spawnEntity.setCustomNameVisible(true);
        double xVector, zVector;
        Random rand = new Random();
        for (int i = 0; i < 40; i++) {
            xVector = rand.nextDouble() * 2 - 1;
            double sqrt = sqrt(1 - Math.pow(xVector, 2));
            if (rand.nextBoolean()) {
                zVector = sqrt;
            } else {
                zVector = -sqrt;
            }
            player.getWorld().createExplosion(player.getLocation(), 5, true);
            Arrow spawnArrow = player.getWorld().spawnArrow(eyeLocation.add(xVector, 80, zVector), new Vector(0, 10, 0), 0, 0);
            spawnArrow.setVelocity(new Vector(0, -5, 0));
            spawnArrow.setTicksLived(300);
            spawnArrow.setCritical(true);
            spawnArrow.setCustomNameVisible(true);
            Entity signalSpawn = player.getWorld().spawnEntity(spawnArrow.getLocation(), EntityType.ENDER_SIGNAL);
            signalSpawn.setCustomName("千军万马来相见");
            signalSpawn.setCustomNameVisible(true);
            spawnArrow.setPassenger(signalSpawn);
            eyeLocation.add(-xVector, -75, -zVector);
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        if (args.length == 1) {
            List<String> result = new ArrayList<>();
            Bukkit.getOnlinePlayers().forEach(player -> {
                if (player.getName().toLowerCase().startsWith(args[0].toLowerCase())) {
                    result.add(player.getName());
                }
            });
            return result;
        } else {
            return null;
        }
    }
}
