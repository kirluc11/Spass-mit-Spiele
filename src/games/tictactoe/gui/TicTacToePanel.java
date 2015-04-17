/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package games.tictactoe.gui;

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
import java.util.LinkedList;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

/**
 *
 * @author Lukas
 */
public class TicTacToePanel extends JLabel {

    private LinkedList<JLabel> labels = new LinkedList<>();
    private TicTacToeGewinnabfrage tttg;
    private TicTacToeKI tttki = new TicTacToeKI();
    private boolean singlePlayer = true;
    private int player = 1;
    private int beginner = 1;

    private boolean gameOver;

    public TicTacToePanel() {
        this.initComponents();
        tttg = new TicTacToeGewinnabfrage(labels);

        NumberOfPlayerDLG dlg = new NumberOfPlayerDLG(null, true);

        if (dlg.isOk()) {
            singlePlayer = dlg.isSinglePlayer();
        }
    }

    private void initComponents() {
        this.setLayout(new GridLayout(3, 3, 1, 1));

        popupmenu = new JPopupMenu("Game");
        miRestartSinglePlayer = new JMenuItem("New Single Player Game");
        miRestartOfflineMultiplayer = new JMenuItem("New Offline Multiplayer Game");

        miRestartSinglePlayer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                singlePlayer = true;
                restart();
            }
        });

        miRestartOfflineMultiplayer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                singlePlayer = false;
                restart();
            }
        });

        popupmenu.add(miRestartSinglePlayer);
        popupmenu.add(miRestartOfflineMultiplayer);

        for (int i = 0; i < 9; i++) {
            JLabel lb = new JLabel();
            lb.setOpaque(true);
            lb.setBackground(Color.black);
            lb.setFont(new Font("Courier New", Font.BOLD, 75));
            lb.setHorizontalAlignment(JLabel.CENTER);
            lb.setComponentPopupMenu(popupmenu);
            lb.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    onMouseClicked(e);
                }
            });
            lb.setName("" + i);
            this.add(lb);
            labels.add(lb);
        }
    }

    private void changeLabelState(boolean b) {
        for (JLabel label : labels) {
            label.setEnabled(b);
        }
    }

    private void onMouseClicked(MouseEvent e) {
        if (!gameOver) {
            if (singlePlayer) {
                Object obj = e.getSource();
                if (obj instanceof JLabel) {
                    JLabel lb = (JLabel) obj;
                    if (lb.getBackground().equals(Color.black)) {
                        lb.setBackground(Color.yellow);
                        lb.setText("X");

                        if (tttg.isOver()) {
                            String gewinner = tttg.getSieger() == 0 ? "X" : "O";
                            JOptionPane.showMessageDialog(this, String.format("%s hat gewonnen!", gewinner));
                            beginner *= -1;

                            changeLabelState(false);
                            gameOver = true;

                        } else {
                            if (tttg.isUnendschieden()) {
                                JOptionPane.showMessageDialog(this, String.format("Unendschieden"));
                                beginner *= -1;

                                changeLabelState(false);
                                gameOver = true;

                            } else {
                                player *= -1;
                                lb = tttki.nextStep(labels);
                                lb.setBackground(Color.cyan);
                                lb.setText("O");
                                if (tttg.isOver()) {
                                    String gewinner = tttg.getSieger() == 0 ? "X" : "O";
                                    JOptionPane.showMessageDialog(this, String.format("%s hat gewonnen!", gewinner));
                                    beginner *= -1;

                                    changeLabelState(false);
                                    gameOver = true;

                                } else {
                                    if (tttg.isUnendschieden()) {
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
            } else {
                Object obj = e.getSource();
                if (obj instanceof JLabel) {
                    JLabel lb = (JLabel) obj;
                    if (lb.getBackground().equals(Color.black)) {
                        if (player == 1) {
                            lb.setBackground(Color.yellow);
                            lb.setText("X");
                        } else {
                            lb.setBackground(Color.cyan);
                            lb.setText("O");
                        }

                        if (tttg.isOver()) {
                            String gewinner = tttg.getSieger() == 0 ? "X" : "Y";
                            JOptionPane.showMessageDialog(this, String.format("%s hat gewonnen!", gewinner));
                            beginner *= -1;

                            changeLabelState(false);
                            gameOver = true;

                        } else {
                            if (tttg.isUnendschieden()) {
                                JOptionPane.showMessageDialog(this, String.format("Unendschieden"));
                                beginner *= -1;

                                changeLabelState(false);
                                gameOver = true;

                            }
                        }
                        player *= -1;
                    }
                }
            }
        }
    }

    private void restart() {
        for (JLabel label : labels) {
            label.setBackground(Color.black);
            label.setText("");
        }
        player = beginner;
        if (singlePlayer) {
            if (beginner == -1) {
                JLabel lb = tttki.nextStep(labels);
                lb.setBackground(Color.cyan);
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
