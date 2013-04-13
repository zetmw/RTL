/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zet.riptel.gui;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author zet
 */
public class DB_conn {

    Connection conn = null;
    PreparedStatement pst = null;
    
    ResultSet rs = null;
    private boolean flgConn = false;
    private String driver = "com.mysql.jdbc.Driver";
    private static final Logger log = Logger.getLogger(DB_conn.class.getName());

    public DB_conn() {
    }

    public DB_conn(String url, String dbName, String userName, String password) {

        //setFlgConn(false);
        openDBcon(url, dbName, userName, password);

    }

    public final boolean openDBcon(String url, String dbName, String userName, String password) {
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url + dbName + "?connectTimeout=3000", userName, password);
            System.out.println("Connected to the database");
            setFlgConn(true);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            log.log(Level.SEVERE, "Error: ", e);
            //e.printStackTrace();           
            JOptionPane.showMessageDialog(null, "Error: " + e, "DB Connection", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public void closeDBconn() {
        try {        
            if (!conn.isClosed()) {
                try {
                    conn.close();
                    setFlgConn(false);
                } catch (SQLException e) {
                    log.log(Level.SEVERE, "Error: ", e);
                    //e.printStackTrace();   
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DB_conn.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }


    public void insMain(String ips, String icls) {
        try {       
            pst = conn.prepareStatement("INSERT INTO ipsgen (ip, class) VALUES (?,?) ");
            pst.setString(1, ips);
            pst.setString(2, icls);
            pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DB_conn.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void insOutp(String outp, String ip){
        try {
            pst = conn.prepareStatement("UPDATE ipsgen SET output = ? where ip = ?");
            pst.setString(1, outp);
            pst.setString(2, ip);
            pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DB_conn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insNmap(String nmap, String ip){
        try {
            pst = conn.prepareStatement("UPDATE ipsgen SET nmap = ? where ip = ?");
            pst.setString(1, nmap);
            pst.setString(2, ip);
            pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DB_conn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insCreds(String user, String pass, String ip){
        try {
            pst=conn.prepareStatement("UPDATE ipsgen SET user = ?, pass = ? where ip = ?");
            pst.setString(1, user);
            pst.setString(2, pass);
            pst.setString(3, ip);
            pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DB_conn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the flgConn
     */
    public boolean isFlgConn() {
        return flgConn;
    }

    /**
     * @param flgConn the flgConn to set
     */
    public void setFlgConn(boolean flgConn) {
        this.flgConn = flgConn;
    }
}
