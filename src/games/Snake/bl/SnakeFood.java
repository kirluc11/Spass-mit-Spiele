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
 * The food for the snake
 *
 * @since 30.04.2015
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
     * @param x a <code> double </code> which is the x coordinate of the food
     * @param y a <code> double </code> which is the y coordinate of the food
     * @param w a <code> double </code> which is the width of the food
     * @param h a <code> double </code> which is the height of the food
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
     *
     * @return the <code> Color </code> of the food
     */
    public Color getColor() {
        return color;
    }

    /**
     * Returns the selected image for the food
     *
     * @return the <code> Image </code> of the food
     */
    public Image getImg() {
        return img;
    }

    /**
     * Sets the selected image
     *
     * @param img a <code> Image </code> for the food
     */
    public void setImg(Image img) {
        this.img = img;
    }
}
