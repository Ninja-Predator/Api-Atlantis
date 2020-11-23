package com.shineyue.commands.commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.List;

public class Demo implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            GameMode mode1 = GameMode.CREATIVE;
            GameMode mode0 = GameMode.SURVIVAL;
            if (args.length == 0) {
                if (player.getGameMode() == mode1) {
                    player.setGameMode(mode0);
                } else {
                    player.setGameMode(mode1);
                }
            } else if (args.length == 1) {
                if (args[0].equals("0")) {
                    player.setGameMode(mode0);
                } else if (args[0].equals("1")) {
                    player.setGameMode(mode1);
                } else {
                    player.sendMessage("fnndp");
                }
            } else {
                player.sendMessage("太长了");
                return true;
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return null;
    }
}
