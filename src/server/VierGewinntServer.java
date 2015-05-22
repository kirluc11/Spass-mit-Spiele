/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import client.Player;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class VierGewinntServer
{

    private GameServer gs;
    private LinkedList<Player> allPlayer = new LinkedList<>();

    public VierGewinntServer(GameServer gs)
    {
        this.gs = gs;
    }

    public void addPlayer(Player player) throws IOException
    {
        System.out.println("VierGewinntServer.addPlayer");
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
        System.out.println("VierGewinntServer.startNewGame: Player names not sent");
        player1.getOos().writeObject("Player1");
        player2.getOos().writeObject("Player2");
        System.out.println("VierGewinntServer.startNewGame: Player names sent");
        new VierGewinntPlayerThread(player1, player2).start();
        new VierGewinntPlayerThread(player2, player1).start();
    }

    class VierGewinntPlayerThread extends Thread
    {

        Player player1;
        Player player2;

        public VierGewinntPlayerThread(Player player1, Player player2)
        {
            this.player1 = player1;
            this.player2 = player2;
        }

        @Override
        public void run()
        {
            System.out.println("VierGewinntServer.VierGewinntPlayerThread.run: start");
            try
            {
                while (true)
                {

                    Object request = player1.getOis().readObject();
                    if (request instanceof String)
                    {
                        String text = (String) request;

                        if (text.equals("###Client###Disconnect###"))
                        {

                            player2.getOos().writeObject("##OpponentLeft##");
                            break;

                        } else if (text.equals("##GO##HOME##"))
                        {
                            player2.getOos().writeObject("##OpponentLeft##");
                            gs.startNewClientHomeThread(player1);
                            gs.startNewClientHomeThread(player2);
                            break;
                        }
                    } else if (request instanceof Integer)
                    {
                        //asdddddddddddddddddd
                        int column = (int) request;
                        gs.log("recieved: from: Player " + player1.getNickname() + "; column: " + column);
                        player2.getOos().writeObject(column);
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
