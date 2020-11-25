package com.shineyue.commands.commands;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Demo implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            GameMode mode1 = GameMode.CREATIVE;
            GameMode mode0 = GameMode.SURVIVAL;
            if (args.length == 0) {
                List<String> lore;
                ItemStack item=new ItemStack(Material.WOOD_SWORD);
                ItemMeta meta=item.getItemMeta();
                if (meta.hasLore()){
                    lore = meta.getLore();
                }else {
                    lore = new ArrayList<>();
                }
                lore.add("§b巨人杀手");
                lore.add("§c它有它的主人");
                meta.setLore(lore);
                meta.setDisplayName("§b斩铁剑");
                item.setDurability((short) 1);
                meta.addEnchant(Enchantment.PROTECTION_PROJECTILE,300,true);
                item.setItemMeta(meta);
                if (!player.getInventory().contains(item)){
                    player.getInventory().addItem(item);
                }
            } else if (args.length == 1) {
                if (args[0].equals("0")) {
                    player.setGameMode(mode0);
                } else if (args[0].equals("1")) {
                    player.setGameMode(mode1);
                } else {
                    player.sendMessage("fnndp");
                }
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return null;
    }
}
