package games.Snake.bl;

import java.awt.Color;

/**
 * The head of a snake
 *
 * @since 30.04.2015
 * @author Lukas
 */
public class SnakeHead extends SnakePart {

    /**
     * Constructor for <code> SnakePart </code>
     *
     * @param dir is the <code> Direction </code> in which the <code> SnakePart
     * </code> has to move
     * @param x a <code> double </code> which is the x coordinate of the food
     * @param y a <code> double </code> which is the y coordinate of the food
     * @param w a <code> double </code> which is the width of the food
     * @param h a <code> double </code> which is the height of the food
     */
    public SnakeHead(Direction dir, double x, double y, double w, double h) {
        super(dir, x, y, w, h);
        color = Color.red;
    }

    /**
     * Detects if food was eaten by the snake
     * @param food a <code> SnakeFood </code> which probably was eaten
     * @return a <code> boolean </code> which is true when the <code> SnakeFood </code> was eaten
     */
    public boolean eatenFood(SnakeFood food) {
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
