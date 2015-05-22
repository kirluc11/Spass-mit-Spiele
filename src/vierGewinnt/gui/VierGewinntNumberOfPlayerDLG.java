/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vierGewinnt.gui;

import games.tictactoe.gui.*;
import javax.swing.JLabel;

/**
 *
 * @author Churchy
 */
public class VierGewinntNumberOfPlayerDLG extends javax.swing.JDialog
{

    private boolean ok = false;
    private int gameMode;

    /**
     * Creates new form AnzSpielerDLG
     */
    public VierGewinntNumberOfPlayerDLG(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public boolean isOk()
    {
        return ok;
    }

    public int getGameMode()
    {
        return gameMode;
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

        jPanel1 = new javax.swing.JPanel();
        lbMulti = new javax.swing.JLabel();
        lbOnline = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        lbMulti.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lbMulti.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbMulti.setText("Singleplayer");
        lbMulti.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        lbMulti.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                onMouseClicked(evt);
            }
        });
        jPanel1.add(lbMulti);

        lbOnline.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lbOnline.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbOnline.setText("Online");
        lbOnline.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        lbOnline.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                onMouseClicked(evt);
            }
        });
        jPanel1.add(lbOnline);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void onMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_onMouseClicked
        if (((JLabel) evt.getSource()).getText().equals(lbMulti.getText()))
        {
            ok = true;
            gameMode = 1;
            dispose();
        } else if (((JLabel) evt.getSource()).getText().equals(lbOnline.getText()))
        {
            ok = true;
            gameMode = 2;
            dispose();
        }

    }//GEN-LAST:event_onMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbMulti;
    private javax.swing.JLabel lbOnline;
    // End of variables declaration//GEN-END:variables
}