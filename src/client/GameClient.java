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

/**
 *
 * @author user
 */
public class GameClient
{

    private int PORTNR;
    private static InetAddress address;
    private Socket socket;
    private ObjectOutputStream oos = null;
    private ObjectInputStream ois = null;
    private String nickname;

    public void setPORTNR(int PORTNR)
    {
        this.PORTNR = PORTNR;
    }

    public void setAddress(String inetAdress) throws UnknownHostException
    {
        address = InetAddress.getByName(inetAdress);
    }

    public void setNickname(String nickname) throws IOException
    {
        this.nickname = nickname;
        oos.writeObject(nickname);
    }

    public void startClient() throws IOException
    {
        if (socket == null || socket.isClosed())
        {
            socket = new Socket(address, PORTNR);
            OutputStream os = socket.getOutputStream();
            oos = new ObjectOutputStream(os);
            ois = new ObjectInputStream(socket.getInputStream());
        }

    }

    public void stopClient() throws IOException
    {
        if (socket != null && !socket.isClosed())
        {
            oos.writeObject("###Client###Disconnect###");
            oos.close();
            ois.close();
            socket.close();
        }
    }

    public boolean isConnected()
    {
        if (socket != null && !socket.isClosed())
        {
            return true;
        }
        return false;
    }

    public void sendObject(Object request) throws IOException, ClassNotFoundException
    {
        oos.writeObject(request);
    }

    public void newTicTacToeThread(LinkedList<JLabel> labels, TicTacToeGewinnabfrage tttg, TicTacToePanel tttp)
    {
        WaitingForOpponentDLG waitingDLG = new WaitingForOpponentDLG(null, true);
        ClientTicTacToeThread ctttt = new ClientTicTacToeThread(labels, tttg, tttp, waitingDLG);
        ctttt.start();
        waitingDLG.setVisible(true);
    }

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
                            tttp.changeLabelState(false);
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
    
    
    public void newVierGewinntThread()
    {
        
    }
    
    class ClientVierGewinntThread extends Thread
    {
        @Override
        public void run()
        {
            super.run(); //To change body of generated methods, choose Tools | Templates.
        }

    }
    
}
