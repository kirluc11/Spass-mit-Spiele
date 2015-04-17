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
 * @author Lukas
 */
public class TicTacToeGewinnabfrage {

    private LinkedList<JLabel> labels;
    private int sieger;

    public TicTacToeGewinnabfrage(LinkedList<JLabel> labels) {
        this.labels = labels;
    }

    public boolean isOver() {
        String text = "X";
        sieger = 0;
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
        sieger = 1;
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

    public boolean isUnendschieden() {
        for (JLabel jLabel : labels) {
            if (jLabel.getBackground() == Color.black) {
                return false;
            }
        }
        return true;
    }
    
    public int getSieger()
    {
        return sieger;
    }
}
