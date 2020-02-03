package com.koloksk.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class cancelcommand implements Listener {
    @EventHandler
    public void preProcessCommand(PlayerCommandPreprocessEvent event) {
        if (event.getMessage().equalsIgnoreCase("/spawn")) {
            event.setCancelled(true);
        }
        if (event.getMessage().equalsIgnoreCase("/tpa")) {
            event.setCancelled(true);
        }
        if (event.getMessage().equalsIgnoreCase("/spawn")) {
            event.setCancelled(true);
        }
    }
}
