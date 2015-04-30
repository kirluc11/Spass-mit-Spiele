/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class TicTacToeServer
{
    
    private GameServer gs;
    private LinkedList<Socket> player = new LinkedList<>();
    
    public TicTacToeServer(GameServer gs)
    {
        this.gs=gs;
    }
    
    public void addPlayer(Socket socket)
    {
        player.add(socket);
    }
    
    public void removePlayer(Socket socket)
    {
        player.remove(socket);
    }
    
    
    
     class TicTacToeThread extends Thread
    {

        Socket socket;
        String test = "";

        public TicTacToeThread(Socket socket)
        {
            this.socket = socket;
        }

        @Override
        public void run()
        {

            InputStream is = null;
            OutputStream os = null;
            try
            {
                is = socket.getInputStream();
                os = socket.getOutputStream();
                ObjectInputStream ois = new ObjectInputStream(is);
                ObjectOutputStream oos = new ObjectOutputStream(os);
                while (true)
                {
                    Object request = ois.readObject();
                    if (request instanceof String)
                    {
                        String nickname = (String) request;
                        if(nickname.equals("###Client###Disconnect###"))
                        {
                            break;
                        }
                        test += nickname;
                    }
                }
            } catch (EOFException ex)
            {

            } catch (IOException ex)
            {
                gs.log("Communication failure: " + ex.toString());
            } catch (ClassNotFoundException ex)
            {
                Logger.getLogger(GameServer.class.getName()).log(Level.SEVERE, null, ex);
            } finally
            {
                try
                {
                    is.close();
                    os.close();
                    socket.close();

                } catch (IOException ex)
                {
                    gs.log("Communication failure: " + ex.toString());
                }
            }

        }
    }
   
}
