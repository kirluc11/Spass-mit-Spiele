/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.Snake.bl;

import java.awt.Color;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Lukas
 */
public class SnakeFood extends Ellipse2D.Double {

    Color color = Color.BLUE;
    double w;
    double h;
    
    private String path = System.getProperty("user.dir")
                + File.separator + "src"
                + File.separator + "res"
                + File.separator + "pictures";

    Image img;

    /**
     * Params are position and size of the food
     * @param x
     * @param y
     * @param w
     * @param h
     * @throws IOException 
     */
    public SnakeFood(double x, double y, double w, double h) throws IOException {
        super(x, y, w, h);
        this.img = ImageIO.read(new File(path + File.separator + "wurst.png"));
        this.w = w;
        this.h = h;
    }

    
    /**
     * Returns the color of the food
     * @return 
     */
    public Color getColor() {
        return color;
    }

    /**
     * Returns the selected image
     * @return 
     */
    public Image getImg() {
        return img;
    }

    /**
     * Sets the selected image
     * @param img 
     */
    public void setImg(Image img) {
        this.img = img;
    }
}
