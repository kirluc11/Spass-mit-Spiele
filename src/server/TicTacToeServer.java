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

    public void addPlayer(Player player)
    {
        System.out.println("TicTacToeServer.addPlayer");
        if (allPlayer.size() >= 1)
        {
            Player player1 = allPlayer.getLast();
            allPlayer.removeLast();
            new TicTacToeThread(player1, player).start();
        } else
        {
            allPlayer.add(player);
        }
    }

    class TicTacToeThread extends Thread
    {

        Player player1;
        Player player2;

        public TicTacToeThread(Player player1, Player player2)
        {
            this.player1 = player1;
            this.player2 = player2;
        }

        @Override
        public void run()
        {
            try
            {

                System.out.println("TicTacToeServer.TicTacToeThread.run: Player names not sent");
                player1.getOos().writeObject("Player1");
                player2.getOos().writeObject("Player2");
                System.out.println("TicTacToeServer.TicTacToeThread.run: Player names sent");
                Player aktPlayer = player1;
                Player waitingPlayer = player2;
                while (true)
                {

                    Object request = aktPlayer.getOis().readObject();
                    if (request instanceof String)
                    {
                        String label = (String) request;

                        if (label.equals("###Client###Disconnect###"))
                        {
                            waitingPlayer.getOos().writeObject("##OpponentLeft##");
                            break;
                        } else if (label.equals("##GO##HOME##"))
                        {
                            waitingPlayer.getOos().writeObject("##OpponentLeft##");
                            gs.startNewClientHomeThread(aktPlayer);
                            gs.startNewClientHomeThread(waitingPlayer);
                            break;
                        } else
                        {
                            gs.log("recieved: from: Player " + aktPlayer.getNickname() + "; Label: " + label);
                            waitingPlayer.getOos().writeObject(label);

                            Player tempPlayer = aktPlayer;
                            aktPlayer = waitingPlayer;
                            waitingPlayer = tempPlayer;
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
