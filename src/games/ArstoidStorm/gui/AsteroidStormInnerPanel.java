/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package games.ArstoidStorm.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Inner panel of the game Asteroid Storm
 *
 * @author Marcel
 */
public class AsteroidStormInnerPanel extends JPanel implements Runnable
{

    private int x;
    private int y;
    private int numberX;
    private int numberY;
    private int coordX[] = new int[5];
    private int coordY[] = new int[5];
    private Random ran = new Random();
    private Color col = new Color(234, 122, 50);
    private boolean aus = false;
    private int speed = 5;
    private JLabel lbScore;
    private int score=0;

    /**
     * Returns bool variable if the game has ended true else false
     *
     * @return
     */
    public boolean isAus()
    {
        return aus;
    }

    /**
     * Increases the score by 1 and sets the text of the Scorepanel
     */
   public void addScore()
   {
       score++;
       lbScore.setText(""+score);
   }
    
    /**
     * Used to set boolean if game has ended
     *
     * @param aus
     */
    public void setAus(boolean aus)
    {
        this.aus = aus;
    }

    public void setCoordY(int coordY)
    {
        for (int i = 0; i < this.coordY.length; i++)
        {
            this.coordY[i] = coordY;
        }
    }

    public AsteroidStormInnerPanel(JLabel lbScore)
    {
        this.lbScore = lbScore;
        this.setOpaque(true);
        this.setBackground(Color.black);
        repaint();
    }

    /**
     * Sets the x coordinate of the player
     *
     * @param numberX
     */
    public void setNumberX(int numberX)
    {
        this.numberX = numberX;
        repaint();
    }

    /**
     * Sets the y coordinate of the player
     *
     * @param numberY
     */
    public void setNumberY(int numberY)
    {
        this.numberY = numberY;
        repaint();
    }

    /**
     * Sets the speed of the game
     *
     * @param speed
     */
    public void setSpeed(int speed)
    {
        this.speed = speed;
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        x = this.getWidth();
        y = this.getHeight();
        g.setColor(Color.white);
        g.fillRect(x / 2 + numberX, y / 2 + numberY, x / 20, y / 20);
        g.setColor(col);
        for (int i = 0; i < coordX.length; i++)
        {
            g.fillRect(coordX[i], coordY[i], x / 10, y / 10);
        }

    }

    /**
     * Detects collision between the player and other hazards
     */
    public void collision()
    {

        for (int l = 0; l < coordX.length; l++)
        {
            for (int i = 0; i < x / 10; i++)
            {
                for (int j = 0; j < y / 10; j++)
                {
                    if ((x / 2 + numberX == coordX[l] + i && y / 2 + numberY == coordY[l] + j)
                            || (x / 2 + numberX + x / 20 == coordX[l] + i && y / 2 + numberY == coordY[l] + j)
                            || (x / 2 + numberX + x / 20 == coordX[l] + i && y / 2 + numberY + y / 20 == coordY[l] + j)
                            || (x / 2 + numberX == coordX[l] + i && y / 2 + numberY + y / 20 == coordY[l] + j))
                    {
                        aus = true;
                        JOptionPane.showMessageDialog(this, "GAME OVER!!");
                        return;
                    }
                }
            }
        }
    }

    /**
     * Moves the hazards from upside down
     */
    @Override
    public void run()
    {
        
        for (int i = 0; i < coordX.length; i++)
        {
            coordX[i] = ran.nextInt(470);
        }
        col = new Color(ran.nextInt(256), ran.nextInt(256), ran.nextInt(256));
        while (!Thread.interrupted() && !aus)
        {
            for (int i = 0; i < coordX.length; i++)
            {
                int j = coordX[i];
                if (coordY[i] < y)
                {
                    coordY[i]++;
                } else if (y != 0)
                {
                    addScore();
                    col = new Color(ran.nextInt(256), ran.nextInt(256), ran.nextInt(256));
                    coordY[i] = 0;
                    if (i == 0 && speed > 2)
                    {
                        speed--;
                    }
                    coordX[i] = ran.nextInt(x - 30);
                }
            }
            repaint();
            collision();
            try
            {
                Thread.sleep(speed);
            } catch (InterruptedException ex)
            {
                break;
            }
        }
        
    }
}
