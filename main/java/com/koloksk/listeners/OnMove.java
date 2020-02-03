package com.koloksk.listeners;

import com.koloksk.sm4;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;

public class OnMove implements Listener {

    ArrayList<String> zolci = sm4.getListz();
    ArrayList<String> zieloni = sm4.getListzie();
    @EventHandler
    public void OnMove(PlayerMoveEvent e){
        Player p = e.getPlayer();
;


        if (!zieloni.contains(p.getName()) && !zolci.contains(p.getName())) {
            e.setCancelled(true);

        }
}
}
