package me.pyxled.token;

import me.pyxled.token.sqltools.MySQL;
import me.pyxled.token.sqltools.SQLD;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Pyxled Development 2016 (c)
 * File created: 01/09/2016
 */
public class Core extends JavaPlugin
{
    public static Core plugin;
    public static PluginManager pm;
    public static BukkitScheduler bs;
    public static Connection c;
    static MySQL SQL = new MySQL(SQLD.host, SQLD.port, SQLD.db, SQLD.user, SQLD.pw);

    @Override
    public void onEnable(){
        plugin = this;
        pm = Bukkit.getPluginManager();
        bs = Bukkit.getScheduler();
        regListeners();
    }
    @SuppressWarnings("static-access")
    @Override
    public void onDisable()
    {
        c = SQL.closeConnection(c);
    }
    private void regListeners(){
        pm.registerEvents(new LoginEvent(), this);
    }
    private void printData() {
        new BukkitRunnable() {
            @Override
            public void run() {
                openConnection();

                List<String> temp = getNameColumn("name");

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (!temp.isEmpty()) {
                            for (String name : temp) {
                                System.out.println("I found the name: " + name);
                            }
                        } else {
                            System.out.println("There were no names :(");
                        }
                    }
                }.runTask(plugin);
            }
        }.runTaskAsynchronously(this);
    }
    private synchronized void openConnection(){
        try {
            c = SQL.open();
        }
        catch (Exception sql){
            sql.printStackTrace();
        }
    }
    public void InsertElement(String key, String element){
        try {
            String query = "INSERT INTO " + SQLD.db + " VALUES(?, ?)";
            PreparedStatement ps = c.prepareCall(query);
            ps.setString(1, key);
            ps.setString(2, element);
            ps.execute();
            ps.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    private List<String> getNameColumn(String columnName) {
        List<String> temp = new ArrayList<>();
        final String QUERY = "SELECT name FROM some_database";
        try {
            ResultSet res = c.prepareStatement(QUERY).executeQuery();
            while (res.next()) {
                temp.add(res.getString(columnName));
            }
            res.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return temp;
    }
}
