package com.koloksk.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class OnMine implements Listener {
    @EventHandler
    public void OnMine(BlockBreakEvent e){
        Player p = e.getPlayer();

        if(e.getBlock().getType() == Material.DIAMOND_BLOCK){
            Location loc = e.getBlock().getLocation();
            World world = p.getWorld();
            e.getBlock().getDrops().clear();
            e.setDropItems(false);
            world.dropItem(loc, new ItemStack(Material.COBBLESTONE));
        }
        if(e.getBlock().getType() == Material.STONE){
            Location loc = e.getBlock().getLocation();

            Random r = new Random();
            int game = r.nextInt(600);
            if(game == 2){
                World world = p.getWorld();
                world.dropItem(loc, new ItemStack(Material.DIAMOND));
                p.sendMessage("Brawo Znalazłeś diament");
            }
        }
    }
}
