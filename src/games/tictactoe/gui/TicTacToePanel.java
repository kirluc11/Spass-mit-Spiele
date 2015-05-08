/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package games.tictactoe.gui;

import client.GameClient;
import games.tictactoe.bl.TicTacToeGewinnabfrage;
import games.tictactoe.bl.TicTacToeKI;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Lukas
 */
public class TicTacToePanel extends JPanel
{

    private LinkedList<JLabel> labels = new LinkedList<>();
    private Color spieler1 = Color.yellow;
    private Color spieler2 = Color.red;
    private TicTacToeGewinnabfrage tttg;
    private TicTacToeKI tttki = new TicTacToeKI();
    //GameMode: 0=Single,1=Multiplayer, 2=Online
    private int gameMode = 0;
    private int player = 1;
    private int beginner = 1;
    private GameClient gc;
    private boolean myTurn = true;

    private boolean gameOver;

    public Color getSpieler1()
    {
        return spieler1;
    }

    public TicTacToePanel(GameClient gc)
    {
        this.initComponents();
        this.gc = gc;
        tttg = new TicTacToeGewinnabfrage(labels);
        if (gc.isConnected())
        {
            NumberOfPlayerOnlineDLG onlinedlg = new NumberOfPlayerOnlineDLG(null, true);
            if (onlinedlg.isOk())
            {
                gameMode = onlinedlg.getGameMode();
                if (gameMode == 2)
                {
                    try
                    {
                        gc.sendObject("TicTacToe");
                        gc.newTicTacToeThread(labels, tttg, this);
                    } catch (IOException ex)
                    {
                        Logger.getLogger(TicTacToePanel.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex)
                    {
                        Logger.getLogger(TicTacToePanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } else
        {
            NumberOfPlayerDLG dlg = new NumberOfPlayerDLG(null, true);
            if (dlg.isOk())
            {
                gameMode = dlg.getGameMode();
            }
        }
    }

    public boolean isMyTurn()
    {
        return myTurn;
    }

    public void setMyTurn(boolean myTurn)
    {
        this.myTurn = myTurn;
        if (tttg.isOver())
        {
            String gewinner = tttg.getSieger() == 0 ? "X" : "O";
            JOptionPane.showMessageDialog(this, String.format("%s hat gewonnen!", gewinner));
            beginner *= -1;

            changeLabelState(false);
            gameOver = true;

        } else if (tttg.isUnendschieden())
        {
            JOptionPane.showMessageDialog(this, String.format("Unendschieden"));
            beginner *= -1;

            changeLabelState(false);
            gameOver = true;

        }
    }

    private void initComponents()
    {
        this.setLayout(new GridLayout(3, 3, 1, 1));

        this.setBorder(new TitledBorder(new EmptyBorder(5, 0, 0, 0), "Tic Tac Toe", TitledBorder.CENTER, TitledBorder.ABOVE_TOP));

        popupmenu = new JPopupMenu("Game");
        miRestartSinglePlayer = new JMenuItem("New Single Player Game");
        miRestartOfflineMultiplayer = new JMenuItem("New Offline Multiplayer Game");

        miRestartSinglePlayer.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                gameMode = 0;
                restart();
            }
        });

        miRestartOfflineMultiplayer.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                gameMode = 1;
                restart();
            }
        });

        popupmenu.add(miRestartSinglePlayer);
        popupmenu.add(miRestartOfflineMultiplayer);

