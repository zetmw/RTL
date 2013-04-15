/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zet.utils.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RIPT extends Thread {

    private String ip;
    private boolean conStatus;
    private Socket socket;
    private int SOCK_TTL = 2000;
    private String clsRng;

    public RIPT(String name) {
        super(name);
    }

    @Override
    public void run() {
        startL();
    }

    public void startL() {
        ipRnd(getClsRng());
        doCon(getIP());
    }

    public void ipRnd(String clsRng) {
        //TODO combo box class selection
        int o1, o2, o3, o4;
        String tmpIP;

        if (clsRng.equalsIgnoreCase("A")) {
            o1 = 1 + (int) (Math.random() * 127);
        } else if (clsRng.equalsIgnoreCase("B")) {
            o1 = 128 + (int) (Math.random() * 63);
        } else if (clsRng.equalsIgnoreCase("C")) {
            o1 = 192 + (int) (Math.random() * 31);
        } else {
            o1 = 1 + (int) (Math.random() * 223);
        }

        if (o1 == 127 || o1 == 172 || o1 == 192 || o1 == 10) {
            ipRnd(clsRng);
        } else {
            o2 = 1 + (int) (Math.random() * 254);
            o3 = 1 + (int) (Math.random() * 254);
            o4 = 1 + (int) (Math.random() * 254);

            tmpIP = Integer.toString(o1) + "." + Integer.toString(o2) + "." + Integer.toString(o3) + "." + Integer.toString(o4);
            setIP(tmpIP);
        }


        //System.out.print(getIP() + " => ");
    }

    public void doCon(String ipcon) {
        try {                
             SocketAddress sockAdd = new InetSocketAddress(ipcon, 23);            
             socket = new Socket();
             socket.connect(sockAdd, getTTL());
             setConStatus(true);
             socket.close();                    
        } catch (IOException ex) {
            setConStatus(false);
            Logger.getLogger(RIPT.class.getName()).log(Level.SEVERE, null, ex);
        }
               
    }

    public void setTTL(int ttlsec) {
        SOCK_TTL = ttlsec;
    }

    public int getTTL() {
        return SOCK_TTL;
    }

    public void setIP(String rand_IP) {
        ip = rand_IP;
    }

    public String getIP() {
        return ip;
    }

    public void setConStatus(boolean cs) {
        conStatus = cs;
    }

    public boolean getConStatus() {
        return conStatus;
    }

    /**
     * @return the clsRng
     */
    public String getClsRng() {
        return clsRng;
    }

    /**
     * @param clsRng the clsRng to set
     */
    public void setClsRng(String clsRng) {
        this.clsRng = clsRng;
    }
}
