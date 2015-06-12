/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vierGewinnt.gui;

import client.GameClient;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.Timer;
import vierGewinnt.bl.vierGewinntBL;

/**
 *
 * @author Lukas
 */
public class VierGewinntPanel extends JPanel {

    public static Color spieler1 = Color.red;
    public static Color spieler2 = Color.yellow;
    private JLabel[][] labels = new JLabel[6][7];
    private int[] numberOfClicks = new int[7];
    private JButton[] buttons = new JButton[7];
    private final String[] namen
            = {
                "Spiele 1", "Spieler 2"
            };
    private int lastGameStartedWith = 1;
    private int spieler = 1;
    private vierGewinntBL bl;
    private int column;
    private boolean over = false;
    private boolean addTimerCompleted = true;
    private int countRow;
    private int ss1;
    private int ss2;
    private GameClient gc;
    private int gameMode = 1;
    private boolean turn;

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    private Timer addTimer = new Timer(75, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("6 - " + numberOfClicks[column] + " = " + (6 - numberOfClicks[column]));
            System.out.println("countRow " + countRow + "\n");
            if(6 - numberOfClicks[column] == 0)
            {
                countRow = 0;
            }
            if (countRow == 0) {
                if (spieler == 1) {
                    labels[countRow][column].setBackground(spieler1);
                } else {
                    labels[countRow][column].setBackground(spieler2);
                }
            } else {
                labels[countRow - 1][column].setBackground(Color.black);
                if (spieler == 1) {
                    labels[countRow][column].setBackground(spieler1);
                } else {
                    labels[countRow][column].setBackground(spieler2);
                }
            }
            pnLbs.repaint();
            if (countRow == 6 - numberOfClicks[column] || 6 - numberOfClicks[column] == 0) {
                addTimerCompleted = true;
                isOver();
                spieler *= -1;
                countRow = 0;
                changePlayer(spieler == 1 ? true : false);
                addTimer.stop();
            }
            countRow++;
        }
    });
    private Timer restartTimer = new Timer(75, new ActionListener() {
        private int count = 0;

        @Override
        public void actionPerformed(ActionEvent e) {
            if (bl.isEverythingBlack()) {
                count = 6;
            } else {
                for (int i = 5; i >= 0; i--) {
                    for (int j = 6; j >= 0; j--) {
                        if (i <= count) {
                            labels[i][j].setBackground(Color.black);
                        } else {
                            labels[i][j].setBackground(labels[i - 1][j].getBackground());
                        }
                    }
                }
            }
            pnLbs.repaint();
            if (count == 6) {
                for (int i = 0; i < numberOfClicks.length; i++) {
                    numberOfClicks[i] = 0;
                }
                spieler = (lastGameStartedWith *= -1);
                changePlayer(spieler == 1 ? true : false);
                over = false;
                count = 0;
                restartTimer.stop();
            }
            count++;
        }
    });

    public VierGewinntPanel(GameClient gc) {
        initComponents();
        this.gc = gc;
        turn = true;
        if (gc.isConnected()) {
            VierGewinntNumberOfPlayerDLG dlg = new VierGewinntNumberOfPlayerDLG(null, true);
            if (dlg.isOk()) {
                gameMode = dlg.getGameMode();
                if (gameMode == 2) {
                    try {
                        turn = false;
                        gc.sendObject("VierGewinnt");
                        gc.newVierGewinntThread(this);
                    } catch (IOException ex) {
                        Logger.getLogger(VierGewinntPanel.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(VierGewinntPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        bl = new vierGewinntBL(labels);
        changePlayer(spieler == 1 ? true : false);
        lbSpieler.setText(namen[0]);
        changePlayer(true);
        this.setVisible(true);
    }

    private void initComponents() {
        this.setLayout(new BorderLayout());

        panel = new JPanel(new BorderLayout());
        lbSpieler = new JLabel();
        pnLbs = new VierGewinntLabelPanel(new GridLayout(6, 7, 0, 0));
        pnBts = new JPanel(new GridLayout(1, 7, 2, 1));

        popupmenu = new JPopupMenu("Game");
        miRestart = new JMenuItem("New Game");

        miRestart.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                restart();
            }
        });

        popupmenu.add(miRestart);

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                JLabel lb = new JLabel();
                lb.setOpaque(true);
                lb.setBackground(Color.black);
                lb.setName(i + "-" + j);
                lb.setComponentPopupMenu(popupmenu);
                labels[i][j] = lb;
                pnLbs.add(lb);
            }
        }

        for (int i = 0; i < 7; i++) {
            JButton bt = new JButton("V");
            bt.setActionCommand(i + "");
            bt.setBackground(Color.white);
            bt.setFont(new Font("Courier New", Font.BOLD, 20));
            bt.setComponentPopupMenu(popupmenu);
            bt.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    onClick(e);
                }
            });
            buttons[i] = bt;
            pnBts.add(bt);
        }

        lbSpieler.setFont(new Font("Courier New", Font.BOLD, 20));
        lbSpieler.setHorizontalAlignment(JLabel.CENTER);
        lbSpieler.setOpaque(true);
        lbSpieler.setComponentPopupMenu(popupmenu);

        panel.add(pnBts, BorderLayout.CENTER);
        panel.add(lbSpieler, BorderLayout.NORTH);

        this.add(pnLbs, BorderLayout.CENTER);
        this.add(panel, BorderLayout.NORTH);
    }

    private void restart() {
        over = true;
        restartTimer.start();
    }

    private void changePlayer(boolean spieler1) {
        if (spieler1) {
            Color col = new Color(255 - VierGewinntPanel.spieler1.getRed(), 255 - VierGewinntPanel.spieler1.getGreen(), 255 - VierGewinntPanel.spieler1.getBlue());
            lbSpieler.setBackground(VierGewinntPanel.spieler1);
            lbSpieler.setForeground(col);
            lbSpieler.setText(namen[0]);
        } else {
            Color col = new Color(255 - spieler2.getRed(), 255 - spieler2.getGreen(), 255 - spieler2.getBlue());
            lbSpieler.setBackground(spieler2);
            lbSpieler.setForeground(col);
            lbSpieler.setText(namen[1]);
        }
    }

    public void insertStone(int column) {
        if (numberOfClicks[column] < 6) {
            this.column = column;
            numberOfClicks[column] += 1;
            System.out.println(this.toString() + ":   " + column);
            //countRow = 0;
            addTimerCompleted = false;
            addTimer.start();
        }
    }

    private void onClick(ActionEvent e) {
        if (!over && addTimerCompleted && turn) {
            column = Integer.parseInt(e.getActionCommand());
            insertStone(column);

            if (gameMode == 2) {
                try {
                    System.out.println("VierGewinntPanel.onClick: sendObject");
                    gc.sendObject(column);
                    turn = false;
                } catch (IOException ex) {
                    Logger.getLogger(VierGewinntPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(VierGewinntPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void isOver() {
        if ((over = bl.isOver())) {
            String name = "";
            if (spieler == 1) {
                name = namen[0];
                ss1++;
            } else {
                name = namen[1];
                ss2++;
            }
            JOptionPane.showMessageDialog(this, name + " hat gewonnen!", "Hurra", JOptionPane.INFORMATION_MESSAGE);
        } else if ((over = bl.isUnendschieden())) {
            JOptionPane.showMessageDialog(this, "Unendschieden", "Ups", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private VierGewinntLabelPanel pnLbs;
    private JPanel pnBts;
    private JPanel panel;
    private JLabel lbSpieler;
    private JMenuBar mb;
    private JMenu menu;
    private JMenuItem miRestart;
    private JMenuItem miHelp;
    private JPopupMenu popupmenu;
    private JMenuItem miNewGame;
}
