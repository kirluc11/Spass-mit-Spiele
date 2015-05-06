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
import java.util.HashMap;
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
    private HashMap<String, Socket> player = new HashMap<>();

    public TicTacToeServer(GameServer gs)
    {
        this.gs = gs;
    }

    public void addPlayer(Socket socket, String nickname)
    {
        player.put(nickname, socket);
        new TicTacToeThread(socket, nickname).start();
    }

    public void removePlayer(String nickname)
    {
        player.remove(nickname);
    }

    class TicTacToeThread extends Thread
    {

        Socket socket;
        String nickname;
        String test = "";

        public TicTacToeThread(Socket socket, String nickname)
        {
            this.socket = socket;
            this.nickname = nickname;
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
                System.out.println("new TicTacToeThread");
                while (true)
                {
                    Object request = ois.readObject();
                    if (request instanceof String)
                    {
                        String nickname = (String) request;
                        if (nickname.equals("###Client###Disconnect###"))
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
            }

            gs.startNewClientHomeThread(socket, nickname);

        }
    }

}
