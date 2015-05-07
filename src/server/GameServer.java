/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

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
 * @author user
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

    public void setPORTNR(int PORTNR)
    {
        this.PORTNR = PORTNR;
    }

    public GameServer(JTextComponent logArea) throws IOException
    {
        this.logArea = logArea;
        ttts = new TicTacToeServer(this);
    }

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

        @Override
        public void run()
        {
            log("Server started on port: " + PORTNR);
            while (!interrupted())
            {
                try
                {
                    Socket socket = server.accept();

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
    
    
    public void startNewClientHomeThread(Socket socket,String nickname)
    {
        new HomeThread(socket, nickname);
    }
    

    class HomeThread extends Thread
    {

        Socket socket;
        boolean inGame = false;
        String nickname = "";

        public HomeThread(Socket socket)
        {
            this.socket = socket;
        }

        public HomeThread(Socket socket, String nickname)
        {
            this.nickname = nickname;
            this.socket = socket;
        }

        @Override
        public void run()
        {

            InputStream is = null;
            OutputStream os = null;
            try
            {
                is = socket.getInputStream();
                os = socket.getOutputStream();
                ObjectInputStream ois = new ObjectInputStream(is);
                ObjectOutputStream oos = new ObjectOutputStream(os);
                if (nickname.isEmpty())
                {
                    Object request = ois.readObject();
                    nickname = (String) request;
                    nicknames.add(nickname);
                    System.out.println("" + nickname);
                }

                Object request = ois.readObject();
                if (request instanceof String)
                {
                    String text = (String) request;
                    System.out.println(""+text);
                    if (text.equals("TicTacToe"))
                    {
                        ttts.addPlayer(socket, nickname);
                    }
                    
                    if (text.equals("###Client###Disconnect###"))
                    {
                        //break;
                    }
                }

            } catch (EOFException ex)
            {

            } catch (IOException ex)
            {
                log("Communication failure: " + ex.toString());
            } catch (ClassNotFoundException ex)
            {
                Logger.getLogger(GameServer.class.getName()).log(Level.SEVERE, null, ex);
            } finally
            {
                try
                {
                    is.close();
                    os.close();

                } catch (IOException ex)
                {
                    log("Communication failure: " + ex.toString());
                }
            }

        }
    }

}