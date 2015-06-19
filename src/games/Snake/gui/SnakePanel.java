/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.Snake.gui;

import games.Snake.bl.Directions;
import games.Snake.bl.Snake;
import games.Snake.bl.SnakePart;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Lukas, Marcel
 */
public class SnakePanel extends javax.swing.JPanel implements Runnable, Directions {

    private Snake snake;
    private Direction dir = Direction.RIGHT;
    private Thread thread;

    /**
     * Creates new form SnakePanel
     */
    public SnakePanel() {
        initComponents();
    }
    
    public void startGame()
    {
        this.initSnake();

        this.requestFocus();

        thread = new Thread(this);
        thread.start();
    }

    private void initSnake() {
        snake = new Snake(this.getWidth(), this.getHeight());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        pmRestart = new javax.swing.JPopupMenu();
        miRestart = new javax.swing.JMenuItem();

        miRestart.setText("Restart");
        miRestart.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                onMiRestart(evt);
            }
        });
        pmRestart.add(miRestart);

        setBackground(new java.awt.Color(0, 0, 0));
        setComponentPopupMenu(pmRestart);
        addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                onKeyTyped(evt);
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

    private void onKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_onKeyTyped
        if (evt.getKeyCode() == KeyEvent.VK_UP || evt.getKeyCode() == KeyEvent.VK_W || (evt.getKeyChar() + "").equalsIgnoreCase("w")) {
            dir = Direction.UP;
        } else {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN || evt.getKeyCode() == KeyEvent.VK_S || (evt.getKeyChar() + "").equalsIgnoreCase("s")) {
                dir = Direction.DOWN;
            } else {
                if (evt.getKeyCode() == KeyEvent.VK_RIGHT || evt.getKeyCode() == KeyEvent.VK_D || (evt.getKeyChar() + "").equalsIgnoreCase("d")) {
                    dir = Direction.RIGHT;
                } else {
                    if (evt.getKeyCode() == KeyEvent.VK_LEFT || evt.getKeyCode() == KeyEvent.VK_A || (evt.getKeyChar() + "").equalsIgnoreCase("a")) {
                        dir = Direction.LEFT;
                    }
                }
            }
        }
        snake.changeDirection(dir);
    }//GEN-LAST:event_onKeyTyped

    private void onMiRestart(java.awt.event.ActionEvent evt)//GEN-FIRST:event_onMiRestart
    {//GEN-HEADEREND:event_onMiRestart
        if(!thread.interrupted())
        {
            endGame();
        }
        startGame();
    }//GEN-LAST:event_onMiRestart

    public void restart()
    {
        initSnake();
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(new BorderLayout());

        JPanel pn = new JPanel(new BorderLayout());

        SnakePanel sp = new SnakePanel();

        pn.add(sp);

        pn.setBorder(new TitledBorder(new EmptyBorder(5, 0, 0, 0), "Snake", TitledBorder.CENTER, TitledBorder.ABOVE_TOP));

        frame.add(pn, BorderLayout.CENTER);

        frame.setVisible(true);
        sp.initSnake();

        sp.requestFocus();

        Thread t = new Thread(sp);
        t.start();
    }

    public void endGame()
    {
        thread.interrupt();
    }
    
    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem miRestart;
    private javax.swing.JPopupMenu pmRestart;
    // End of variables declaration//GEN-END:variables
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()&&snake.move()) {
                try {
                    Thread.sleep(150);
                    repaint();
                } catch (InterruptedException ex) {
                }
            }
        } catch (IOException ex) {
            
        }catch(NullPointerException ex)
        {
            //System.out.println(ex.toString());
        }
    }

    @Override
    public void paint(Graphics gr) {
        super.paint(gr);
        try {
            Graphics2D g = (Graphics2D) gr;
            if (snake != null) {
                if (snake.getFood() != null) {
                    //image, 10, (getHeight() - getWidth()) / 2, getWidth() - 20, getWidth(), null
                    g.drawImage(snake.getFood().getImg(), (int) snake.getFood().getX(), (int) snake.getFood().getY(), (int)snake.getFood().getWidth(), (int)snake.getFood().getHeight(), null);
//                    g.setColor(snake.getFood().getColor());
//                    g.fill(snake.getFood());
                }
                
                
                LinkedList<SnakePart> snakeParts = snake.getSnakeParts();
                if (snakeParts != null || !snakeParts.isEmpty()) {
                    for (int i = snakeParts.size() - 1; i >= 0; i--) {
                        g.setColor(snakeParts.get(i).getColor());
                        g.fill(snakeParts.get(i));
                        g.setColor(Color.lightGray);
                        g.draw(snakeParts.get(i));
                    }
                }
            }
        } catch (Exception ex) {

        }
    }
}
