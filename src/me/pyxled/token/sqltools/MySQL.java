package me.pyxled.token.sqltools;

import org.bukkit.entity.Player;

import java.sql.*;

/**
 * Pyxled Development 2016 (c)
 * File created: 01/09/2016
 */
public class MySQL {
    private Connection connection;

    public MySQL(String ip, String userName, String password, String db) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + ip + "/" + db + "?user=" + userName + "&password=" + password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void insertNewUser(Player p, int sb){
        try{
            PreparedStatement s = connection.prepareStatement("insert into users (uuid, ign, tokens)\nvalues('" + p.getUniqueId() + "', '" + p.getName() + "', " + sb + "');");
            s.executeUpdate();
            s.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public int getTokenBalance(Player p){
        try {
            PreparedStatement s = connection.prepareStatement("select tokens from users where uuid'" + p.getUniqueId() + "'");
            ResultSet rs = s.executeQuery();
            if(rs.next()){
               return rs.getInt("tokens");
            } else {
                return 0;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
    public void setTokenBalance(Player p, int amnt){
        try{
            PreparedStatement s = connection.prepareStatement("select 'tokens' from users where uuid'" + p.getUniqueId() + "'");
            ResultSet rs = s.executeQuery();
            rs.next();

            PreparedStatement setUpdate = connection.prepareStatement("update users set tokens=? where uuid'" + p.getUniqueId() + "'");
            setUpdate.setInt(1, amnt);
            s.executeUpdate();
            setUpdate.close();
            s.close();
            rs.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void addTokenBalance(Player p, int amnt){
        try{
            PreparedStatement s = connection.prepareStatement("select 'tokens' from users where uuid'" + p.getUniqueId() + "'");
            ResultSet rs = s.executeQuery();
            rs.next();

            PreparedStatement addUpdate = connection.prepareStatement("update users set tokens=? where uuid'" + p.getUniqueId() + "'");
            addUpdate.setInt(1, getTokenBalance(p) + amnt);
            s.executeUpdate();
            addUpdate.close();
            s.close();
            rs.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public boolean removeTokenBalance(Player p, int amnt){

        return true;
    }
}
