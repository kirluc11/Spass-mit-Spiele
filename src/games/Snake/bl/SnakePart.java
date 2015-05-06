/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.Snake.bl;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Churchy
 */
public class SnakePart extends Rectangle2D.Double implements Directions {

    Color color = Color.YELLOW;
    double w;
    double h;
    Direction dir;

    public SnakePart(Direction dir, double x, double y, double w, double h) {
        super(x, y, w, h);
        this.w = w;
        this.h = h;
        this.dir = dir;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        final SnakePart other = (SnakePart) obj;
        if (java.lang.Double.doubleToLongBits(this.x) != java.lang.Double.doubleToLongBits(other.x)) {
            return false;
        }
        if (java.lang.Double.doubleToLongBits(this.y) != java.lang.Double.doubleToLongBits(other.y)) {
            return false;
        }
        return true;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void changeX(double zuX) {
        this.setX(this.getX() + zuX * w);
    }

    public void changeY(double zuY) {
        this.setY(this.getY() + zuY * h);
    }

    public Direction getDir() {
        return dir;
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }
}
