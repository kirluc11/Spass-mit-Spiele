/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import client.GameClient;
import games.ArstoidStorm.gui.AsteroidStormPanel;
import games.BoxJumper.gui.BoxJumperPanel;
import games.Snake.gui.SnakePanel;
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
import vierGewinnt.gui.VierGewinntPanel;

/**
 *
 * @author Marcel, Lukas
 */
public class GameChooserPanel extends javax.swing.JPanel
{

    private HashMap<String, Image> allGames = new HashMap<String, Image>();
    private JPanel paGame;
    private GameClient gc;
    private PlayerGUI pgui;

    public GameChooserPanel(JPanel paGame, GameClient gc, PlayerGUI pgui)
    {
        this.paGame = paGame;
        this.gc = gc;
        this.pgui = pgui;
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
     * Adds the games to allGames HashMap
     * @throws IOException 
     */
    public void gameAdding() throws IOException
    {
        String path = System.getProperty("user.dir")
                + File.separator + "src"
                + File.separator + "res"
                + File.separator + "pictures";

        allGames.put("Hangman", ImageIO.read(new File(path + File.separator + "hangman.png")));
        allGames.put("TicTacToe", ImageIO.read(new File(path + File.separator + "TicTacToe.png")));
        allGames.put("AsteroidStorm", ImageIO.read(new File(path + File.separator + "AsteroidStorm.PNG")));
        allGames.put("4-Gewinnt", ImageIO.read(new File(path + File.separator + "vierGewinnt.png")));
        allGames.put("Snake", ImageIO.read(new File(path + File.separator + "Snake.png")));
        allGames.put("Boxjumper", ImageIO.read(new File(path + File.separator + "BoxJumper.png")));

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
                    try
                    {
                        changeGame(clickedPanel.getName());
                    } catch (IOException ex)
                    {
                        Logger.getLogger(GameChooserPanel.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex)
                    {
                        Logger.getLogger(GameChooserPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            paTempGame.setBorder(new TitledBorder(name));
            paTempGame.setName(name);
            this.add(paTempGame);
        }
    }

    /**
     * Updates the panel and informations in the PlayerGUI
     * @param aktGame 
     */
    public void aktualisierePaGame(JPanel aktGame)
    {
        pgui.setAktPanel(aktGame);
        paGame.add(aktGame);
        paGame.repaint();
        paGame.getRootPane().updateUI();
    }

    /**
     * Changes the actual game
     * @param name
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public void changeGame(String name) throws IOException, ClassNotFoundException
    {
        paGame.removeAll();

        switch (name)
        {
            case "Hangman":
                HangmanPanel paHang = new HangmanPanel(pgui);
                aktualisierePaGame(paHang);
                paHang.startGame();
                break;
            case "TicTacToe":
                TicTacToePanel tttp = new TicTacToePanel(gc, pgui);
                if (tttp.isOk())
                {
                    aktualisierePaGame(tttp);
                }
                break;
            case "AsteroidStorm":
                AsteroidStormPanel asp = new AsteroidStormPanel();
                aktualisierePaGame(asp);
                asp.requestFocus();
                break;
            case "4-Gewinnt":
                VierGewinntPanel vgp = new VierGewinntPanel(gc, pgui);
                if (vgp.isOk())
                {
                    aktualisierePaGame(vgp);
                    vgp.requestFocus();
                }
                break;
            case "Snake":
                SnakePanel snake = new SnakePanel();
                aktualisierePaGame(snake);
                snake.startGame();
                snake.requestFocus();
                break;
            case "Boxjumper":
                BoxJumperPanel boxjumper = new BoxJumperPanel();
                aktualisierePaGame(boxjumper);
                boxjumper.restart();
                boxjumper.requestFocus();
                break;
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
            if (getHeight() < getWidth())
            {
                g.drawImage(image, (getWidth() - getHeight()) / 2, 10, getHeight(), getHeight() - 20, null);
            } else
            {
                g.drawImage(image, 10, (getHeight() - getWidth()) / 2, getWidth() - 20, getWidth(), null);
            }
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
