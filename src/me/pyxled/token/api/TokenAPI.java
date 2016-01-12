package me.pyxled.token.api;

import me.pyxled.token.sqltools.MySQL;
import org.bukkit.entity.Player;

/**
 * Pyxled Development 2016 (c)
 * File created: 01/10/2016
 */
public class TokenAPI {
    private TokenAPI() { }

    static TokenAPI instance = new TokenAPI();

    public static TokenAPI getInstance() {
        return instance;
    }
    MySQL mySQL;
    public void getTokenBalance(Player p){
        mySQL.getTokenBalance(p);
    }
    public void setTokenBalance(Player p, int amnt){
        mySQL.setTokenBalance(p, amnt);
    }
    public boolean removeTokenBalace(Player p, int amnt){
        return mySQL.removeTokenBalance(p, amnt);
    }
    public void addTokenBalace(Player p, int amnt){
        mySQL.addTokenBalance(p, amnt);
    }
}
