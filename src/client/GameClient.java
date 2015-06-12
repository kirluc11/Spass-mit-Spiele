/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import games.tictactoe.bl.TicTacToeGewinnabfrage;
import games.tictactoe.gui.TicTacToePanel;
import gui.WaitingForOpponentDLG;
import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import vierGewinnt.gui.VierGewinntPanel;

/**
 *
 * @author Marcel, Lukas
 */
public class GameClient
{

    private int PORTNR;
    private static InetAddress address;
    private Socket socket;
    private ObjectOutputStream oos = null;
    private ObjectInputStream ois = null;
    private String nickname;

    /**
     * Sets port user wants to connect to
     * @param PORTNR 
     */
    public void setPORTNR(int PORTNR)
    {
        this.PORTNR = PORTNR;
    }

    /**
     * Sets address client wants to connect to
     * @param inetAddress
     * @throws UnknownHostException 
     */
    public void setAddress(String inetAddress) throws UnknownHostException
    {
        address = InetAddress.getByName(inetAddress);
    }

    /**
     * Sets nickname of the client and writes it to the server
     * @param nickname
     * @throws IOException 
     */
    public void setNickname(String nickname) throws IOException
    {
        this.nickname = nickname;
        oos.writeObject(nickname);
    }


    /**
     * Connects client with server if he isn't connected
     * @throws IOException 
     */
    public void startClient() throws IOException
    {
        if (!isConnected())
        {
            socket = new Socket(address, PORTNR);
            OutputStream os = socket.getOutputStream();
            oos = new ObjectOutputStream(os);
            ois = new ObjectInputStream(socket.getInputStream());
        }

    }

    /**
     * Disconnects client from server if he is connected
     * @throws IOException 
     */
    public void stopClient() throws IOException
    {
        if (isConnected())
        {
            oos.writeObject("###Client###Disconnect###");
            oos.close();
            ois.close();
            socket.close();
        }
    }

    /**
     * Returns true if client is connected, false if not
     * @return 
     */
    public boolean isConnected()
    {
        if (socket != null && !socket.isClosed())
        {
            return true;
        }
        return false;
    }

    /**
     * Sends Object to the server
     * @param request
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public void sendObject(Object request) throws IOException, ClassNotFoundException
    {
        oos.writeObject(request);
    }

    /**
     * Creates a new ClientTicTacToeThread
     * @param labels
     * @param tttg
     * @param tttp 
     */
    public void newTicTacToeThread(LinkedList<JLabel> labels, TicTacToeGewinnabfrage tttg, TicTacToePanel tttp)
    {
        WaitingForOpponentDLG waitingDLG = new WaitingForOpponentDLG(null, true);
        ClientTicTacToeThread ctttt = new ClientTicTacToeThread(labels, tttg, tttp, waitingDLG);
        ctttt.start();
        waitingDLG.setVisible(true);
    }

    /**
     * Class used for online TicTacToe games
     */
    class ClientTicTacToeThread extends Thread
    {

        LinkedList<JLabel> labels;
        TicTacToeGewinnabfrage tttg;
        TicTacToePanel tttp;
        boolean turn;
        Color labelColor;
        WaitingForOpponentDLG waitingDLG;
        

        public ClientTicTacToeThread(LinkedList<JLabel> labels, TicTacToeGewinnabfrage tttg, TicTacToePanel tttp,WaitingForOpponentDLG waitingDLG)
        {
            this.labels = labels;
            this.tttg = tttg;
            this.tttp = tttp;
            labelColor = tttp.getSpieler1();
            this.waitingDLG=waitingDLG;
        }

        /**
         * Is waiting for information from server and react depending on the information
         */
        @Override
        public void run()
        {
            try
            {
                System.out.println("GameClient.ClientTicTacToeThread.run: Anfang");
                Object response = ois.readObject();
                waitingDLG.dispose();
                System.out.println("GameClient.ClientTicTacToeThread.run: Player:"+response);
                if(response.equals("Player1"))
                {
                    tttp.setMyTurn(true);
                    JOptionPane.showMessageDialog(tttp, "You are Player 1");
                }else
                {
                    tttp.setMyTurn(false);
                    JOptionPane.showMessageDialog(tttp, "You are Player 2");
                }
                while (!interrupted())
                {
                    response = ois.readObject();

                    if (response instanceof String)
                    {
                        
                        String aktlabel = (String) response;
                        if(aktlabel.equals("##OpponentLeft##"))
                        {
                            JOptionPane.showMessageDialog(tttp, "Opponent has left the Game");
                            tttp.setMyTurn(false);
                            tttp.changeLabelState(false);
                            break;
                        }else if(aktlabel.equals("##I##Left##"))
                        {
                            tttp.setMyTurn(false);
                            break;
                        }
                        for (JLabel label : labels)
                        {
                            if (label.getName().equals(aktlabel))
                            {
                                label.setBackground(labelColor);
                                label.setText("O");
                                tttp.setMyTurn(true);
                            }
                        }
                    }
                }
            } catch (IOException ex)
            {

            } catch (ClassNotFoundException ex)
            {
                System.out.println(ex.toString());
            }

        }
    }
    
    /**
     * Creates a new ClientVierGewinntThread
     * @param vgp 
     */
    public void newVierGewinntThread(VierGewinntPanel vgp)
    {
        System.out.println("GameClient.newVierGewinntThread: start");
        WaitingForOpponentDLG waitingDLG = new WaitingForOpponentDLG(null, true);
       new ClientVierGewinntThread(vgp,waitingDLG).start();
        waitingDLG.setVisible(true);
    }
    /**
     * Class used for online VierGewinnt games
     */
    class ClientVierGewinntThread extends Thread
    {
        
        private VierGewinntPanel vgp;
        private WaitingForOpponentDLG waitingDLG;
        
        public ClientVierGewinntThread(VierGewinntPanel vgp, WaitingForOpponentDLG waitingDLG)
        {
            this.vgp = vgp;
            this.waitingDLG = waitingDLG;
        }
        
        /**
         * Is waiting for information from server and react depending on the information
         */
        @Override
        public void run()
        {
            try
            {
                System.out.println("GameClient.ClientVierGewinntThread.run: Anfang");
                Object response = ois.readObject();
                waitingDLG.dispose();
                System.out.println("GameClient.ClientVierGewinntThread.run: Player:"+response);
                if(response.equals("Player1"))
                {
                    vgp.setTurn(true);
                    JOptionPane.showMessageDialog(vgp, "You are Player 1");
                }else
                {
                    vgp.setTurn(false);
                    JOptionPane.showMessageDialog(vgp, "You are Player 2");
                }
                
                while (true)
                {
                    System.out.println("GameClient.ClientVierGewinntThread.run: WhileTrue beginn");
                    response = ois.readObject();

                    if (response instanceof String)
                    {
                        String aktlabel = (String) response;
                        if(aktlabel.equals("##OpponentLeft##"))
                        {
                            vgp.setTurn(false);
                            JOptionPane.showMessageDialog(vgp, "Opponent has left the Game");
                            break;
                        }else if(aktlabel.equals("##I##Left##"))
                        {
                            vgp.setTurn(false);
                            break;
                        }
                    }else if(response instanceof Integer)
                    {
                       int column = (int) response;
                       vgp.insertStone(column);
                       vgp.setTurn(true);
                    }
                }
            } catch (IOException ex)
            {

            } catch (ClassNotFoundException ex)
            {
                System.out.println(ex.toString());
            }
        }

    }
    
}
