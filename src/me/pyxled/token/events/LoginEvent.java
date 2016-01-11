package me.pyxled.token.events;

import me.pyxled.token.sqltools.MySQL;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;


/**
 * Pyxled Development 2016 (c)
 * File created: 01/10/2016
 */
public class LoginEvent implements Listener{
    MySQL sql;

    @EventHandler
    public void onLogin(PlayerLoginEvent event){
        Player p = event.getPlayer();
        if(!p.hasPlayedBefore()){
            sql.insertNewUser(p, 0);
        }
    }
}
