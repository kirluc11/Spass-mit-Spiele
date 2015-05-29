/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import client.Player;
import java.io.EOFException;
import java.io.IOException;
import java.net.SocketTimeoutException;
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
        TicTacToePlayerThread tttpt1 = new TicTacToePlayerThread(player1, player2);
        TicTacToePlayerThread tttpt2 = new TicTacToePlayerThread(player2, player1);
        tttpt1.setOtherPlayerThread(tttpt2);
        tttpt2.setOtherPlayerThread(tttpt1);
        tttpt1.start();
        tttpt2.start();
    }

    
    /**
     * Class for communication between 2 TicTacToe playing Clients
     */
    class TicTacToePlayerThread extends Thread
    {

        private Player player1;
        private Player player2;
        private TicTacToePlayerThread otherPlayerThread;

        public TicTacToePlayerThread(Player player1, Player player2)
        {
            this.player1 = player1;
            this.player2 = player2;
        }

        public void setOtherPlayerThread(TicTacToePlayerThread otherPlayerThread)
        {
            this.otherPlayerThread = otherPlayerThread;
        }

        @Override
        public void run()
        {

            System.out.println("TicTacToeServer.TicTacToePlayerThread.run: start");
            try
            {
                while (!interrupted())
                {
                    try
                    {
                        Object request = player1.getOis().readObject();
                        if (request instanceof String)
                        {
                            String label = (String) request;

                            if (label.equals("###Client###Disconnect###"))
                            {
                                player2.getOos().writeObject("##OpponentLeft##");
                                player1.getOos().writeObject("##I##Left##");
                                otherPlayerThread.interrupt();
                                break;

                            } else if (label.equals("##GO##HOME##"))
                            {
                                player2.getOos().writeObject("##OpponentLeft##");
                                player1.getOos().writeObject("##I##Left##");
                                otherPlayerThread.interrupt();
                                gs.startNewClientHomeThread(player1);
                                gs.startNewClientHomeThread(player2);
                                break;
                            } else
                            {
                                gs.log("recieved: from: Player " + player1.getNickname() + "; Label: " + label);
                                player2.getOos().writeObject(label);
                            }
                        }
                    } catch (SocketTimeoutException ex)
                    {
                        //gs.log("VierGewinntServer.VierGewinntPlayerThread.run: SocketTimeout");
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
