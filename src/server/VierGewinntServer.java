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

    public void addPlayer(Player player)
    {
        System.out.println("VierGewinntServer.addPlayer");
        if (allPlayer.size() >= 1)
        {
            Player player1 = allPlayer.getLast();
            allPlayer.removeLast();
            new VierGewinntThread(player1, player).start();
        } else
        {
            allPlayer.add(player);
        }
    }

    class VierGewinntThread extends Thread
    {
        
        Player player1;
        Player player2;

        public VierGewinntThread(Player player1, Player player2)
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
            } catch (IOException ex)
            {
                Logger.getLogger(VierGewinntServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }
}
