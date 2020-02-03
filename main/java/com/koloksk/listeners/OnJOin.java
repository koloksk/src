package com.koloksk.listeners;

import com.koloksk.sm4;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Wool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OnJOin implements Listener {
    sm4 plugin = sm4.getPlugin(sm4.class);
    ArrayList<String> zolci = sm4.getListz();
    ArrayList<String> zieloni = sm4.getListzie();




    @EventHandler
    public void OnJOin(PlayerJoinEvent e) {

        Player p = e.getPlayer();
        e.setJoinMessage(null);

        String nick = p.getName();

        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM `dane` WHERE `nick` = '" + nick + "'");

            ResultSet results = statement.executeQuery();
            results.next();

            if (results.getString("team") == null) {
                Bukkit.getScheduler().runTaskLater(plugin, new Runnable(){
                    @Override
                    public void run(){
                        try {
                            openinvteam(p);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                }, 20L);

            } else if (results.getString("team").equals("b")){
                zieloni.add(p.getName());
                //--------Under Nick
                OnClick.belowname(p,"Zieloni");
                //----------Scoreboard
                OnClick.scoreboard(p,"Zieloni");

            } else if (results.getString("team").equals("z")){
                zolci.add(p.getName());
                OnClick.scoreboard(p,"Zolci");
                OnClick.belowname(p,"Zolci");

            }
        } catch (SQLException | IllegalArgumentException ex) {
            ex.printStackTrace();
        }

    }

    public void openinvteam(Player p) throws SQLException {
        PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT count(*) FROM `dane` WHERE `team` = 'b'");

        ResultSet results = statement.executeQuery();
        results.next();
        PreparedStatement statement1 = plugin.getConnection().prepareStatement("SELECT count(*) FROM `dane` WHERE `team` = 'z'");

        ResultSet results1 = statement1.executeQuery();
        results1.next();
        Wool wool = new Wool(DyeColor.GREEN);
        ItemStack cziel = wool.toItemStack(1);
        Wool wool1 = new Wool(DyeColor.YELLOW);
        ItemStack czol = wool1.toItemStack(1);
        Inventory help = Bukkit.getServer().createInventory(p, 9, "Wybierz team");

        //Here you define our item
        ItemStack ref1 = new ItemStack(cziel);
        ItemMeta metaref1 = ref1.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        lore.add("Liczba"+results.getInt("count(*)"));


        metaref1.setLore(lore);
        metaref1.setDisplayName("§6§lZieloni");
        ref1.setItemMeta(metaref1);
        help.setItem(2, ref1);
        //--------------------------
        ItemStack ref2 = new ItemStack(czol);;
        ItemMeta metaref2 = ref1.getItemMeta();
        ArrayList<String> lore2 = new ArrayList<>();

        lore2.add("Liczba"+results1.getInt("count(*)"));
        metaref2.setLore(lore2);
        metaref2.setDisplayName("§6§lZolci");


        ref2.setItemMeta(metaref2);
        help.setItem(6, ref2);


        //Here opens the inventory
        p.openInventory(help);

    }





}
