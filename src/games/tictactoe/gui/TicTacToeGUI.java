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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Lukas
 */
public class TicTacToeGUI extends JLabel {

    private LinkedList<JLabel> labels = new LinkedList<>();
    private TicTacToeGewinnabfrage tttg;
    private TicTacToeKI tttki = new TicTacToeKI();
    private boolean einSpieler = true;
    private int spieler = 1;
    private int starter = 1;
    private int siegeS1;
    private int siegeS2;

    public TicTacToeGUI() {
        this.initComponents();
        tttg = new TicTacToeGewinnabfrage(labels);
        /*if (dlg.isOk()) {
            if (einSpieler = dlg.isEinSpieler()) {
                EinSpielerDLG esdlg = new EinSpielerDLG(this, true);
                esdlg.setVisible(true);
                if (esdlg.isOk()) {
                    namen = new String[2];
                    namen[0] = esdlg.getName();
                    namen[1] = "KI";
                    sdlg = new SpielstandDLG(this, namen);
                    sdlg.setVisible(true);
                    this.setVisible(true);
                } else {
                    this.setVisible(true);
                    this.dispose();
                }
            } else {
                ZweiSpilerDLG zsdlg = new ZweiSpilerDLG(this, true);
                zsdlg.setVisible(true);
                if (zsdlg.isOk()) {
                    namen = zsdlg.getNamen();
                    sdlg = new SpielstandDLG(this, namen);
                    sdlg.setVisible(true);
                    this.setVisible(true);
                } else {
                    this.setVisible(true);
                    this.dispose();
                }
            }
        } else {
            this.setVisible(false);
            this.dispose();
        }*/
    }

