package com.koloksk.listeners;

import com.koloksk.commands.spawn;
import com.koloksk.sm4;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.scoreboard.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OnClick implements Listener {
    sm4 plugin = sm4.getPlugin(sm4.class);

    com.koloksk.commands.spawn spawn = new spawn();
    ArrayList<String> zolci = sm4.getListz();
    ArrayList<String> zieloni = sm4.getListzie();
    @EventHandler
    public void OnClick(InventoryClickEvent e) throws SQLException {
        if (e.getInventory().getName().equals("Wybierz team")) {
            PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT count(*) FROM `dane` WHERE `team` = 'b'");

            ResultSet b = statement.executeQuery();
            b.next();
            PreparedStatement statement1 = plugin.getConnection().prepareStatement("SELECT count(*) FROM `dane` WHERE `team` = 'z'");

            ResultSet a = statement1.executeQuery();
            a.next();

            int roznica_zolci = a.getInt("count(*)") - b.getInt("count(*)");
            int roznica_zieloni = b.getInt("count(*)") - a.getInt("count(*)");

            Player p = (Player) e.getWhoClicked();
            String name = p.getName();
            e.setCancelled(true);
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§6§lZieloni")) {
                if(roznica_zieloni < 10) {
                    try {
                        PreparedStatement statement2 = plugin.getConnection()
                                .prepareStatement("UPDATE dane SET team ='b' WHERE nick = ?");
                        statement2.setString(1, name);
                        statement2.executeUpdate();


                        zieloni.add(name);
                        spawn.spawn(p);
                        scoreboard(p, "Zieloni");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }


            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§6§lZolci")) {
                if (roznica_zolci <= 10) {
                    try {
                        PreparedStatement statement3 = plugin.getConnection()
                                .prepareStatement("UPDATE dane SET team ='z' WHERE nick = ?");
                        statement3.setString(1, name);
                        statement3.executeUpdate();


                        zolci.add(name);
                        spawn.spawn(p);
                        scoreboard(p, "Zolci");


                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }
    static void belowname(Player p, String t){
        ScoreboardManager m1 = Bukkit.getScoreboardManager();
        Scoreboard b1 = m1.getNewScoreboard();
        Objective obj1 = b1.registerNewObjective("test", "dummy");
        obj1.setDisplaySlot(DisplaySlot.BELOW_NAME);
        obj1.setDisplayName("/ 20 "+t);
        p.setScoreboard(b1);
    }

    static void scoreboard(Player p, String t) {

        ScoreboardManager m1 = Bukkit.getScoreboardManager();
        Scoreboard b2 = m1.getNewScoreboard();

        Objective o = b2.registerNewObjective("serwer", "");
        o.setDisplaySlot(DisplaySlot.SIDEBAR);
        o.setDisplayName(ChatColor.DARK_AQUA + "Stylowa Masakra 4");


        Score ip = o.getScore("Wojna za: 7d");
        ip.setScore(2);

        Score aa = o.getScore("Kasa: %vault_eco_balance%");
        aa.setScore(4);



        Score ranga = o.getScore(ChatColor.WHITE + "Ranga: §7za niedługo");
        ranga.setScore(6);

        Score spacer1 = o.getScore("Team: "+t);
        spacer1.setScore(7);

        Score name = o.getScore("§7Nick: §0" + ChatColor.WHITE + p.getPlayer().getName());
        name.setScore(8);

        p.setScoreboard(b2);
    }
}
