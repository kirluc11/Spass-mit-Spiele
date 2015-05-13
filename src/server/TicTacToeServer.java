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

    private LinkedList<ObjectOutputStream> lioos = new LinkedList<>();

    public TicTacToeServer(GameServer gs)
    {
        this.gs = gs;
    }

    public void addPlayer(ObjectInputStream ois, ObjectOutputStream oos, String nickname)
    {
        System.out.println("TicTacToeServer.addPlayer");
        new TicTacToeThread(ois, oos, nickname).start();
    }

    class TicTacToeThread extends Thread
    {

        Socket socket;
        String nickname;
        String test = "";
        ObjectInputStream ois;
        ObjectOutputStream oos;

        public TicTacToeThread(ObjectInputStream ois, ObjectOutputStream oos, String nickname)
        {
            this.ois = ois;
            this.oos = oos;
            this.nickname = nickname;
        }

        @Override
        public void run()
        {
            try
            {
                lioos.add(oos);
                while (true)
                {
                    Object request = ois.readObject();
                    if (request instanceof String)
                    {
                        String label = (String) request;

                        if (label.equals("###Client###Disconnect###"))
                        {

                            gs.log("Connection closed from: " + socket.getRemoteSocketAddress().toString());
                            break;
                        } else if (label.equals("##GO##HOME##"))
                        {
                            lioos.remove(oos);
                            gs.startNewClientHomeThread(ois, oos, nickname);
                            return;
                        } else
                        {
                            gs.log("recieved: from: Player " + nickname + "; Label: " + label);
                            for (ObjectOutputStream aktoos : lioos)
                            {
                                if (aktoos != oos)
                                {
                                    aktoos.writeObject(label);
                                }
                            }
                        }
                    }
                }

            } catch (EOFException ex)
            {
            } catch (IOException ex)
            {
                gs.log("Communication failure: " + ex.toString());
            } catch (ClassNotFoundException ex)
            {
                gs.log("Communication failure: " + ex.toString());
            }
            

        }
    }

}
