package com.shineyue.event;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class GrassBlockEvent extends Event implements Cancellable {
    public static HandlerList handlerList;

    public final Block grass;
    public final Player player;
    public boolean cancellable;

    public GrassBlockEvent(Block grass, Player player, boolean cancellable) {
        this.grass = grass;
        this.player = player;
        this.cancellable = cancellable;
    }

    public GrassBlockEvent(Block grass, Player player) {
        this.grass = grass;
        this.player = player;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static void setHandlerList(HandlerList handlerList) {
        GrassBlockEvent.handlerList = handlerList;
    }

    public Block getGrass() {
        return grass;
    }

    public Player getPlayer() {
        return player;
    }
    @Override
    public boolean isCancelled() {
        return cancellable;
    }

    public void setCancelled(boolean cancellable) {
        this.cancellable = cancellable;
    }


}
