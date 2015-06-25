/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.BoxJumper.gui;

import games.BoxJumper.bl.Box;
import games.BoxJumper.bl.BoxJumper;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.util.ConcurrentModificationException;
import java.util.LinkedList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @since 12.06.2015
 * @author Lukas Kirchsteiger
 */
public class BoxJumperPanel extends javax.swing.JPanel implements Runnable {

    private Thread thread;

    private BoxJumper boxJumper = new BoxJumper();

    private final Color groundColor = Color.BLUE;
    private final int DIV = 20;
    private final int POSITION_OF_GROUND = 5;

    private double w;
    private double h;

    private LinkedList<Box> boxes = new LinkedList<>();
    
    private boolean jump;
    private boolean up = true;
    private int heightOfJump = 0;
    private int jumpTime = 20;

    private int score;

    private Random rand = new Random();

    private boolean start = false;

    /**
     * Creates new form BoxJumperPanel
     */
    public BoxJumperPanel() {
        initComponents();
        this.setComponentPopupMenu(jPopupMenu1);
    }


    /**
     * Restarts the game
     */
    public void restart() {
        start = true;
        score = 0;
        thread = new Thread(this);
        thread.start();
    }
    
    /**
     * Game thread gets interrupted
     */
    public void killThread()
    {
        thread.interrupt();
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        Graphics2D g = (Graphics2D) graphics;

        w = this.getWidth() / DIV;
        h = this.getHeight() / DIV;

        double jumpMultiplier = h / 7;

        g.setColor(groundColor);
        g.fill(new Rectangle2D.Double(0, h * (DIV - POSITION_OF_GROUND), this.getWidth(), POSITION_OF_GROUND*h+h));

        if (start) {
            Box startBox = new Box();
            startBox.setFrame(this.getWidth(), h * (DIV - POSITION_OF_GROUND - 1), w, h);
            boxes.clear();
            boxes.add(startBox);
            start = false;
        }

        if (jump) {
            if (up) {
                heightOfJump--;
                
            } else {
                heightOfJump++;
            }
            if (heightOfJump == -jumpTime) {
                up = false;
            }
            if (heightOfJump == 0) {
                up = true;
                jump = false;
            }

        }

        try {
            for (int i = boxes.size()-1; i >= 0; i--) {
                Box box = boxes.get(i);
                box.move(w / 4);
                g.setColor(box.getColor());
                g.fill(box);
                if (box.getX() < -w * 2) {
                    boxes.remove(box);
                    score++;
                } else if (box.intersects(boxJumper)) {
                    thread.interrupt();
                    JOptionPane.showMessageDialog(this, "Game over", "Game Over", JOptionPane.ERROR_MESSAGE);
                    break;
                }
            }
        } catch (ConcurrentModificationException cme) {

        }

        boxJumper.setFrame(w * 2, h * (DIV - POSITION_OF_GROUND - 1) + jumpMultiplier * heightOfJump, w, h);


        g.setFont(new Font("Open Sans Extrabold", Font.PLAIN, 170));
        g.setColor(groundColor);
        g.drawString(score + "", (float) w * DIV / 4, (float) h * DIV / 3);

        g.setColor(boxJumper.getColor());
        g.fill(boxJumper);
    }

    /**
     * Makes the boxjumper jump
     */
    public void jump() {
        if (!jump) {
            jump = true;
        }
    }

    /**
     * Adds a new Box which appears on the right side of the window
     */
    public void addBox() {
        Box box = new Box();
        box.setFrame(this.getWidth(), h * (DIV - POSITION_OF_GROUND - 1), w, h);
        boxes.add(box);
    }

    @Override
    public void run() {
        int og = 100;
        int ug = 30;
        int count = 0;
        int rn = rand.nextInt(og - ug + 1) + ug;
        try {
            while (!Thread.interrupted()) {
                Thread.sleep(15);
                count++;
                repaint();
//                if (count % 10 == 0) {
//                    jump();
//                }
                if (count == rn) {
                    addBox();
                    rn = rand.nextInt(og - ug + 1) + ug;
                    if (rn % 10 == 0) {
                        rn = 12;
                    }
                    count = 0;
                }

            }
        } catch (InterruptedException ex) {
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        miRestart = new javax.swing.JMenuItem();

        miRestart.setText("Restart");
        miRestart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onMiRestart(evt);
            }
        });
        jPopupMenu1.add(miRestart);
        
        miHowTo = new javax.swing.JMenuItem();

        miHowTo.setText("How To");
        miHowTo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JOptionPane.showMessageDialog(null, "The aim of the game is to jump over "
                        + "as much blocks as you can\n"
                        + "Jump: W, VK_UP");
            }
        });
        jPopupMenu1.add(miHowTo);

        setBackground(new java.awt.Color(0, 0, 0));
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                onMove(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void onMove(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_onMove
        if ((evt.getKeyCode() == KeyEvent.VK_UP || evt.getKeyCode() == KeyEvent.VK_W)) {
            jump();
        }
    }//GEN-LAST:event_onMove

    private void onMiRestart(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onMiRestart
        restart();
    }//GEN-LAST:event_onMiRestart

    /**
     * Main method to start game isolated from the whole project
     * 
     * @param args 
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(new BorderLayout());

        JPanel pn = new JPanel(new BorderLayout());

        BoxJumperPanel bjp = new BoxJumperPanel();

        pn.add(bjp);

        //pn.setBorder(new TitledBorder(new EmptyBorder(5, 0, 0, 0), "Snake", TitledBorder.CENTER, TitledBorder.ABOVE_TOP));
        frame.add(pn, BorderLayout.CENTER);

        frame.setVisible(true);

        bjp.restart();

        bjp.requestFocus();

        /*Thread t = new Thread(bjp);
         t.start();*/
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JMenuItem miRestart;
    private javax.swing.JMenuItem miHowTo;
    // End of variables declaration//GEN-END:variables
}
