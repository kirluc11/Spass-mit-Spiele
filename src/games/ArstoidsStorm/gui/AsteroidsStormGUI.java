/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package games.ArstoidsStorm.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.border.LineBorder;

/**
 *
 * @author Marcel
 */
public class AsteroidsStormGUI extends JFrame {

    private int numberX;
    private int numberY;
    private int moveSpeed = 10;

    public AsteroidsStormGUI() throws HeadlessException {
        super("Faaad");
        initComponents();
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setUndecorated(true);
    }

    public void initComponents() {
        Container con = this.getContentPane();
        con.setLayout(new BorderLayout());
        mb = new JMenuBar();
        mb.setOpaque(true);
        mb.setBorder(new LineBorder(Color.white));
        mb.setBackground(Color.black);
        menu = new JMenu("Game");
        menu.setForeground(Color.white);
        restart = new JMenuItem("Restart");
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onRestart(e);
            }
        });
        menu.add(restart);
        exit = new JMenuItem("Exit");
        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                onExit(e);
            }
        });
        menu.add(exit);
        this.setJMenuBar(mb);
        paStorm = new AsteroidsStormPanel();
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                move(e);
            }
        });
        mb.add(menu);
        con.add(paStorm, BorderLayout.CENTER);
        thread = new Thread((Runnable) paStorm);
        thread.start();
    }

    public void move(KeyEvent e) {
        if (!paStorm.isAus()) {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                numberY = numberY - moveSpeed;
                paStorm.setNumberY(numberY);
                paStorm.collision();
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                numberY = numberY + moveSpeed;
                paStorm.setNumberY(numberY);
                paStorm.collision();
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                numberX = numberX + moveSpeed;
                paStorm.setNumberX(numberX);
                paStorm.collision();
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                numberX = numberX - moveSpeed;
                paStorm.setNumberX(numberX);
                paStorm.collision();
            }
        }
    }
    
    public void onExit(ActionEvent e)
    {
        System.exit(0);
    }
    

    public void onRestart(ActionEvent e) {
        thread.interrupt();
        paStorm.setAus(false);
        numberX=0;
        numberY=0;
        paStorm.setNumberX(numberX);
        paStorm.setNumberY(numberY);
        paStorm.setCoordY(0);
        paStorm.setSpeed(5);
        thread = new Thread((Runnable) paStorm);
        thread.start();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new AsteroidsStormGUI().setVisible(true);
    }
    private Thread thread;
    private AsteroidsStormPanel paStorm;
    private JMenuBar mb;
    private JMenu menu;
    private JMenuItem restart;
    private JMenuItem exit;
}
