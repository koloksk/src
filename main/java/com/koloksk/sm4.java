package com.koloksk;

import com.koloksk.commands.spawn;
import com.koloksk.listeners.*;
import net.minecraft.server.v1_12_R1.ChatComponentText;
import net.minecraft.server.v1_12_R1.PacketPlayOutPlayerListHeaderFooter;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

public final class sm4 extends JavaPlugin {
    Connection conn = null;
    static ArrayList<String> zolci = new ArrayList<>();
    static ArrayList<String> zieloni = new ArrayList<>();
    @Override
    public void onEnable() {

        PluginManager add = getServer().getPluginManager() ;

        mysqlSetup();
        add.registerEvents(new OnClick(), this);
        add.registerEvents(new OnJOin(), this);
        add.registerEvents(new OnAttack(), this);
        add.registerEvents(new OnQuitInv(), this);
        add.registerEvents(new OnQuit(), this);
        add.registerEvents(new cancelcommand(), this);
        add.registerEvents(new OnMove(), this);
        getCommand("spawn").setExecutor(new spawn());
        PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
        new BukkitRunnable() {

            @Override
            public void run() {
                try {
                    Field a = packet.getClass().getDeclaredField("a");
                    a.setAccessible(true);
                    Field b = packet.getClass().getDeclaredField("b");
                    b.setAccessible(true);

                    Object header2 = new ChatComponentText("§aStylowa Masakra 4\n§3Witamy na naszym serwerze!");
                    Object footer = new ChatComponentText("§a§l");

                    a.set(packet, header2);

                    b.set(packet, footer);

                    if (Bukkit.getOnlinePlayers().size() == 0) return;
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
                    }

                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }.runTaskTimer(this, 0, 20);

    }
    public void mysqlSetup() {




        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql:///bazka?autoReconnect=true", "koloksk", "qweasdzxc333");

        }catch(Exception e){ System.out.println(e);}
    }
    public Connection getConnection() {
        return conn;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static ArrayList<String> getListz() {
        return zolci;
    }
    public static ArrayList<String> getListzie() {
        return zieloni;
    }
}
