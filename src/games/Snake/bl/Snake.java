/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.Snake.bl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author Churchy
 */
public class Snake implements Directions {

    private int width;
    private int height;

    private LinkedList<SnakePart> snakeParts;

    public Snake(int windowWidth, int windowHeight) {
        width = windowWidth;
        height = windowHeight;
    }

    public void initSnake(int windowWidth, int windowHeight) {
        snakeParts = new LinkedList<>();
        Random rand = new Random();
        double w = windowWidth / 20;
        double h = windowHeight / 20;
        double x = rand.nextInt(windowWidth / 20 - 4);
        double y = rand.nextInt(windowHeight / 20 - 4);

        SnakeHead head = new SnakeHead(Direction.RIGHT, x * w, y * h, w, h);
        SnakePart p1 = new SnakePart(Direction.RIGHT, x * w - w, y * h, w, h);
        SnakePart p2 = new SnakePart(Direction.RIGHT, x * w - w * 2, y * h, w, h);
        SnakePart p3 = new SnakePart(Direction.RIGHT, x * w - w * 3, y * h, w, h);

        snakeParts.add(head);
        snakeParts.add(p1);
        snakeParts.add(p2);
        snakeParts.add(p3);
    }

    public void move() {
        if (snakeParts == null) {
            initSnake(width, height);
        }

        for (SnakePart sp : snakeParts) {
            Direction dir = sp.getDir();
            System.out.println(dir);
            if (dir == null) {
                snakeParts.remove(sp);
            } else {
                switch (dir) {
                    case UP:
                        sp.changeY(-1.);
                        break;
                    case DOWN:
                        sp.changeY(1.);
                        break;
                    case LEFT:
                        sp.changeX(-1.);
                        break;
                    case RIGHT:
                        sp.changeX(1.);
                        break;
                }
            }
        }
        System.out.println("\n");

        for (int i = snakeParts.size() - 1; i > 0; i--) {
            snakeParts.get(i).setDir(snakeParts.get(i - 1).getDir());
        }
    }

    public void changeDirection(Direction dir) {
        snakeParts.getFirst().setDir(dir);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public LinkedList<SnakePart> getSnakeParts() {
        return snakeParts;
    }

    public void setSnakeParts(LinkedList<SnakePart> snakeParts) {
        this.snakeParts = snakeParts;
    }
}
