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
    private static MySQL mySQL;
    public static void getTokenBalance(Player p){
        mySQL.getTokenBalance(p);
    }
    public static void setTokenBalance(Player p, int amnt){
        mySQL.setTokenBalance(p, amnt);
    }
    public static void removeTokenBalace(Player p, int amnt){
        mySQL.removeTokenBalance(p, amnt);
    }
    public static void addTokenBalace(Player p, int amnt){
        mySQL.addTokenBalance(p, amnt);
    }
}
