package me.pyxled.token;

import me.pyxled.token.events.LoginEvent;
import me.pyxled.token.sqltools.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Pyxled Development 2016 (c)
 * File created: 01/09/2016
 */
public class Core extends JavaPlugin implements Listener
{
    public static PluginManager pm;
    private MySQL mysql;

    @Override
    public void onEnable(){
        mysql = new MySQL("[ip]", "[username]", "[password]", "[database]");
        pm = Bukkit.getPluginManager();
        regListeners();
    }
    private void regListeners(){
        pm.registerEvents(new LoginEvent(), this);
    }
    }
