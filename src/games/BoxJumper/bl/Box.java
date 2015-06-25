/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.BoxJumper.bl;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.Objects;

/**
 * Box to jump over in BoxJumper
 * 
 * @since 12.06.2015
 * @author Churchy
 */
public class Box extends Rectangle2D.Double {

    private final Color color = Color.RED;

    /**
     * Returns the color of a box
     * 
     */
    public Color getColor() {
        return color;
    }

    /**
     * Makes the box moving to the left
     * 
     * @param distance Number of pixles the box should move
     */
    public void move(double distance) {
        this.x -= distance;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Box other = (Box) obj;
        if (!Objects.equals(this.color, other.color)) {
            return false;
        }
        return true;
    }
}
