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
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Churchy
 */
public class SnakePanel extends javax.swing.JPanel implements Runnable, Directions {

    Snake snake;

    /**
     * Creates new form SnakePanel
     */
    public SnakePanel() {
        initComponents();
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
    private void initComponents() {

        setBackground(new java.awt.Color(0, 0, 0));
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
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
            snake.changeDirection(Direction.UP);
        } else {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN || evt.getKeyCode() == KeyEvent.VK_S || (evt.getKeyChar() + "").equalsIgnoreCase("s")) {
                snake.changeDirection(Direction.DOWN);
            } else {
                if (evt.getKeyCode() == KeyEvent.VK_RIGHT || evt.getKeyCode() == KeyEvent.VK_D || (evt.getKeyChar() + "").equalsIgnoreCase("d")) {
                    snake.changeDirection(Direction.RIGHT);
                } else {
                    if (evt.getKeyCode() == KeyEvent.VK_LEFT || evt.getKeyCode() == KeyEvent.VK_A || (evt.getKeyChar() + "").equalsIgnoreCase("a")) {
                        snake.changeDirection(Direction.LEFT);
                    }
                }
            }
        }
    }//GEN-LAST:event_onKeyTyped

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(300);
                repaint();
            } catch (InterruptedException ex) {
            }
        }
    }

    @Override
    public void paint(Graphics gr) {
        super.paint(gr);
        Graphics2D g = (Graphics2D) gr;
        if (snake != null) {
            snake.move();
            LinkedList<SnakePart> snakeParts = snake.getSnakeParts();
            for (SnakePart snakePart : snakeParts) {
                g.setColor(snakePart.getColor());
                g.fill(snakePart);
            }
        }
    }
}