    private void initComponents() {
        this.addKeyListener(new MyKeyAdapter());
        this.setLayout(new GridLayout(3, 3, 1, 1));
        for (int i = 0; i < 9; i++) {
            JLabel lb = new JLabel();
            lb.setOpaque(true);
            lb.setBackground(Color.black);
            lb.setFont(new Font("Courier New", Font.BOLD, 75));
            lb.setHorizontalAlignment(JLabel.CENTER);
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

    class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyTyped(KeyEvent e) {
            onKeyTyped(e);
        }
    }

    private void onKeyTyped(KeyEvent e) {
        JLabel clickedLabel = null;
        switch (e.getKeyChar()) {
            case '1':
                clickedLabel = labels.get(6);
                break;
            case '2':
                clickedLabel = labels.get(7);
                break;
            case '3':
                clickedLabel = labels.get(8);
                break;
            case '4':
                clickedLabel = labels.get(3);
                break;
            case '5':
                clickedLabel = labels.get(4);
                break;
            case '6':
                clickedLabel = labels.get(5);
                break;
            case '7':
                clickedLabel = labels.get(0);
                break;
            case '8':
                clickedLabel = labels.get(1);
                break;
            case '9':
                clickedLabel = labels.get(2);
                break;
        }
        if (clickedLabel != null) {
            if (einSpieler) {
                if (clickedLabel.getBackground().equals(Color.black)) {
                    clickedLabel.setBackground(Color.yellow);
                    clickedLabel.setText("X");

                    if (tttg.isOver()) {
                        String gewinner = tttg.getSieger() == 0 ? "X" : "O";
                        if (tttg.getSieger() == 0) {
                            siegeS1++;
                        } else {
                            siegeS2++;
                        }
                        JOptionPane.showMessageDialog(this, String.format("%s hat gewonnen!", gewinner));
                        starter *= -1;
                        this.restart();
                    } else {
                        if (tttg.isUnendschieden()) {
                            JOptionPane.showMessageDialog(this, String.format("Unendschieden"));
                            starter *= -1;
                            this.restart();
                        } else {
                            spieler *= -1;
                            clickedLabel = tttki.nextStep(labels);
                            clickedLabel.setBackground(Color.cyan);
                            clickedLabel.setText("O");
                            if (tttg.isOver()) {
                                String gewinner = tttg.getSieger() == 0 ? "X" : "O";
                                if (tttg.getSieger() == 0) {
                                    siegeS1++;
                                } else {
                                    siegeS2++;
                                }
                                JOptionPane.showMessageDialog(this, String.format("%s hat gewonnen!", gewinner));
                                starter *= -1;
                                this.restart();
                            } else {
                                if (tttg.isUnendschieden()) {
                                    JOptionPane.showMessageDialog(this, String.format("Unendschieden"));
                                    starter *= -1;
                                    this.restart();
                                }
                            }
                        }
                        spieler *= -1;
                    }
                }
            } else {
                if (clickedLabel.getBackground().equals(Color.black)) {
                    if (spieler == 1) {
                        clickedLabel.setBackground(Color.yellow);
                        clickedLabel.setText("X");
                    } else {
                        clickedLabel.setBackground(Color.cyan);
                        clickedLabel.setText("O");
                    }

                    if (tttg.isOver()) {
                        String gewinner = tttg.getSieger() == 0 ? "X" : "O";
                        if (tttg.getSieger() == 0) {
                            siegeS1++;
                        } else {
                            siegeS2++;
                        }
                        JOptionPane.showMessageDialog(this, String.format("%s hat gewonnen!", gewinner));
                        starter *= -1;
                        this.restart();
                    } else {
                        if (tttg.isUnendschieden()) {
                            JOptionPane.showMessageDialog(this, String.format("Unendschieden"));
                            starter *= -1;
                            this.restart();
                        }
                    }
                    spieler *= -1;
                }
            }
        }
    }

    private void onMouseClicked(MouseEvent e) {
        if (einSpieler) {
            Object obj = e.getSource();
            if (obj instanceof JLabel) {
                JLabel lb = (JLabel) obj;
                if (lb.getBackground().equals(Color.black)) {
                    lb.setBackground(Color.yellow);
                    lb.setText("X");

                    if (tttg.isOver()) {
                        String gewinner = tttg.getSieger() == 0 ? "X" : "O";
                        if (tttg.getSieger() == 0) {
                            siegeS1++;
                        } else {
                            siegeS2++;
                        }
                        JOptionPane.showMessageDialog(this, String.format("%s hat gewonnen!", gewinner));
                        starter *= -1;
                        this.restart();
                    } else {
                        if (tttg.isUnendschieden()) {
                            JOptionPane.showMessageDialog(this, String.format("Unendschieden"));
                            starter *= -1;
                            this.restart();
                        } else {
                            spieler *= -1;
                            lb = tttki.nextStep(labels);
                            lb.setBackground(Color.cyan);
                            lb.setText("O");
                            if (tttg.isOver()) {
                                String gewinner = tttg.getSieger() == 0 ? "X" : "O";
                                if (tttg.getSieger() == 0) {
                                    siegeS1++;
                                } else {
                                    siegeS2++;
                                }
                                JOptionPane.showMessageDialog(this, String.format("%s hat gewonnen!", gewinner));
                                starter *= -1;
                                this.restart();
                            } else {
                                if (tttg.isUnendschieden()) {
                                    JOptionPane.showMessageDialog(this, String.format("Unendschieden"));
                                    starter *= -1;
                                    this.restart();
                                }
                            }
                        }
                        spieler *= -1;
                    }
                }
            }
        } else {
            Object obj = e.getSource();
            if (obj instanceof JLabel) {
                JLabel lb = (JLabel) obj;
                if (lb.getBackground().equals(Color.black)) {
                    if (spieler == 1) {
                        lb.setBackground(Color.yellow);
                        lb.setText("X");
                    } else {
                        lb.setBackground(Color.cyan);
                        lb.setText("O");
                    }

                    if (tttg.isOver()) {
                        String gewinner = tttg.getSieger() == 0 ? "X" : "Y";
                        if (tttg.getSieger() == 0) {
                            siegeS1++;
                        } else {
                            siegeS2++;
                        }
                        JOptionPane.showMessageDialog(this, String.format("%s hat gewonnen!", gewinner));
                        starter *= -1;
                        this.restart();
                    } else {
                        if (tttg.isUnendschieden()) {
                            JOptionPane.showMessageDialog(this, String.format("Unendschieden"));
                            starter *= -1;
                            this.restart();
                        }
                    }
                    spieler *= -1;
                }
            }
        }
    }

    private void restart() {
        for (JLabel label : labels) {
            label.setBackground(Color.black);
            label.setText("");
        }
        spieler = starter;
        if (einSpieler) {
            if (starter == -1) {
                JLabel lb = tttki.nextStep(labels);
                lb.setBackground(Color.cyan);
                lb.setText("O");
            }
        }

    }

    public static void main(String[] args) {
        new TicTacToeGUI();
    }
}
