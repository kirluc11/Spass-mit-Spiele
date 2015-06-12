/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.Snake.bl;

import java.awt.Color;

/**
 *
 * @author Lukas
 */
public class SnakeHead extends SnakePart {

    public SnakeHead(Direction dir, double x, double y, double w, double h) {
        super(dir, x, y, w, h);
        color = Color.red;
    }

    public boolean atenFood(SnakeFood food) {
        if (((int) x == (int) food.getX() && (int) y == (int) food.getY()) 
                || ((int) x - 1 == (int) food.getX() && (int) y - 1 == (int) food.getY()) 
                || ((int) x + 1 == (int) food.getX() && (int) y + 1 == (int) food.getY()) 
                || ((int) x + 1 == (int) food.getX() && (int) y == (int) food.getY()) 
                || ((int) x - 1 == (int) food.getX() && (int) y == (int) food.getY()) 
                || ((int) x == (int) food.getX() && (int) y + 1 == (int) food.getY())
                || ((int) x == (int) food.getX() && (int) y - 1 == (int) food.getY())) {
            return true;
        }
        return false;
    }
}
