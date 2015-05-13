/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import client.Player;
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
    private LinkedList<Player> allPlayer = new LinkedList<>();

    public TicTacToeServer(GameServer gs)
    {
        this.gs = gs;
    }

    public void addPlayer(Player player)
    {
        System.out.println("TicTacToeServer.addPlayer");
        if(allPlayer.size()>=1)
        {
            Player player1 = allPlayer.getLast();
            allPlayer.removeLast();
            new TicTacToeThread(player1, player).start();
        }else
        {
            allPlayer.add(player);
        }
    }

    class TicTacToeThread extends Thread
    {

        
        Player player1;
        Player player2;
        

        public TicTacToeThread(Player player1,Player player2)
        {
            this.player1=player1;
            this.player2=player2;
        }

        @Override
        public void run()
        {
            try
            {
                while (true)
                {
                    System.out.println("TicTacToeServer: start Thread");
                    Object request = player1.getOis().readObject();
                    if (request instanceof String)
                    {
                        String label = (String) request;

                        if (label.equals("###Client###Disconnect###"))
                        {

                            break;
                        } else if (label.equals("##GO##HOME##"))
                        {
                            gs.startNewClientHomeThread(player1);
                            return;
                        } else
                        {
                            gs.log("recieved: from: Player " + player1.getNickname() + "; Label: " + label);
                            player2.getOos().writeObject(label);
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
