/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import client.Player;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marcel, Lukas
 */
public class VierGewinntServer
{

    private GameServer gs;
    private LinkedList<Player> waitingList = new LinkedList<>();

    public VierGewinntServer(GameServer gs)
    {
        this.gs = gs;
    }

    /**
     * If enought player are connected
     * a game will be created.
     * Else adds player to waitingList.
     * @param player
     * @throws IOException 
     */
    public void addPlayer(Player player) throws IOException
    {
        System.out.println("VierGewinntServer.addPlayer: ListLenght: "+waitingList.size());
        if (waitingList.size() >= 1)
        {
            Player player1 = waitingList.getLast();
            waitingList.removeLast();
            startNewGame(player, player1);
        } else
        {
            waitingList.add(player);
        }
    }

    /**
     * If two player are connected this method puts them into one game and
     * starts for each player one VierGewinntPlayerThread
     * @param player1
     * @param player2
     * @throws IOException 
     */
    public void startNewGame(Player player1, Player player2) throws IOException
    {
        System.out.println("VierGewinntServer.startNewGame: Player names not sent");
        player1.getOos().writeObject("Player1##"+player2.getNickname());
        player2.getOos().writeObject("Player2##"+player1.getNickname());
        System.out.println("VierGewinntServer.startNewGame:"+player1.getNickname()+" VS "+player2.getNickname());
        VierGewinntPlayerThread vgpt1 = new VierGewinntPlayerThread(player1, player2);
        VierGewinntPlayerThread vgpt2 = new VierGewinntPlayerThread(player2, player1);
        vgpt1.setOtherPlayerThread(vgpt2);
        vgpt2.setOtherPlayerThread(vgpt1);
        vgpt1.start();
        vgpt2.start();
    }

    
    /**
     * Class for communication between 2 VierGewinnt playing Clients
     */
    class VierGewinntPlayerThread extends Thread
    {

        Player player1;
        Player player2;
        VierGewinntPlayerThread otherPlayerThread;

        public VierGewinntPlayerThread(Player player1, Player player2)
        {
            this.player1 = player1;
            this.player2 = player2;
        }

        /**
         * Sets the other player thread to be able to interrupt it if player leaves
         * @param otherPlayerThread 
         */
        public void setOtherPlayerThread(VierGewinntPlayerThread otherPlayerThread)
        {
            this.otherPlayerThread = otherPlayerThread;
        }

        
        /**
         * Is reading information from one player and transmits them to other player
         */
        @Override
        public void run()
        {
            System.out.println("VierGewinntServer.VierGewinntPlayerThread.run: start");
            try
            {
                while (!interrupted())
                {
                    System.out.println("VierGewinntServer.VierGewinntPlayerThread.run: in not interrepted");
                    try
                    {
                    Object request = player1.getOis().readObject();
                    if (request instanceof String)
                    {
                        String text = (String) request;

                        if (text.equals("###Client###Disconnect###"))
                        {
                            player2.getOos().writeObject("##OpponentLeft##");
                            player1.getOos().writeObject("##I##Left##");
                            otherPlayerThread.interrupt();
                            break;

                        } else if (text.equals("##GO##HOME##"))
                        {
                            player2.getOos().writeObject("##OpponentLeft##");
                            player1.getOos().writeObject("##I##Left##");
                            otherPlayerThread.interrupt();
                            gs.startNewClientHomeThread(player1);
                            gs.startNewClientHomeThread(player2);
                            break;
                        }
                    } else if (request instanceof Integer)
                    {
                        int column = (int) request;
                        gs.log("recieved: from: Player " + player1.getNickname() + "; column: " + column);
                        player2.getOos().writeObject(column);
                    }
                    }catch(SocketTimeoutException ex)
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
