/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vierGewinnt.gui;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JLabel;

/**
 *
 * @author Churchy
 */
public class VierGewinntLabel extends JLabel {

    String path = System.getProperty("user.dir")
            + File.separator + "src"
            + File.separator + "res"
            + File.separator + "pictures";

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        try {
            g.drawImage(ImageIO.read(new File(path + File.separator + "vierGewinntGridHole2.png")), 0, 0, getWidth(), getHeight(), null);
        } catch (IOException ex) {
        }
    }
}
