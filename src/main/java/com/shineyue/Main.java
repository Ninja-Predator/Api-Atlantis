package com.shineyue;

import com.shineyue.commands.commands.Demo;
import com.shineyue.commands.commands.Demo2;
import com.shineyue.listener.*;
import org.bukkit.command.PluginCommand;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;



public class Main extends JavaPlugin implements Listener{

    @Override
    public void onEnable(){
        getLogger().info("加载成功");
        PluginCommand cmdDemo=getCommand("demo");
        cmdDemo.setExecutor(new Demo());
        PluginCommand cmdDemo2 = getCommand("demo2");
        cmdDemo2.setExecutor(new Demo2());
        regListeners(new JoinListener(),new BreakListener(),new SkeletonShootListener(),new BoomerHitListener(),new PlayerOnHitListener());
    }

    private void regListeners(Listener... listeners){
        for (Listener listener : listeners) {
            getServer().getPluginManager().registerEvents(listener,this);
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("卸载成功");
    }


}
