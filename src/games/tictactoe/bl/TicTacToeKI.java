/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package games.tictactoe.bl;

import java.awt.Color;
import java.util.LinkedList;
import java.util.Random;
import javax.swing.JLabel;

/**
 *
 * @author Lukas
 */
public class TicTacToeKI {

    /**
     * An intelligent KI made by Lukas
     *
     * @param labels a <code> LinkedList </code> including every single <code> JLabel
     * </code> which is used in the game.
     * @return the <code> JLabel </code> where the O should be set next.
     */
    public JLabel nextStep(LinkedList<JLabel> labels) {
        int[] indices = new int[3];
        String a = "O";
        String b = "X";
        indices[0] = 0;
        indices[1] = 1;
        indices[2] = 2;
        if (labels.get(indices[0]).getText().equals(a) && labels.get(indices[1]).getText().equals(a) && !labels.get(indices[2]).getText().equals(b)) {
            return labels.get(indices[2]);
        }
        if (labels.get(indices[0]).getText().equals(a) && labels.get(indices[2]).getText().equals(a) && !labels.get(indices[1]).getText().equals(b)) {
            return labels.get(indices[1]);
        }
        if (labels.get(indices[1]).getText().equals(a) && labels.get(indices[2]).getText().equals(a) && !labels.get(indices[0]).getText().equals(b)) {
            return labels.get(indices[0]);
        }

        indices[0] = 3;
        indices[1] = 4;
        indices[2] = 5;
        if (labels.get(indices[0]).getText().equals(a) && labels.get(indices[1]).getText().equals(a) && !labels.get(indices[2]).getText().equals(b)) {
            return labels.get(indices[2]);
        }
        if (labels.get(indices[0]).getText().equals(a) && labels.get(indices[2]).getText().equals(a) && !labels.get(indices[1]).getText().equals(b)) {
            return labels.get(indices[1]);
        }
        if (labels.get(indices[1]).getText().equals(a) && labels.get(indices[2]).getText().equals(a) && !labels.get(indices[0]).getText().equals(b)) {
            return labels.get(indices[0]);
        }

        indices[0] = 6;
        indices[1] = 7;
        indices[2] = 8;
        if (labels.get(indices[0]).getText().equals(a) && labels.get(indices[1]).getText().equals(a) && !labels.get(indices[2]).getText().equals(b)) {
            return labels.get(indices[2]);
        }
        if (labels.get(indices[0]).getText().equals(a) && labels.get(indices[2]).getText().equals(a) && !labels.get(indices[1]).getText().equals(b)) {
            return labels.get(indices[1]);
        }
        if (labels.get(indices[1]).getText().equals(a) && labels.get(indices[2]).getText().equals(a) && !labels.get(indices[0]).getText().equals(b)) {
            return labels.get(indices[0]);
        }

        indices[0] = 0;
        indices[1] = 3;
        indices[2] = 6;
        if (labels.get(indices[0]).getText().equals(a) && labels.get(indices[1]).getText().equals(a) && !labels.get(indices[2]).getText().equals(b)) {
            return labels.get(indices[2]);
        }
        if (labels.get(indices[0]).getText().equals(a) && labels.get(indices[2]).getText().equals(a) && !labels.get(indices[1]).getText().equals(b)) {
            return labels.get(indices[1]);
        }
        if (labels.get(indices[1]).getText().equals(a) && labels.get(indices[2]).getText().equals(a) && !labels.get(indices[0]).getText().equals(b)) {
            return labels.get(indices[0]);
        }

        indices[0] = 1;
        indices[1] = 4;
        indices[2] = 7;
        if (labels.get(indices[0]).getText().equals(a) && labels.get(indices[1]).getText().equals(a) && !labels.get(indices[2]).getText().equals(b)) {
            return labels.get(indices[2]);
        }
        if (labels.get(indices[0]).getText().equals(a) && labels.get(indices[2]).getText().equals(a) && !labels.get(indices[1]).getText().equals(b)) {
            return labels.get(indices[1]);
        }
        if (labels.get(indices[1]).getText().equals(a) && labels.get(indices[2]).getText().equals(a) && !labels.get(indices[0]).getText().equals(b)) {
            return labels.get(indices[0]);
        }

        indices[0] = 2;
        indices[1] = 5;
        indices[2] = 8;
        if (labels.get(indices[0]).getText().equals(a) && labels.get(indices[1]).getText().equals(a) && !labels.get(indices[2]).getText().equals(b)) {
            return labels.get(indices[2]);
        }
        if (labels.get(indices[0]).getText().equals(a) && labels.get(indices[2]).getText().equals(a) && !labels.get(indices[1]).getText().equals(b)) {
            return labels.get(indices[1]);
        }
        if (labels.get(indices[1]).getText().equals(a) && labels.get(indices[2]).getText().equals(a) && !labels.get(indices[0]).getText().equals(b)) {
            return labels.get(indices[0]);
        }

        indices[0] = 0;
        indices[1] = 4;
        indices[2] = 8;
        if (labels.get(indices[0]).getText().equals(a) && labels.get(indices[1]).getText().equals(a) && !labels.get(indices[2]).getText().equals(b)) {
            return labels.get(indices[2]);
        }
        if (labels.get(indices[0]).getText().equals(a) && labels.get(indices[2]).getText().equals(a) && !labels.get(indices[1]).getText().equals(b)) {
            return labels.get(indices[1]);
        }
        if (labels.get(indices[1]).getText().equals(a) && labels.get(indices[2]).getText().equals(a) && !labels.get(indices[0]).getText().equals(b)) {
            return labels.get(indices[0]);
        }

        indices[0] = 2;
        indices[1] = 4;
        indices[2] = 6;
        if (labels.get(indices[0]).getText().equals(a) && labels.get(indices[1]).getText().equals(a) && !labels.get(indices[2]).getText().equals(b)) {
            return labels.get(indices[2]);
        }
        if (labels.get(indices[0]).getText().equals(a) && labels.get(indices[2]).getText().equals(a) && !labels.get(indices[1]).getText().equals(b)) {
            return labels.get(indices[1]);
        }
        if (labels.get(indices[1]).getText().equals(a) && labels.get(indices[2]).getText().equals(a) && !labels.get(indices[0]).getText().equals(b)) {
            return labels.get(indices[0]);
        }

        a = "X";
        b = "O";
        indices[0] = 0;
        indices[1] = 1;
        indices[2] = 2;
        if (labels.get(indices[0]).getText().equals(a) && labels.get(indices[1]).getText().equals(a) && !labels.get(indices[2]).getText().equals(b)) {
            return labels.get(indices[2]);
        }
        if (labels.get(indices[0]).getText().equals(a) && labels.get(indices[2]).getText().equals(a) && !labels.get(indices[1]).getText().equals(b)) {
            return labels.get(indices[1]);
        }
        if (labels.get(indices[1]).getText().equals(a) && labels.get(indices[2]).getText().equals(a) && !labels.get(indices[0]).getText().equals(b)) {
            return labels.get(indices[0]);
        }

        indices[0] = 3;
        indices[1] = 4;
        indices[2] = 5;
        if (labels.get(indices[0]).getText().equals(a) && labels.get(indices[1]).getText().equals(a) && !labels.get(indices[2]).getText().equals(b)) {
            return labels.get(indices[2]);
        }
        if (labels.get(indices[0]).getText().equals(a) && labels.get(indices[2]).getText().equals(a) && !labels.get(indices[1]).getText().equals(b)) {
            return labels.get(indices[1]);
        }
        if (labels.get(indices[1]).getText().equals(a) && labels.get(indices[2]).getText().equals(a) && !labels.get(indices[0]).getText().equals(b)) {
            return labels.get(indices[0]);
        }

        indices[0] = 6;
        indices[1] = 7;
        indices[2] = 8;
        if (labels.get(indices[0]).getText().equals(a) && labels.get(indices[1]).getText().equals(a) && !labels.get(indices[2]).getText().equals(b)) {
            return labels.get(indices[2]);
        }
        if (labels.get(indices[0]).getText().equals(a) && labels.get(indices[2]).getText().equals(a) && !labels.get(indices[1]).getText().equals(b)) {
            return labels.get(indices[1]);
        }
        if (labels.get(indices[1]).getText().equals(a) && labels.get(indices[2]).getText().equals(a) && !labels.get(indices[0]).getText().equals(b)) {
            return labels.get(indices[0]);
        }

        indices[0] = 0;
        indices[1] = 3;
        indices[2] = 6;
        if (labels.get(indices[0]).getText().equals(a) && labels.get(indices[1]).getText().equals(a) && !labels.get(indices[2]).getText().equals(b)) {
            return labels.get(indices[2]);
        }
        if (labels.get(indices[0]).getText().equals(a) && labels.get(indices[2]).getText().equals(a) && !labels.get(indices[1]).getText().equals(b)) {
            return labels.get(indices[1]);
        }
        if (labels.get(indices[1]).getText().equals(a) && labels.get(indices[2]).getText().equals(a) && !labels.get(indices[0]).getText().equals(b)) {
            return labels.get(indices[0]);
        }

        indices[0] = 1;
        indices[1] = 4;
        indices[2] = 7;
        if (labels.get(indices[0]).getText().equals(a) && labels.get(indices[1]).getText().equals(a) && !labels.get(indices[2]).getText().equals(b)) {
            return labels.get(indices[2]);
        }
        if (labels.get(indices[0]).getText().equals(a) && labels.get(indices[2]).getText().equals(a) && !labels.get(indices[1]).getText().equals(b)) {
            return labels.get(indices[1]);
        }
        if (labels.get(indices[1]).getText().equals(a) && labels.get(indices[2]).getText().equals(a) && !labels.get(indices[0]).getText().equals(b)) {
            return labels.get(indices[0]);
        }

        indices[0] = 2;
        indices[1] = 5;
        indices[2] = 8;
        if (labels.get(indices[0]).getText().equals(a) && labels.get(indices[1]).getText().equals(a) && !labels.get(indices[2]).getText().equals(b)) {
            return labels.get(indices[2]);
        }
        if (labels.get(indices[0]).getText().equals(a) && labels.get(indices[2]).getText().equals(a) && !labels.get(indices[1]).getText().equals(b)) {
            return labels.get(indices[1]);
        }
        if (labels.get(indices[1]).getText().equals(a) && labels.get(indices[2]).getText().equals(a) && !labels.get(indices[0]).getText().equals(b)) {
            return labels.get(indices[0]);
        }

        indices[0] = 0;
        indices[1] = 4;
        indices[2] = 8;
        if (labels.get(indices[0]).getText().equals(a) && labels.get(indices[1]).getText().equals(a) && !labels.get(indices[2]).getText().equals(b)) {
            return labels.get(indices[2]);
        }
        if (labels.get(indices[0]).getText().equals(a) && labels.get(indices[2]).getText().equals(a) && !labels.get(indices[1]).getText().equals(b)) {
            return labels.get(indices[1]);
        }
        if (labels.get(indices[1]).getText().equals(a) && labels.get(indices[2]).getText().equals(a) && !labels.get(indices[0]).getText().equals(b)) {
            return labels.get(indices[0]);
        }

        indices[0] = 2;
        indices[1] = 4;
        indices[2] = 6;
        if (labels.get(indices[0]).getText().equals(a) && labels.get(indices[1]).getText().equals(a) && !labels.get(indices[2]).getText().equals(b)) {
            return labels.get(indices[2]);
        }
        if (labels.get(indices[0]).getText().equals(a) && labels.get(indices[2]).getText().equals(a) && !labels.get(indices[1]).getText().equals(b)) {
            return labels.get(indices[1]);
        }
        if (labels.get(indices[1]).getText().equals(a) && labels.get(indices[2]).getText().equals(a) && !labels.get(indices[0]).getText().equals(b)) {
            return labels.get(indices[0]);
        }

        Random rand = new Random();
        int zz = 0;
        do {
            zz = rand.nextInt(9);
        } while (!labels.get(zz).getBackground().equals(Color.black));

        return labels.get(zz);
    }
}
