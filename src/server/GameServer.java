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
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Marcel
 */
public class GameServer
{

    private int PORTNR;
    private JTextComponent logArea = null;
    private ServerSocket server;
    private ServerThread st = null;
    private LinkedList<ObjectOutputStream> writerList = new LinkedList<ObjectOutputStream>();
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss'>'");
    private LinkedList<String> nicknames = new LinkedList<>();
    private TicTacToeServer ttts;
    private VierGewinntServer vgs;

    public void setPORTNR(int PORTNR)
    {
        this.PORTNR = PORTNR;
    }

    /**
     * logArea is used to write on it
     * @param logArea
     * @throws IOException 
     */
    public GameServer(JTextComponent logArea) throws IOException
    {
        this.logArea = logArea;
        ttts = new TicTacToeServer(this);
        vgs = new VierGewinntServer(this);
    }

    /**
     * Starts a new Server if server isn't running
     */
    public void startServer()
    {
        if (st == null || !st.isAlive())
        {
            st = new ServerThread();
            try
            {
                server = new ServerSocket(PORTNR);
                server.setSoTimeout(500);

                st.start();
            } catch (IOException ex)
            {
                log("Couldn't start Server Port is already in use");
                System.out.println("Server-Exception: " + ex.toString());
            }

        }

    }
    /**
     * Stops the Server if server is running
     */
    public void stopServer() throws IOException
    {

        if (st != null && st.isAlive())
        {
            for (ObjectOutputStream writerList1 : writerList)
            {

                writerList1.writeObject("###Sever###stopped###");
            }
            st.interrupt();
        }

    }

    /**
     * Writes the logText into the logArea or into console
     * @param logText 
     */
    public void log(String logText)
    {
        Date date = new Date();
        if (logArea == null)
        {
            System.out.println(sdf.format(date) + logText);
        } else
        {
            synchronized (logArea)
            {
                logArea.setText(logArea.getText()
                        + System.lineSeparator()
                        + sdf.format(date)
                        + logText);
            }
        }
    }

    
    class ServerThread extends Thread
    {

        public ServerThread()
        {
            this.setPriority(Thread.MIN_PRIORITY + 2);
        }

        /**
         * Is waiting for clients to connect
         * Adds client to HomeThread if connect
         */
        @Override
        public void run()
        {
            log("Server started on port: " + PORTNR);
            while (!interrupted())
            {
                try
                {
                    Socket socket = server.accept();
                    socket.setSoTimeout(2000);
                    log("Connection established with: " + socket.getRemoteSocketAddress().toString());

                    new HomeThread(socket).start();

                } catch (SocketTimeoutException ex)
                {
                    //log("Timeout from accept");
                } catch (Exception ex)
                {
                    log("Server-Exception: " + ex.toString());
                }

            }
            try
            {
                server.close();
            } catch (Exception ex)
            {
                log("Server-Exception: " + ex.toString());
            }
            log("Server closed");

        }

    }

    /**
     * Starts a new ClientHomeThread uses player to start thread.
     * @param player 
     */
    public void startNewClientHomeThread(Player player)
    {
        new HomeThread(player.getOis(), player.getOos(), player.getNickname()).start();
    }

    /**
     * Thread which is running when player is connected but isn't playing an online game.
     */
    class HomeThread extends Thread
    {

        Socket socket;
        boolean inGame = false;
        String nickname = "";
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;

        /**
         * For first connect from player.
         * @param socket 
         */
        public HomeThread(Socket socket)
        {
            this.socket = socket;
        }

        /**
         * For clients who returned from a game.
         * @param ois
         * @param oos
         * @param nickname 
         */
        public HomeThread(ObjectInputStream ois, ObjectOutputStream oos, String nickname)
        {
            this.nickname = nickname;
            this.ois = ois;
            this.oos = oos;
        }

        /**
         * Is waiting for response from client to add him to selected gameserver.
         */
        @Override
        public void run()
        {
            System.out.println("GameServer.HomeThread.run: Anfang");
            InputStream is = null;
            OutputStream os = null;
            try
            {
                if (ois == null)
                {
                    is = socket.getInputStream();
                    ois = new ObjectInputStream(is);
                }
                if (oos == null)
                {
                    os = socket.getOutputStream();
                    oos = new ObjectOutputStream(os);
                }
                //If first connect nickname is empty and needs to be set 
                if (nickname.isEmpty())
                {
                    Object request = ois.readObject();
                    nickname = (String) request;
                    nicknames.add(nickname);
                    System.out.println("GameServer.HomeThread.run: Nickname=" + nickname);
                }

                while (true)
                {
                    try
                    {
                        Object request = ois.readObject();
                        //Checks if client is pressing home but is already in Home
                        while (request.equals("##GO##HOME##"))
                        {
                            System.out.println("GOHOME");
                            request = ois.readObject();
                        }
                        if (request instanceof String)
                        {
                            String text = (String) request;
                            System.out.println("GameServer.HomeThread.run: Text=" + text);
                            //Puts client to game he selected
                            if (text.equals("TicTacToe"))
                            {
                                ttts.addPlayer(new Player(oos, ois, nickname));
                                break;
                            } else if (text.equals("VierGewinnt"))
                            {
                                vgs.addPlayer(new Player(oos, ois, nickname));
                                break;
                            }
                            if (text.equals("###Client###Disconnect###"))
                            {
                                break;
                            }
                        }
                    } catch (SocketTimeoutException ex)
                    {
                        //log("Timeout from GameChoose");
                    }
                }

            } catch (EOFException ex)
            {

            } catch (IOException ex)
            {
                log("Communication failure: " + ex.toString());
            } catch (ClassNotFoundException ex)
            {
                log("Communication failure: " + ex.toString());
            }

        }
    }

}
