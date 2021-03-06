/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import server.GameServer;

/**
 *
 * @author Marcel
 */
public class ServerGUI extends javax.swing.JFrame
{
    private GameServer gs;

    /**
     * Creates new form ServerGUI
     */
    public ServerGUI()
    {
        initComponents();
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        taOutput.setEditable(false);
        try
        {
            gs= new GameServer(taOutput);
        } catch (IOException ex)
        {
            Logger.getLogger(ServerGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
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

        paButtons = new javax.swing.JPanel();
        tfPort = new javax.swing.JTextField();
        btStart = new javax.swing.JButton();
        btStop = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        taOutput = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        paButtons.setBackground(new java.awt.Color(255, 255, 255));
        paButtons.setLayout(new java.awt.GridLayout(1, 3));

        tfPort.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tfPort.setText("9999");
        paButtons.add(tfPort);

        btStart.setBackground(new java.awt.Color(255, 255, 255));
        btStart.setFont(new java.awt.Font("Courier New", 0, 11)); // NOI18N
        btStart.setText("Start");
        btStart.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                onStart(evt);
            }
        });
        paButtons.add(btStart);

        btStop.setBackground(new java.awt.Color(255, 255, 255));
        btStop.setFont(new java.awt.Font("Courier New", 0, 11)); // NOI18N
        btStop.setText("Stop");
        btStop.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                onStop(evt);
            }
        });
        paButtons.add(btStop);

        getContentPane().add(paButtons, java.awt.BorderLayout.PAGE_START);

        jScrollPane1.setBackground(new java.awt.Color(0, 0, 0));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(51, 51, 51), new java.awt.Color(51, 51, 51), new java.awt.Color(51, 51, 51), new java.awt.Color(51, 51, 51)), "Output", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Courier New", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        taOutput.setEditable(false);
        taOutput.setBackground(new java.awt.Color(0, 0, 0));
        taOutput.setColumns(20);
        taOutput.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        taOutput.setForeground(new java.awt.Color(255, 255, 255));
        taOutput.setRows(5);
        jScrollPane1.setViewportView(taOutput);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Starts the server on the selected port
     * @param evt ActionEvent
     */
    private void onStart(java.awt.event.ActionEvent evt)//GEN-FIRST:event_onStart
    {//GEN-HEADEREND:event_onStart

        String portString = tfPort.getText();
        if (!portString.isEmpty())
        {
            try
            {
            int portNr = Integer.parseInt(portString);
            gs.setPORTNR(portNr);
            gs.startServer();
            }catch(NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(this, "Please enter valid portnumber", "Invalid port", JOptionPane.ERROR_MESSAGE);
            }
        }


    }//GEN-LAST:event_onStart

    /**
     * Stops the server
     * @param evt ActionEvent
     */
    private void onStop(java.awt.event.ActionEvent evt)//GEN-FIRST:event_onStop
    {//GEN-HEADEREND:event_onStop
        try
        {
            gs.stopServer();
        } catch (IOException ex)
        {
            Logger.getLogger(ServerGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_onStop

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
//        try
//        {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
//            {
//                if ("Nimbus".equals(info.getName()))
//                {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex)
//        {
//            java.util.logging.Logger.getLogger(ServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex)
//        {
//            java.util.logging.Logger.getLogger(ServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex)
//        {
//            java.util.logging.Logger.getLogger(ServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex)
//        {
//            java.util.logging.Logger.getLogger(ServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
        try
        {
            //BeautyEyeLNFHelper.commonBackgroundColor = new Color(240, 240, 240);
            BeautyEyeLNFHelper.translucencyAtFrameInactive = false;
            BeautyEyeLNFHelper.launchBeautyEyeLNF();
            UIManager.put("RootPane.setupButtonVisible", false);
        } catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, "Could not found LaF");
        }

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new ServerGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btStart;
    private javax.swing.JButton btStop;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel paButtons;
    private javax.swing.JTextArea taOutput;
    private javax.swing.JTextField tfPort;
    // End of variables declaration//GEN-END:variables
}
