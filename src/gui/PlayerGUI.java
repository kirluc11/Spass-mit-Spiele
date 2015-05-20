/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import client.GameClient;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

/**
 *
 * @author Churchy
 */
public class PlayerGUI extends javax.swing.JFrame
{

    private JPanel aktPanel;
    private boolean connected = false;
    private GameClient gClient;

    /**
     * Creates new form PlayerGUI
     */
    public PlayerGUI()
    {
        initComponents();
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);
        //HangmanPanel hmp = new HangmanPanel();
        this.setVisible(true);
        gClient = new GameClient();
        //hmp.startGame();
        showGameChooser();
    }

    public void showGameChooser()
    {
        GameChooserPanel gcp = new GameChooserPanel(pnGame, gClient);
        aktPanel = gcp;
        pnGame.removeAll();
        pnGame.add(gcp);
        pnGame.updateUI();
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

        pnTop = new javax.swing.JPanel();
        pnServer = new javax.swing.JPanel();
        pnIP = new javax.swing.JPanel();
        tfIP = new javax.swing.JTextField();
        pnPort = new javax.swing.JPanel();
        tfPort = new javax.swing.JTextField();
        btConnect = new javax.swing.JButton();
        btHome = new javax.swing.JButton();
        pnGame = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnTop.setLayout(new java.awt.BorderLayout());

        pnServer.setLayout(new java.awt.GridLayout(1, 4, 5, 0));

        pnIP.setBorder(javax.swing.BorderFactory.createTitledBorder("IP-Adress"));
        pnIP.setLayout(new java.awt.BorderLayout());

        tfIP.setText("127.0.0.1");
        pnIP.add(tfIP, java.awt.BorderLayout.CENTER);

        pnServer.add(pnIP);

        pnPort.setBorder(javax.swing.BorderFactory.createTitledBorder("Port"));
        pnPort.setLayout(new java.awt.BorderLayout());

        tfPort.setText("9999");
        pnPort.add(tfPort, java.awt.BorderLayout.CENTER);

        pnServer.add(pnPort);

        btConnect.setText("Connect");
        btConnect.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                onDisConnect(evt);
            }
        });
        pnServer.add(btConnect);

        pnTop.add(pnServer, java.awt.BorderLayout.CENTER);

        btHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/pictures/home.png"))); // NOI18N
        btHome.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                onHome(evt);
            }
        });
        pnTop.add(btHome, java.awt.BorderLayout.EAST);

        getContentPane().add(pnTop, java.awt.BorderLayout.NORTH);

        pnGame.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        pnGame.setLayout(new java.awt.GridLayout(1, 0));
        getContentPane().add(pnGame, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void onHome(java.awt.event.ActionEvent evt)//GEN-FIRST:event_onHome
    {//GEN-HEADEREND:event_onHome
        if(gClient.isConnected())
        {
            try
            {
                gClient.sendObject("##GO##HOME##");
            } catch (IOException ex)
            {
                Logger.getLogger(PlayerGUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex)
            {
                Logger.getLogger(PlayerGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        showGameChooser();
    }//GEN-LAST:event_onHome

    private void onDisConnect(java.awt.event.ActionEvent evt)//GEN-FIRST:event_onDisConnect
    {//GEN-HEADEREND:event_onDisConnect
        if (!connected)
        {
            String nickname = JOptionPane.showInputDialog("Please enter nickname");
            String inetAddress = tfIP.getText();
            String portString = tfPort.getText();
            if (!inetAddress.isEmpty() && !portString.isEmpty())
            {
                try
                {
                    gClient.setPORTNR(Integer.parseInt(portString));
                    gClient.setAddress(inetAddress);
                    gClient.startClient();
                    gClient.setNickname(nickname);
                    connected = true;
                    btConnect.setText("Disconnect");
                } catch (IOException ex)
                {
                    JOptionPane.showMessageDialog(this, "An error occurred", "ERROR", JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(this, "Please enter valid portnumber", "Invalid port", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else
        {
            try
            {
                gClient.stopClient();
                connected = false;
                btConnect.setText("Connect");
            } catch (IOException ex)
            {
                JOptionPane.showMessageDialog(this, "An error occurred", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_onDisConnect

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {

        try
        {
            /* Set the Nimbus look and feel */
            //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
            /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
             * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
             */
            try
            {
                for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
                {
                    if ("Nimbus".equals(info.getName()))
                    {
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (ClassNotFoundException ex)
            {
                java.util.logging.Logger.getLogger(PlayerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (InstantiationException ex)
            {
                java.util.logging.Logger.getLogger(PlayerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex)
            {
                java.util.logging.Logger.getLogger(PlayerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (javax.swing.UnsupportedLookAndFeelException ex)
            {
                java.util.logging.Logger.getLogger(PlayerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            //</editor-fold>

            BeautyEyeLNFHelper.translucencyAtFrameInactive = false;
            BeautyEyeLNFHelper.launchBeautyEyeLNF();
            UIManager.put("RootPane.setupButtonVisible", false);

            /* Create and display the form */
            java.awt.EventQueue.invokeLater(new Runnable()
            {
                public void run()
                {
                    new PlayerGUI();
                    new ServerGUI().setVisible(true);
                }
            });
        } catch (Exception ex)
        {
            Logger.getLogger(PlayerGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        //</editor-fold>
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btConnect;
    private javax.swing.JButton btHome;
    private javax.swing.JPanel pnGame;
    private javax.swing.JPanel pnIP;
    private javax.swing.JPanel pnPort;
    private javax.swing.JPanel pnServer;
    private javax.swing.JPanel pnTop;
    private javax.swing.JTextField tfIP;
    private javax.swing.JTextField tfPort;
    // End of variables declaration//GEN-END:variables
}
