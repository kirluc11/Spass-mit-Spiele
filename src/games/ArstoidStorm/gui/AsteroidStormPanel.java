/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.ArstoidStorm.gui;

import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class AsteroidStormPanel extends javax.swing.JPanel {

    private AsteroidStormInnerPanel asip = new AsteroidStormInnerPanel();
    private int numberX;
    private int numberY;
    private int moveSpeed = 10;
    private Thread thread;

    /**
     * Creates new form AsteroidStormPanel
     */
    public AsteroidStormPanel() {
        initComponents();
        this.add(asip);

        this.setFocusable(true);
        startGame();

    }

    public void startGame() {

        thread = new Thread((Runnable) asip);
        thread.start();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pmRestart = new javax.swing.JPopupMenu();
        miRestart = new javax.swing.JMenuItem();

        miRestart.setText("Restart");
        miRestart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onRestart(evt);
            }
        });
        pmRestart.add(miRestart);

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createTitledBorder("AsteroidStorm"));
        setComponentPopupMenu(pmRestart);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                onMove(evt);
            }
        });
        setLayout(new java.awt.GridLayout());
    }// </editor-fold>//GEN-END:initComponents

    private void onMove(java.awt.event.KeyEvent evt)//GEN-FIRST:event_onMove
    {//GEN-HEADEREND:event_onMove

        if (!asip.isAus()) {

            if ((evt.getKeyCode() == KeyEvent.VK_UP || evt.getKeyCode() == KeyEvent.VK_W) && numberY > (asip.getHeight() / 2) * -1) {

                numberY = numberY - moveSpeed;
                asip.setNumberY(numberY);
                asip.collision();
            } else if ((evt.getKeyCode() == KeyEvent.VK_DOWN || evt.getKeyCode() == KeyEvent.VK_S) && numberY < asip.getHeight() / 2 - asip.getHeight() / 20) {

                numberY = numberY + moveSpeed;
                asip.setNumberY(numberY);
                asip.collision();
            } else if ((evt.getKeyCode() == KeyEvent.VK_RIGHT || evt.getKeyCode() == KeyEvent.VK_D) && numberX < (asip.getWidth() / 2 - asip.getWidth() / 20)) {
                numberX = numberX + moveSpeed;
                asip.setNumberX(numberX);
                asip.collision();
            } else if ((evt.getKeyCode() == KeyEvent.VK_LEFT || evt.getKeyCode() == KeyEvent.VK_A) && numberX > (asip.getWidth() / 2) * -1) {
                numberX = numberX - moveSpeed;
                asip.setNumberX(numberX);
                asip.collision();
            }
        }
    }//GEN-LAST:event_onMove

    private void onRestart(java.awt.event.ActionEvent evt)//GEN-FIRST:event_onRestart
    {//GEN-HEADEREND:event_onRestart

        thread.interrupt();
        asip.setAus(false);
        numberX = 0;
        numberY = 0;
        asip.setNumberX(numberX);
        asip.setNumberY(numberY);
        asip.setCoordY(0);
        asip.setSpeed(5);
        startGame();
    }//GEN-LAST:event_onRestart

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem miRestart;
    private javax.swing.JPopupMenu pmRestart;
    // End of variables declaration//GEN-END:variables
}
