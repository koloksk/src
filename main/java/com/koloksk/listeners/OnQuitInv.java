
package com.koloksk.listeners;

import com.koloksk.sm4;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.sql.SQLException;
import java.util.ArrayList;

public class OnQuitInv implements Listener {
    sm4 plugin = sm4.getPlugin(sm4.class);

    OnJOin join = new OnJOin();
    ArrayList<String> zolci = sm4.getListz();
    ArrayList<String> zieloni = sm4.getListzie();

    @EventHandler
    public void OnQuitInv(InventoryCloseEvent e) {
        HumanEntity p = e.getPlayer();
        if (!zieloni.contains(p.getName()) && !zolci.contains(p.getName())) {

            for (Player pl : Bukkit.getServer().getOnlinePlayers()) {
                if (pl.getUniqueId().equals(p.getUniqueId())) {

                    if (e.getInventory().getName().equals("Wybierz team")) {
                        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    join.openinvteam((Player) p);
                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }, 5L);


                    }
                }
            }
        }
    }
}

