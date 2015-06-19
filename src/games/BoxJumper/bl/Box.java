/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.BoxJumper.bl;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Churchy
 */
public class Box extends Rectangle2D.Double {

    private final Color color = Color.RED;

    public Color getColor() {
        return color;
    }

    public void move(double distance) {
        this.x -= distance;
    }
}
