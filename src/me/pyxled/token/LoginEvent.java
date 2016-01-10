package me.pyxled.token;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPreLoginEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Pyxled Development 2016 (c)
 * File created: 01/09/2016
 */
public class LoginEvent implements Listener {
    Core main = Core.plugin;

    @SuppressWarnings("deprecation, static-access")
    @EventHandler
    public void opple(PlayerPreLoginEvent e)
    {
        try {
            ResultSet res = main.c.createStatement().executeQuery("SELECT * FROM users WHERE uuid= '" + e.getUniqueId() + "';");
            if(!res.next())
            {
                main.c.createStatement().executeUpdate("INSERT INTO `users` (`uuid`, `ign`, `rank`) VALUE('" + e.getUniqueId() + "', '" + e.getName() + "', '" + "Default" + "');");
            }
        }
        catch(SQLException sql) {
            sql.printStackTrace();
        }
    }
}
