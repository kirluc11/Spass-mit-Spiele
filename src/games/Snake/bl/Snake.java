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
        
        int devider = 15;
        
        double w = windowWidth / devider;
        double h = windowHeight / devider;
        double x = rand.nextInt((int) (w - 4));
        double y = rand.nextInt((int) (h - 4));

        for (int i = 0; i < 10; i++) {
            if (i == 0) {
                SnakeHead head = new SnakeHead(Direction.RIGHT, x * w, y * h, w, h);
                snakeParts.add(head);
            } else {
                SnakePart part = new SnakePart(Direction.RIGHT, x * w - w * i, y * h, w, h);
                snakeParts.add(part);
            }
        }
    }

    public void move() {
        if (snakeParts == null) {
            initSnake(width, height);
        }

        for (SnakePart sp : snakeParts) {
            Direction dir = sp.getDir();
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
        
        SnakeHead head = (SnakeHead) snakeParts.getFirst();
        
        for (int i = 1; i < snakeParts.size(); i++) {
            SnakePart part = snakeParts.get(i);
            if(head.equals(part))
            {
                System.out.println("dead: " + i);
            }
        }

        for (int i = snakeParts.size() - 1; i > 0; i--) {
            snakeParts.get(i).setDir(snakeParts.get(i - 1).getDir());
        }
    }

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
