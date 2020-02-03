package com.koloksk.listeners;

import com.koloksk.sm4;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;

public class OnQuit implements Listener {
    ArrayList<String> zolci = sm4.getListz();
    ArrayList<String> zieloni = sm4.getListzie();
    @EventHandler
    public void OnQuit(PlayerQuitEvent e){
        e.setQuitMessage(null);
        zolci.remove(e.getPlayer().getName());
        zieloni.remove(e.getPlayer().getName());
    }
}
