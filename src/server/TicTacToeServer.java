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
    private LinkedList<Player> allPlayer = new LinkedList<>();

    public TicTacToeServer(GameServer gs)
    {
        this.gs = gs;
    }

    public void addPlayer(Player player) throws IOException
    {
        System.out.println("TicTacToeServer.addPlayer");
        if (allPlayer.size() >= 1)
        {
            Player player1 = allPlayer.getLast();
            allPlayer.removeLast();
            startNewGame(player, player1);
        } else
        {
            allPlayer.add(player);
        }
    }

    public void startNewGame(Player player1, Player player2) throws IOException
    {
        System.out.println("TicTacToeServer.startNewGame: Player names not sent");
        player1.getOos().writeObject("Player1");
        player2.getOos().writeObject("Player2");
        System.out.println("TicTacToeServer.startNewGame: Player names sent");
        new TicTacToePlayerThread(player1, player2).start();
        new TicTacToePlayerThread(player2, player1).start();
    }

    class TicTacToePlayerThread extends Thread
    {

        Player player1;
        Player player2;

        public TicTacToePlayerThread(Player player1, Player player2)
        {
            this.player1 = player1;
            this.player2 = player2;
        }

        @Override
        public void run()
        {

            System.out.println("TicTacToeServer.TicTacToePlayerThread.run: start");
            try
            {
                while (true)
                {

                    Object request = player1.getOis().readObject();
                    if (request instanceof String)
                    {
                        String label = (String) request;

                        if (label.equals("###Client###Disconnect###"))
                        {

                            player2.getOos().writeObject("##OpponentLeft##");
                            break;

                        } else if (label.equals("##GO##HOME##"))
                        {
                            player2.getOos().writeObject("##OpponentLeft##");
                            gs.startNewClientHomeThread(player1);
                            gs.startNewClientHomeThread(player2);
                            break;
                        } else
                        {
                            gs.log("recieved: from: Player " + player1.getNickname() + "; Label: " + label);
                            player2.getOos().writeObject(label);
                        }
                    }
                }
            } catch (IOException ex)
            {
                Logger.getLogger(TicTacToeServer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex)
            {
                Logger.getLogger(TicTacToeServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
