package com.koloksk.commands;

import com.koloksk.listeners.OnJOin;
import com.koloksk.sm4;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class spawn implements CommandExecutor {
    ArrayList<String> zolci = sm4.getListz();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            return false;
        }
        Player p = (Player) sender;
        spawn(p);
        return false;

    }
    public void spawn(Player p){
        String WorldName = p.getWorld().getName();
        World w = p.getWorld();
        if(WorldName.equals("world")) {

            if (zolci.contains(p.getName())) {
                p.teleport(new Location(w, -10, 72, 66));

            } else {
                p.teleport(new Location(w, -30, 69, -152));
            }
        }
    }
}