        for (int i = 0; i < 9; i++)
        {
            JLabel lb = new JLabel();
            lb.setOpaque(true);
            lb.setBackground(Color.black);
            lb.setFont(new Font("Courier New", Font.BOLD, 75));
            lb.setHorizontalAlignment(JLabel.CENTER);
            lb.setComponentPopupMenu(popupmenu);
            lb.addMouseListener(new MouseAdapter()
            {
                @Override
                public void mouseClicked(MouseEvent e)
                {
                    try
                    {
                        onMouseClicked(e);
                    } catch (IOException ex)
                    {
                        Logger.getLogger(TicTacToePanel.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex)
                    {
                        Logger.getLogger(TicTacToePanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            lb.setName("" + i);
            this.add(lb);
            labels.add(lb);
        }
    }

    private void changeLabelState(boolean b)
    {
        for (JLabel label : labels)
        {
            label.setEnabled(b);
        }
    }

    private void onMouseClicked(MouseEvent e) throws IOException, ClassNotFoundException
    {
        if (!gameOver)
        {
            if (gameMode == 0)
            {
                Object obj = e.getSource();
                if (obj instanceof JLabel)
                {
                    JLabel lb = (JLabel) obj;
                    if (lb.getBackground().equals(Color.black))
                    {
                        lb.setBackground(spieler2);
                        lb.setText("X");

                        if (tttg.isOver())
                        {
                            String gewinner = tttg.getSieger() == 0 ? "X" : "O";
                            JOptionPane.showMessageDialog(this, String.format("%s hat gewonnen!", gewinner));
                            beginner *= -1;

                            changeLabelState(false);
                            gameOver = true;

                        } else
                        {
                            if (tttg.isUnendschieden())
                            {
                                JOptionPane.showMessageDialog(this, String.format("Unendschieden"));
                                beginner *= -1;

                                changeLabelState(false);
                                gameOver = true;

                            } else
                            {
                                player *= -1;
                                lb = tttki.nextStep(labels);
                                lb.setBackground(spieler1);
                                lb.setText("O");
                                if (tttg.isOver())
                                {
                                    String gewinner = tttg.getSieger() == 0 ? "X" : "O";
                                    JOptionPane.showMessageDialog(this, String.format("%s hat gewonnen!", gewinner));
                                    beginner *= -1;

                                    changeLabelState(false);
                                    gameOver = true;

                                } else
                                {
                                    if (tttg.isUnendschieden())
                                    {
                                        JOptionPane.showMessageDialog(this, String.format("Unendschieden"));
                                        beginner *= -1;

                                        changeLabelState(false);
                                        gameOver = true;

                                    }
                                }
                            }
                            player *= -1;
                        }
                    }
                }
            } else if (gameMode == 1)
            {
                Object obj = e.getSource();
                if (obj instanceof JLabel)
                {
                    JLabel lb = (JLabel) obj;
                    if (lb.getBackground().equals(Color.black))
                    {
                        if (player == 1)
                        {
                            lb.setBackground(spieler2);
                            lb.setText("X");
                        } else
                        {
                            lb.setBackground(spieler1);
                            lb.setText("O");
                        }

                        if (tttg.isOver())
                        {
                            String gewinner = tttg.getSieger() == 0 ? "X" : "Y";
                            JOptionPane.showMessageDialog(this, String.format("%s hat gewonnen!", gewinner));
                            beginner *= -1;

                            changeLabelState(false);
                            gameOver = true;

                        } else
                        {
                            if (tttg.isUnendschieden())
                            {
                                JOptionPane.showMessageDialog(this, String.format("Unendschieden"));
                                beginner *= -1;

                                changeLabelState(false);
                                gameOver = true;

                            }
                        }
                        player *= -1;
                    }
                }
            } else if (gameMode == 2)
            {
                if (myTurn)
                {
                    Object obj = e.getSource();
                    JLabel lb = (JLabel) obj;
                    if (lb.getBackground().equals(Color.black))
                    {

                        lb.setBackground(spieler2);
                        lb.setText("X");
                        System.out.println("TicTacToePanel.onMouseClicked: ClickedLb" + lb.getName());
                        gc.sendObject(lb.getName());

                        myTurn = false;
                        if (tttg.isOver())
                        {
                            String gewinner = tttg.getSieger() == 0 ? "X" : "O";
                            JOptionPane.showMessageDialog(this, String.format("%s hat gewonnen!", gewinner));
                            beginner *= -1;

                            changeLabelState(false);
                            gameOver = true;

                        } else if (tttg.isUnendschieden())
                        {
                            JOptionPane.showMessageDialog(this, String.format("Unendschieden"));
                            beginner *= -1;

                            changeLabelState(false);
                            gameOver = true;

                        }
                    }
                }
            }
        }
    }

    private void restart()
    {
        for (JLabel label : labels)
        {
            label.setBackground(Color.black);
            label.setText("");
        }
        player = beginner;
        if (gameMode == 0)
        {
            if (beginner == -1)
            {
                JLabel lb = tttki.nextStep(labels);
                lb.setBackground(spieler1);
                lb.setText("O");
            }
        }
        changeLabelState(true);
        gameOver = false;
    }

    private JPopupMenu popupmenu;
    private JMenuItem miRestartSinglePlayer;
    private JMenuItem miRestartOfflineMultiplayer;

}
