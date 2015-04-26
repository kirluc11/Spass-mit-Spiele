/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vierGewinnt.gui;

import java.awt.Graphics;
import java.awt.LayoutManager;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Churchy
 */
public class VierGewinntLabelPanel extends JPanel {

    String path = System.getProperty("user.dir")
            + File.separator + "src"
            + File.separator + "res"
            + File.separator + "pictures";

    public VierGewinntLabelPanel(LayoutManager layout, boolean isDoubleBuffered) {
        super(layout, isDoubleBuffered);
    }

    public VierGewinntLabelPanel(LayoutManager layout) {
        super(layout);
    }

    public VierGewinntLabelPanel(boolean isDoubleBuffered) {
        super(isDoubleBuffered);
    }

    public VierGewinntLabelPanel() {
    }

    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        try {
            g.drawImage(ImageIO.read(new File(path + File.separator + "vierGewinntGrid.png")), 0, 0, getWidth(), getHeight(), null);
        } catch (IOException ex) {
        }
    }
}
