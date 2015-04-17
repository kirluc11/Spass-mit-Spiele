/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package games.ArstoidsStorm.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Marcel
 */
public class AsteroidsStormPanel extends JPanel implements Runnable {

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

    public boolean isAus() {
        return aus;
    }

    public void setAus(boolean aus) {
        this.aus = aus;
    }

    public void setCoordY(int coordY) {
        for (int i = 0; i < this.coordY.length; i++) {
            this.coordY[i] = coordY;
        }
    }

    public AsteroidsStormPanel() {
        this.setOpaque(true);
        this.setBackground(Color.black);
        repaint();
    }

    public void setNumberX(int numberX) {
        this.numberX = numberX;
        repaint();
    }

    public void setNumberY(int numberY) {
        this.numberY = numberY;
        repaint();
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        x = this.getWidth();
        y = this.getHeight();
        g.setColor(Color.white);
        g.fillRect(x / 2 + numberX, y / 2 + numberY, 10, 10);
        g.setColor(col);
        for (int i = 0; i < coordX.length; i++) {
            g.fillRect(coordX[i], coordY[i], 30, 30);
        }



    }

    public void collision() {
        for (int l = 0; l < coordX.length; l++) {
            for (int i = 0; i < 30; i++) {
                for (int j = 0; j < 30; j++) {
                    if ((x / 2 + numberX == coordX[l] + i && y / 2 + numberY == coordY[l] + j)
                            || (x / 2 + numberX + 10 == coordX[l] + i && y / 2 + numberY == coordY[l] + j)
                            || (x / 2 + numberX + 10 == coordX[l] + i && y / 2 + numberY + 10 == coordY[l] + j)
                            || (x / 2 + numberX == coordX[l] + i && y / 2 + numberY + 10 == coordY[l] + j)) {
                        aus = true;
                        JOptionPane.showMessageDialog(this, "GAME OVER!!");
                        return;
                    }
                }
            }
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < coordX.length; i++) {
            coordX[i] = ran.nextInt(470);
        }
        col = new Color(ran.nextInt(256), ran.nextInt(256), ran.nextInt(256));


        while (!Thread.interrupted() && !aus) {
            for (int i = 0; i < coordX.length; i++) {
                int j = coordX[i];
                if (coordY[i] < y) {
                    coordY[i]++;
                } else if (y != 0) {
                    col = new Color(ran.nextInt(256), ran.nextInt(256), ran.nextInt(256));
                    coordY[i] = 0;
                    if (i == 0&&speed>2) {
                        speed--;
                    }
                    coordX[i] = ran.nextInt(x - 30);
                }

            }

            repaint();
            collision();
            try {
                Thread.sleep(speed);
            } catch (InterruptedException ex) {
                break;
            }
        }
    }
}
