/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import games.hangman.gui.HangmanPanel;
import games.tictactoe.gui.TicTacToePanel;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 *
 * @author user
 */
public class GameChooserPanel extends javax.swing.JPanel
{

    private HashMap<String, Image> allGames = new HashMap<String, Image>();
    private JPanel paGame;

    

    public GameChooserPanel(JPanel paGame)
    {
        this.paGame = paGame;
        initComponents();
        try
        {
            this.gameAdding();
        } catch (IOException ex)
        {
            Logger.getLogger(GameChooserPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Creates new form GameChooserPanel
     */
    public GameChooserPanel()
    {
        
    }
    
    public void gameAdding() throws IOException
    {
        String path = System.getProperty("user.dir")
                + File.separator + "src"
                + File.separator + "res"
                + File.separator + "pictures";

        allGames.put("Hangman", ImageIO.read(new File(path + File.separator + "hangman.png")));
        allGames.put("TicTacToe", ImageIO.read(new File(path + File.separator + "hangman.png")));

        for (String name : allGames.keySet())
        {
            Image aktImage = allGames.get(name);
            ImageComponent paTempGame = new ImageComponent(aktImage);
            paTempGame.addMouseListener(new MouseAdapter()
            {

                @Override
                public void mouseClicked(MouseEvent e)
                {
                    JPanel clickedPanel = (JPanel) e.getComponent();
                    changeGame(clickedPanel.getName());
                }
            });
            paTempGame.setBorder(new TitledBorder(name));
            paTempGame.setName(name);
            this.add(paTempGame);
        }
    }
    
    
    
    public void changeGame(String name)
    {
        paGame.removeAll();
        switch(name)
        {
            case "Hangman": 
                HangmanPanel paHang = new HangmanPanel();
                paGame.add(paHang);
                paHang.startGame();
                break;
            case "TicTacToe":
                paGame.add(new TicTacToePanel());
        }
    }

    //Used for changing Picture size
    class ImageComponent extends JPanel
    {

        private Image image;

        public ImageComponent(Image image)
        {
            this.image = image;
        }

        @Override
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
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

        setBorder(javax.swing.BorderFactory.createTitledBorder("Game Chooser"));
        setLayout(new java.awt.GridLayout(3, 3));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
