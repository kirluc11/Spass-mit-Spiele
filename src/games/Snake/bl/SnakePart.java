/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.Snake.bl;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

/**
 * The food for the snake
 *
 * @since 30.04.2015
 * @author Lukas
 */
public class SnakePart extends Rectangle2D.Double implements Directions {

    Color color = Color.YELLOW;
    double w;
    double h;
    Direction dir;

    /**
     * Calculates the x coordinate where the next part needs to be set
     *
     * @param part the last <code> SnakePart </code> of the <code> Snake </code>
     * @return a <code> double </code> which is the x coordinate for a new <code> SnakePart
     * </code>
     */
    public static double calcNextPartX(SnakePart part) {
        if (part.getDir() == Direction.UP || part.getDir() == Direction.DOWN) {
            return part.getX();
        }
        if (part.getDir() == Direction.RIGHT) {
            return part.getX() - part.getW();
        }
        if (part.getDir() == Direction.LEFT) {
            return part.getX() + part.getW();
        }
        return -1;
    }

    /**
     * Calculates the y coordinate where the next part needs to be set
     *
     * @param part the last <code> SnakePart </code> of the <code> Snake </code>
     * @return a <code> double </code> which is the y coordinate for a new <code> SnakePart
     * </code>
     */
    public static double calcNextPartY(SnakePart part) {
        if (part.getDir() == Direction.LEFT || part.getDir() == Direction.RIGHT) {
            return part.getY();
        }
        if (part.getDir() == Direction.DOWN) {
            return part.getY() - part.getH();
        }
        if (part.getDir() == Direction.UP) {
            return part.getY() + part.getH();
        }
        return -1;
    }

    /**
     * Constructor for <code> SnakePart </code>
     *
     * @param dir is the <code> Direction </code> in which the <code> SnakePart
     * </code> has to move
     * @param x a <code> double </code> which is the x coordinate of the food
     * @param y a <code> double </code> which is the y coordinate of the food
     * @param w a <code> double </code> which is the width of the food
     * @param h a <code> double </code> which is the height of the food
     * @throws IOException
     */
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
        if ((int) this.x != (int) other.x) {
            return false;
        }
        if ((int) this.y != (int) other.y) {
            return false;
        }
        return true;
    }

    public double getW() {
        return w;
    }

    public void setW(double w) {
        this.w = w;
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
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

    @Override
    public String toString() {
        return String.format("dir=%s, x=%1.2f, y=%1.2f, w=%1.2f, h=%1.2f", dir, x, y, w, h);
    }
}
