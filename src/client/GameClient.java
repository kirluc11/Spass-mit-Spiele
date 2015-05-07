/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;

/**
 *
 * @author user
 */
public class GameClient
{
    private int PORTNR;
    private static InetAddress address;
    private Socket socket;
    private ObjectOutputStream oos = null;
    private ObjectInputStream ois = null;
    private String nickname;

    public void setPORTNR(int PORTNR)
    {
        this.PORTNR = PORTNR;
    }

    public void setAddress(String inetAdress) throws UnknownHostException
    {
        address = InetAddress.getLocalHost();
    }

    public void setNickname(String nickname) throws IOException
    {
        this.nickname = nickname;
        oos.writeObject(nickname);
    }
    
   

    public void startClient() throws IOException
    {
        if (socket == null || socket.isClosed())
        {
            socket = new Socket(address, PORTNR);
            OutputStream os = socket.getOutputStream();
            oos = new ObjectOutputStream(os);
            ois = new ObjectInputStream(socket.getInputStream());
        }

    }

    public void stopClient() throws IOException
    {
        if (socket != null && !socket.isClosed())
        {
            oos.writeObject("###Client###Disconnect###");
            oos.close();
            ois.close();
            socket.close();
        }
    }
    
    public boolean isConnected()
    {
        if (socket != null && !socket.isClosed())
        {
            return true;
        }
        return false;
    }

    public void sendObject(Object request) throws IOException, ClassNotFoundException
    {
        oos.writeObject(request);
    }
    
    public void newTicTacToeThread(LinkedList<JLabel> labels)
    {
        new ClientTicTacToeThread(labels);
    }
    
    
    class ClientTicTacToeThread extends Thread
    {
        LinkedList<JLabel> labels;
        public ClientTicTacToeThread(LinkedList<JLabel> labels)
        {
            this.labels=labels;
        }
        
        
        @Override
        public void run()
        {
            super.run(); //To change body of generated methods, choose Tools | Templates.
        }
    }
}