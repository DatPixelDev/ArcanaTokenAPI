//package me.pyxled.token;
//
//import org.bukkit.entity.Player;
//import org.bukkit.event.EventHandler;
//import org.bukkit.event.Listener;
//import org.bukkit.event.player.PlayerLoginEvent;
//import org.bukkit.plugin.java.JavaPlugin;
//
//import java.sql.*;
//
///**
// * Pyxled Development 2016 (c)
// * File created: 01/09/2016
// */
//public class Core extends JavaPlugin implements Listener{
//    private static Connection c;
//    @Override
//    public void onEnable(){
//        getServer().getPluginManager().registerEvents(this, this);
//    }
//    @Override
//    public void onDisable(){
//        try {
//            if (c == null && c.isClosed())
//                c.close();
//        }
//        catch (SQLException e){
//            e.printStackTrace();
//        }
//    }
//    public synchronized static void closeConnecton(){
//        try{
//            c.close();
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }
//    }
//    public synchronized static void openConnecton(){
//        try{
//            c = DriverManager.getConnection("jdbc:mysql://<IP>:<PORT>/<DBNAME>", "<USERNAME>", "<PASSWORD>");
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }
//    }
//    public synchronized static boolean playerDataContainsPlayer(Player p){
//        try{
//            PreparedStatement sql = c.prepareStatement("SELECT * FROM `player_data` WHERE player=?;");
//            sql.setString(1, p.getName());
//            ResultSet rs = sql.executeQuery();
//            boolean containsPlayer = rs.next();
//
//            sql.close();
//            rs.close();
//
//            return containsPlayer;
//        }
//        catch (Exception e){
//            e.printStackTrace();
//            return false;
//        }
//    }
//    @EventHandler
//    public void onLogin(PlayerLoginEvent event){
//        openConnecton();
//        try{
//            int previouslogins = 0;
//            if(playerDataContainsPlayer(event.getPlayer())){
//                PreparedStatement sql = c.prepareStatement("SELECT logins FROM `player_data` WHERE player=?;");
//                sql.setString(1, event.getPlayer().getName());
//
//                ResultSet rs = sql.executeQuery();
//                rs.next();
//
//                previouslogins = rs.getInt("logins");
//
//                PreparedStatement loginsUpdate = c.prepareStatement("UPDATE `player_data` SET logins=? WHERE player=?;");
//                loginsUpdate.setInt(1, previouslogins+1);
//                loginsUpdate.setString(2, event.getPlayer().getName());
//                loginsUpdate.executeUpdate();
//
//                loginsUpdate.close();
//                sql.close();
//                rs.close();
//            } else {
//                PreparedStatement newplayer = c.prepareStatement("INSERT INTO `player_data` values(?,0,0,1);");
//                newplayer.setString(1 ,event.getPlayer().getName());
//                newplayer.execute();
//                newplayer.close();
//            }
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//        finally {
//            closeConnecton();
//        }
//    }
//}
