/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connectFour.bl;

import java.awt.Color;
import javax.swing.JLabel;

/**
 *
 * @author Lukas
 */
public class ConnectFourBL {

    private JLabel[][] labels;

    public ConnectFourBL(JLabel[][] labels) {
        this.labels = labels;
    }

    /**
     * Checks if the game is over
     *
     * @return a <code> boolean </code> which is true when the game is over
     */
    public boolean isOver() {
        Color col = null;
        int count = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (labels[i][j].getBackground().equals(col)) {
                    count++;
                } else {
                    count = 1;
                    col = labels[i][j].getBackground();
                }
                if (count == 4 && !Color.black.equals(col)) {
                    return true;
                }
            }
            count = 0;
        }

        for (int j = 0; j < 7; j++) {
            for (int i = 0; i < 6; i++) {
                if (labels[i][j].getBackground().equals(col)) {
                    count++;
                } else {
                    count = 1;
                    col = labels[i][j].getBackground();
                }
                if (count == 4 && !Color.black.equals(col)) {
                    return true;
                }
            }
            count = 0;
        }

        /**
         * Diagonalen von rechts oben nach links unten
         */
        int max = 4;
        for (int i = 0; i < max; i++) {
            if (labels[i][max - i].getBackground().equals(col)) {
                count++;
            } else {
                count = 1;
                col = labels[i][max - i].getBackground();
            }
            if (count == 4 && !Color.black.equals(col)) {
                return true;
            }
        }
        count = 0;

        max = 4;
        for (int i = 0; i <= max; i++) {
            if (labels[i][max - i].getBackground().equals(col)) {
                count++;
            } else {
                count = 1;
                col = labels[i][max - i].getBackground();
            }
            if (count == 4 && !Color.black.equals(col)) {
                return true;
            }
        }
        count = 0;

        max = 5;
        for (int i = 0; i <= max; i++) {
            if (labels[i][max - i].getBackground().equals(col)) {
                count++;
            } else {
                count = 1;
                col = labels[i][max - i].getBackground();
            }
            if (count == 4 && !Color.black.equals(col)) {
                return true;
            }
        }
        count = 0;

        max = 6;
        for (int i = 0; i < max; i++) {
            if (labels[i][max - i].getBackground().equals(col)) {
                count++;
            } else {
                count = 1;
                col = labels[i][max - i].getBackground();
            }
            if (count == 4 && !Color.black.equals(col)) {
                return true;
            }
        }
        count = 0;

        max = 6;
        for (int i = 1; i < max; i++) {
            if (labels[i][max - i].getBackground().equals(col)) {
                count++;
            } else {
                count = 1;
                col = labels[i][max - i].getBackground();
            }
            if (count == 4 && !Color.black.equals(col)) {
                return true;
            }
        }
        count = 0;

        max = 6;
        for (int i = 2; i < max; i++) {
            if (labels[i][max - i + 2].getBackground().equals(col)) {
                count++;
            } else {
                count = 1;
                col = labels[i][max - i + 2].getBackground();
            }
            if (count == 4 && !Color.black.equals(col)) {
                return true;
            }
        }
        count = 0;

        /**
         * Diagonalen von links oben nach rechts unten
         */
        max = 5;
        for (int i = 2; i <= max; i++) {
            if (labels[i][i - 2].getBackground().equals(col)) {
                count++;
            } else {
                count = 1;
                col = labels[i][i - 2].getBackground();
            }
            if (count == 4 && !Color.black.equals(col)) {
                return true;
            }
        }
        count = 0;

        max = 5;
        for (int i = 1; i <= max; i++) {
            if (labels[i][i - 1].getBackground().equals(col)) {
                count++;
            } else {
                count = 1;
                col = labels[i][i - 1].getBackground();
            }
            if (count == 4 && !Color.black.equals(col)) {
                return true;
            }
        }
        count = 0;

        max = 5;
        for (int i = 0; i <= max; i++) {
            if (labels[i][i].getBackground().equals(col)) {
                count++;
            } else {
                count = 1;
                col = labels[i][i].getBackground();
            }
            if (count == 4 && !Color.black.equals(col)) {
                return true;
            }
        }
        count = 0;

        max = 5;
        for (int i = 0; i <= max; i++) {
            if (labels[i][i + 1].getBackground().equals(col)) {
                count++;
            } else {
                count = 1;
                col = labels[i][i + 1].getBackground();
            }
            if (count == 4 && !Color.black.equals(col)) {
                return true;
            }
        }
        count = 0;

        max = 5;
        for (int i = 0; i < max; i++) {
            if (labels[i][i + 2].getBackground().equals(col)) {
                count++;
            } else {
                count = 1;
                col = labels[i][i + 2].getBackground();
            }
            if (count == 4 && !Color.black.equals(col)) {
                return true;
            }
        }
        count = 0;

        max = 4;
        for (int i = 1; i < max; i++) {
            if (labels[i][i + 3].getBackground().equals(col)) {
                count++;
            } else {
                count = 1;
                col = labels[i][i + 3].getBackground();
            }
            if (count == 4 && !Color.black.equals(col)) {
                return true;
            }
        }
        count = 0;

        return false;
    }

    /**
     * Checks if it's a draw
     *
     * @return a <code> boolean </code> which is true when the game is draw
     */
    public boolean isDraw() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (labels[i][j].getBackground().equals(Color.black)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks if every panel is black
     *
     * @return a <code> boolean </code> which is true when every panel is black
     */
    public boolean isEverythingBlack() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (!labels[i][j].getBackground().equals(Color.black)) {
                    return false;
                }
            }
        }
        return true;
    }
}
