/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package games.tictactoe.bl;

import java.awt.Color;
import java.util.LinkedList;
import javax.swing.JLabel;

/**
 * 
 * 
 * @since 16.04.2015
 * @author Lukas
 */
public class TicTacToeGewinnabfrage {

    private LinkedList<JLabel> labels;
    private int winner;

    public TicTacToeGewinnabfrage(LinkedList<JLabel> labels) {
        this.labels = labels;
    }
    
    /**
     * Checks if the game is over
     * @return <code> boolean </code> which is true when the game is over
     */
    public boolean isOver() {
        String text = "X";
        winner = 0;
        if (labels.get(0).getText().equals(text) && labels.get(1).getText().equals(text) && labels.get(2).getText().equals(text)) {
            return true;
        }
        if (labels.get(3).getText().equals(text) && labels.get(4).getText().equals(text) && labels.get(5).getText().equals(text)) {
            return true;
        }
        if (labels.get(6).getText().equals(text) && labels.get(7).getText().equals(text) && labels.get(8).getText().equals(text)) {
            return true;
        }
        if (labels.get(0).getText().equals(text) && labels.get(3).getText().equals(text) && labels.get(6).getText().equals(text)) {
            return true;
        }
        if (labels.get(1).getText().equals(text) && labels.get(4).getText().equals(text) && labels.get(7).getText().equals(text)) {
            return true;
        }
        if (labels.get(2).getText().equals(text) && labels.get(5).getText().equals(text) && labels.get(8).getText().equals(text)) {
            return true;
        }
        if (labels.get(0).getText().equals(text) && labels.get(4).getText().equals(text) && labels.get(8).getText().equals(text)) {
            return true;
        }
        if (labels.get(2).getText().equals(text) && labels.get(4).getText().equals(text) && labels.get(6).getText().equals(text)) {
            return true;
        }

        text = "O";
        winner = 1;
        if (labels.get(0).getText().equals(text) && labels.get(1).getText().equals(text) && labels.get(2).getText().equals(text)) {
            return true;
        }
        if (labels.get(3).getText().equals(text) && labels.get(4).getText().equals(text) && labels.get(5).getText().equals(text)) {
            return true;
        }
        if (labels.get(6).getText().equals(text) && labels.get(7).getText().equals(text) && labels.get(8).getText().equals(text)) {
            return true;
        }
        if (labels.get(0).getText().equals(text) && labels.get(3).getText().equals(text) && labels.get(6).getText().equals(text)) {
            return true;
        }
        if (labels.get(1).getText().equals(text) && labels.get(4).getText().equals(text) && labels.get(7).getText().equals(text)) {
            return true;
        }
        if (labels.get(2).getText().equals(text) && labels.get(5).getText().equals(text) && labels.get(8).getText().equals(text)) {
            return true;
        }
        if (labels.get(0).getText().equals(text) && labels.get(4).getText().equals(text) && labels.get(8).getText().equals(text)) {
            return true;
        }
        if (labels.get(2).getText().equals(text) && labels.get(4).getText().equals(text) && labels.get(6).getText().equals(text)) {
            return true;
        }
        
        return false;
    }
    
    /**
     * Checks if the game is draw
     * @return a <code> boolean </code> which is true when the game is draw
     */
    public boolean isDraw() {
        for (JLabel jLabel : labels) {
            if (jLabel.getBackground() == Color.black) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Returns the winner of the game
     * @return a <code> int </code> which is 0 when X is the winner
     */
    public int getWinner()
    {
        return winner;
    }
}
