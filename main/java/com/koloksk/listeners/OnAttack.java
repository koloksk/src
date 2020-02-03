package com.koloksk.listeners;

import com.koloksk.sm4;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.ArrayList;

public class OnAttack implements Listener {
    ArrayList<String> zolci = sm4.getListz();
    ArrayList<String> zieloni = sm4.getListzie();
    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event){
        if(event.getEntity() instanceof Player && event.getDamager() instanceof Player){
            Player player = (Player) event.getEntity();
            Player damager = (Player) event.getDamager();
            if (zolci.contains(player.getName()) && zolci.contains(damager.getName())){
                event.setCancelled(true);
            } else if (zieloni.contains(player.getName()) && zieloni.contains(damager.getName())){
                event.setCancelled(true);
            }
        }
    }
}
