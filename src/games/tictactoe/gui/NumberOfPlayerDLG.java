/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.tictactoe.gui;

import javax.swing.JLabel;

/**
 *
 * @author Lukas
 */
public class NumberOfPlayerDLG extends javax.swing.JDialog
{

    private boolean ok = false;
    private int gameMode;

    /**
     * Creates new form AnzSpielerDLG
     */
    public NumberOfPlayerDLG(java.awt.Frame parent, boolean modal)
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
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbSingle = new javax.swing.JLabel();
        lbMult = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setLayout(new java.awt.GridLayout());

        lbSingle.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lbSingle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbSingle.setText("Singleplayer");
        lbSingle.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        lbSingle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                onMouseClicked(evt);
            }
        });
        jPanel1.add(lbSingle);

        lbMult.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lbMult.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbMult.setText("Multiplayer");
        lbMult.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        lbMult.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                onMouseClicked(evt);
            }
        });
        jPanel1.add(lbMult);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void onMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_onMouseClicked
        if (((JLabel) evt.getSource()).getText().equals(lbSingle.getText()))
        {
            ok = true;
            gameMode = 0;
            dispose();
        } else if (((JLabel) evt.getSource()).getText().equals(lbMult.getText()))
        {
            ok = true;
            gameMode = 1;
            dispose();
        }

    }//GEN-LAST:event_onMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbMult;
    private javax.swing.JLabel lbSingle;
    // End of variables declaration//GEN-END:variables
}
