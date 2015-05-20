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
 * @author Churchy
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

    public SnakeFood(double x, double y, double w, double h) throws IOException {
        super(x, y, w, h);
        this.img = ImageIO.read(new File(path + File.separator + "wurst.png"));
        this.w = w;
        this.h = h;
    }

    public Color getColor() {
        return color;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }
}
