/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.Snake.bl;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The snake for the game snake
 * 
 * @since 30.04.2015
 * @author Lukas
 */
public class Snake implements Directions, Runnable {

    private final int divider = 20;

    private double windowWidth;
    private double windowHeight;

    private double partWidth;
    private double partHeight;

    private int x;
    private int y;

    private SnakeFood food;

    private LinkedList<SnakePart> snakeParts;

    public Snake(double windowWidth, double windowHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
    }

    /**
     * Inits all things which are necassery for a Snake
     * 
     * @throws IOException 
     */
    public void initSnake() throws IOException {
        snakeParts = new LinkedList<>();
        Random rand = new Random();

        partWidth = windowWidth / divider;
        partHeight = windowHeight / divider;
        x = rand.nextInt((int) (divider / 2)) + divider / 4;
        y = rand.nextInt((int) (divider / 2)) + divider / 4;

        for (int i = 0; i < 4; i++) {
            if (i == 0) {
                SnakeHead head = new SnakeHead(Direction.RIGHT, x * partWidth, y * partHeight, partWidth, partHeight);
                snakeParts.add(head);
            } else {
                SnakePart part = new SnakePart(Direction.RIGHT, x * partWidth - partWidth * i, y * partHeight, partWidth, partHeight);
                snakeParts.add(part);
            }
        }

        newFood();
    }

    /**
     * Initialices a new Food which the Snake can eat
     * 
     * @throws IOException 
     */
    private void newFood() throws IOException {
        Random rand = new Random();
        int xf = 0;
        int yf = 0;
        do {
            xf = rand.nextInt((int) (divider / 2)) + divider / 4;
            yf = rand.nextInt((int) (divider / 2)) + divider / 4;
        } while (foodAppearsInSnake(xf, yf));
        food = new SnakeFood(xf * partWidth, yf * partHeight, partWidth, partHeight);
    }

    /**
     * Checks wheather the Food is under the Snake
     * 
     * @param xf a <code> int </code> to set for the x coordinate of the Food
     * @param yf a <code> int </code> to set for the y coordinate of the Food
     * 
     * @return <code> ture </code> when the food is under the snake
     */
    private boolean foodAppearsInSnake(int xf, int yf) {
        try {
            for (SnakePart snakePart : snakeParts) {
                if (((int) snakePart.getX() == (int) food.getX() && (int) snakePart.getY() == (int) food.getY())
                        || ((int) snakePart.getX() - 1 == (int) food.getX() && (int) snakePart.getY() - 1 == (int) food.getY())
                        || ((int) snakePart.getX() + 1 == (int) food.getX() && (int) snakePart.getY() + 1 == (int) food.getY())
                        || ((int) snakePart.getX() + 1 == (int) food.getX() && (int) snakePart.getY() == (int) food.getY())
                        || ((int) snakePart.getX() - 1 == (int) food.getX() && (int) snakePart.getY() == (int) food.getY())
                        || ((int) snakePart.getX() == (int) food.getX() && (int) snakePart.getY() + 1 == (int) food.getY())
                        || ((int) snakePart.getX() == (int) food.getX() && (int) snakePart.getY() - 1 == (int) food.getY())) {
                    return true;
                }
            }
        } catch (Exception ex) {
        } finally {
            return false;
        }
    }

    /**
     * Adds a new SnakePart at the end of the Snake
     */
    public void addNewSnakePart() {
        SnakePart lastPart = snakeParts.getLast();
        SnakePart newPart = new SnakePart(lastPart.getDir(), SnakePart.calcNextPartX(lastPart), SnakePart.calcNextPartY(lastPart), partWidth, partHeight);
        snakeParts.add(newPart);
    }

    /**
     * Makes the snake move
     * 
     * @return a <code> boolean </code> which is true when the snake chrashed into itself
     * 
     * @throws IOException 
     */
    public boolean move() throws IOException {
        if (snakeParts == null) {
            initSnake();
        }

        for (SnakePart sp : snakeParts) {
            Direction dir = sp.getDir();
            if (dir == null) {
                snakeParts.remove(sp);
            } else {
                switch (dir) {
                    case UP:
                        sp.changeY(-1.);
                        if (sp.getY() < 0 - partHeight / 10) {
                            sp.setY(windowHeight - partHeight);
                        }
                        break;
                    case DOWN:
                        sp.changeY(1.);
                        if (sp.getY() > windowHeight - partHeight + partHeight / 10) {
                            sp.setY(0);
                        }
                        break;
                    case LEFT:
                        sp.changeX(-1.);
                        if (sp.getX() < 0 - partWidth / 10) {
                            sp.setX(windowWidth - partWidth);
                        }
                        break;
                    case RIGHT:
                        sp.changeX(1.);
                        if (sp.getX() > windowWidth - partWidth + partWidth / 10) {
                            sp.setX(0);
                        }
                        break;
                }
            }
        }

        SnakeHead head = (SnakeHead) snakeParts.getFirst();

        for (int i = 1; i < snakeParts.size(); i++) {
            SnakePart part = snakeParts.get(i);
            if (head.equals(part)) {
                return false;
            }
        }

        if (head.eatenFood(food)) {
            addNewSnakePart();
            Thread t = new Thread(this);
            t.start();
        }

        for (int i = snakeParts.size() - 1; i > 0; i--) {
            snakeParts.get(i).setDir(snakeParts.get(i - 1).getDir());
        }

        return true;
    }

    /**
     * Changes direction of the snake
     * 
     * @param dir a <code> Direction </code> in which the snake should move
     */
    public void changeDirection(Direction dir) {
        if (snakeParts != null) {
            SnakeHead head = (SnakeHead) snakeParts.getFirst();
            if (head.getDir().equals(Direction.UP) && !dir.equals(Direction.DOWN)) {
                snakeParts.getFirst().setDir(dir);
            } else {
                if (head.getDir().equals(Direction.DOWN) && !dir.equals(Direction.UP)) {
                    snakeParts.getFirst().setDir(dir);
                } else {
                    if (head.getDir().equals(Direction.LEFT) && !dir.equals(Direction.RIGHT)) {
                        snakeParts.getFirst().setDir(dir);
                    } else {
                        if (head.getDir().equals(Direction.RIGHT) && !dir.equals(Direction.LEFT)) {
                            snakeParts.getFirst().setDir(dir);
                        }
                    }
                }
            }
        }
    }

    public double getWidth() {
        return windowWidth;
    }
    
    public void setWidth(double windowWidth) {
        this.windowWidth = windowWidth;
    }

    public double getHeight() {
        return windowHeight;
    }

    public void setHeight(double windowHeight) {
        this.windowHeight = windowHeight;
    }

    public LinkedList<SnakePart> getSnakeParts() {
        return snakeParts;
    }

    public void setSnakeParts(LinkedList<SnakePart> snakeParts) {
        this.snakeParts = snakeParts;
    }

    public SnakeFood getFood() {
        return food;
    }

    public void setFood(SnakeFood food) {
        this.food = food;
    }

    /**
     * Run method to check if the food is not under the snake in a own Thread
     * to make the performance better
     */
    @Override
    public void run() {
        try {
            newFood();
        } catch (IOException ex) {
            Logger.getLogger(Snake.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
