package games.hangman.gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import gui.PlayerGUI;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
 * Main panel of the game Hangman
 *
 * @author Marcel, Lukas
 */
public class HangmanPanel extends javax.swing.JPanel
{

    
    private int art = 0;
    private String inp = "";
    char[] c;
    private String output = "";
    private char[] c1;
    private PlayerGUI pgui;
    //Variable to check if the player has won
    private int lenght = 0;
    private boolean gameRunning;
    //To save already tried letters
    private HashSet<Character> usedChar;

    public HangmanPanel(PlayerGUI pgui)
    {
        this.pgui = pgui;
        initComponents();
        this.setBorder(new TitledBorder(new EmptyBorder(5, 0, 0, 0), "Hangman", TitledBorder.CENTER, TitledBorder.ABOVE_TOP));
    }
    
    
    /**
     * This class draws the lines of the hanged man
     * @param g 
     */
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics gp = paZeichne.getGraphics();
        gp.setColor(Color.white);
        int x = paZeichne.getWidth();
        int y = paZeichne.getHeight();
        switch (art)
        {
            case 1:
                gp.drawLine(x / 3, y - y / 8, (x / 3) * 2, y - y / 8);
                break;
            case 2:
                gp.drawLine(x / 3, y - y / 8, (x / 3) * 2, y - y / 8);
                gp.drawLine(x / 3, y - y / 8, x / 3, y - y / 4);
                gp.drawLine((x / 3) * 2, y - y / 8, (x / 3) * 2, y - y / 4);
                break;
            case 3:
                gp.drawLine(x / 3, y - y / 8, (x / 3) * 2, y - y / 8);
                gp.drawLine(x / 3, y - y / 8, x / 3, y - y / 4);
                gp.drawLine((x / 3) * 2, y - y / 8, (x / 3) * 2, y - y / 4);
                gp.drawLine(x / 3, y - y / 4, (x / 3) * 2, y - y / 4);
                break;
            case 4:
                gp.drawLine(x / 3, y - y / 8, (x / 3) * 2, y - y / 8);
                gp.drawLine(x / 3, y - y / 8, x / 3, y - y / 4);
                gp.drawLine((x / 3) * 2, y - y / 8, (x / 3) * 2, y - y / 4);
                gp.drawLine(x / 3, y - y / 4, (x / 3) * 2, y - y / 4);
                gp.drawLine((x / 3) + (x / 10), y - y / 4, (x / 3) + (x / 10), y / 4);
                break;
            case 5:
                gp.drawLine(x / 3, y - y / 8, (x / 3) * 2, y - y / 8);
                gp.drawLine(x / 3, y - y / 8, x / 3, y - y / 4);
                gp.drawLine((x / 3) * 2, y - y / 8, (x / 3) * 2, y - y / 4);
                gp.drawLine(x / 3, y - y / 4, (x / 3) * 2, y - y / 4);
                gp.drawLine((x / 3) + (x / 10), y - y / 4, (x / 3) + (x / 10), y / 4);
                gp.drawLine((x / 3) + (x / 10), y / 4, (x / 2) + (x / 12), y / 4);
                break;
            case 6:
                gp.drawLine(x / 3, y - y / 8, (x / 3) * 2, y - y / 8);
                gp.drawLine(x / 3, y - y / 8, x / 3, y - y / 4);
                gp.drawLine((x / 3) * 2, y - y / 8, (x / 3) * 2, y - y / 4);
                gp.drawLine(x / 3, y - y / 4, (x / 3) * 2, y - y / 4);
                gp.drawLine((x / 3) + (x / 10), y - y / 4, (x / 3) + (x / 10), y / 4);
                gp.drawLine((x / 3) + (x / 10), y / 4, (x / 2) + (x / 12), y / 4);
                gp.drawLine((x / 2) + (x / 12), y / 4, (x / 2) + (x / 12), (y / 4) + (y / 16));
                break;
            case 7:
                gp.drawLine(x / 3, y - y / 8, (x / 3) * 2, y - y / 8);
                gp.drawLine(x / 3, y - y / 8, x / 3, y - y / 4);
                gp.drawLine((x / 3) * 2, y - y / 8, (x / 3) * 2, y - y / 4);
                gp.drawLine(x / 3, y - y / 4, (x / 3) * 2, y - y / 4);
                gp.drawLine((x / 3) + (x / 10), y - y / 4, (x / 3) + (x / 10), y / 4);
                gp.drawLine((x / 3) + (x / 10), y / 4, (x / 2) + (x / 12), y / 4);
                gp.drawLine((x / 2) + (x / 12), y / 4, (x / 2) + (x / 12), (y / 4) + (y / 16));
                gp.drawOval(((x / 2) + (x / 12)) - x / 26, (y / 4) + (y / 16), x / 13, y / 12);
                break;
            case 8:
                gp.drawLine(x / 3, y - y / 8, (x / 3) * 2, y - y / 8);
                gp.drawLine(x / 3, y - y / 8, x / 3, y - y / 4);
                gp.drawLine((x / 3) * 2, y - y / 8, (x / 3) * 2, y - y / 4);
                gp.drawLine(x / 3, y - y / 4, (x / 3) * 2, y - y / 4);
                gp.drawLine((x / 3) + (x / 10), y - y / 4, (x / 3) + (x / 10), y / 4);
                gp.drawLine((x / 3) + (x / 10), y / 4, (x / 2) + (x / 12), y / 4);
                gp.drawLine((x / 2) + (x / 12), y / 4, (x / 2) + (x / 12), (y / 4) + (y / 16));
                gp.drawOval(((x / 2) + (x / 12)) - x / 26, (y / 4) + (y / 16), x / 13, y / 12);
                gp.drawLine((x / 2) + (x / 12), (y / 4) + (y / 16) + (y / 12), (x / 2) + (x / 12), (y / 2) + (y / 12));
                break;
            case 9:
                gp.drawLine(x / 3, y - y / 8, (x / 3) * 2, y - y / 8);
                gp.drawLine(x / 3, y - y / 8, x / 3, y - y / 4);
                gp.drawLine((x / 3) * 2, y - y / 8, (x / 3) * 2, y - y / 4);
                gp.drawLine(x / 3, y - y / 4, (x / 3) * 2, y - y / 4);
                gp.drawLine((x / 3) + (x / 10), y - y / 4, (x / 3) + (x / 10), y / 4);
                gp.drawLine((x / 3) + (x / 10), y / 4, (x / 2) + (x / 12), y / 4);
                gp.drawLine((x / 2) + (x / 12), y / 4, (x / 2) + (x / 12), (y / 4) + (y / 16));
                gp.drawOval(((x / 2) + (x / 12)) - x / 26, (y / 4) + (y / 16), x / 13, y / 12);
                gp.drawLine((x / 2) + (x / 12), (y / 4) + (y / 16) + (y / 12), (x / 2) + (x / 12), (y / 2) + (y / 12));
                gp.drawLine((x / 2) + (x / 12), y / 2 - y / 12, (x / 2) + (x / 12) - (x / 24), y / 2);
                gp.drawLine((x / 2) + (x / 12), y / 2 - y / 12, (x / 2) + (x / 12) + (x / 24), y / 2);
                break;
            case 10:
                gp.drawLine(x / 3, y - y / 8, (x / 3) * 2, y - y / 8);
                gp.drawLine(x / 3, y - y / 8, x / 3, y - y / 4);
                gp.drawLine((x / 3) * 2, y - y / 8, (x / 3) * 2, y - y / 4);
                gp.drawLine(x / 3, y - y / 4, (x / 3) * 2, y - y / 4);
                gp.drawLine((x / 3) + (x / 10), y - y / 4, (x / 3) + (x / 10), y / 4);
                gp.drawLine((x / 3) + (x / 10), y / 4, (x / 2) + (x / 12), y / 4);
                gp.drawLine((x / 2) + (x / 12), y / 4, (x / 2) + (x / 12), (y / 4) + (y / 16));
                gp.drawOval(((x / 2) + (x / 12)) - x / 26, (y / 4) + (y / 16), x / 13, y / 12);
                gp.drawLine((x / 2) + (x / 12), (y / 4) + (y / 16) + (y / 12), (x / 2) + (x / 12), (y / 2) + (y / 12));
                gp.drawLine((x / 2) + (x / 12), y / 2 - y / 12, (x / 2) + (x / 12) - (x / 24), y / 2);
                gp.drawLine((x / 2) + (x / 12), y / 2 - y / 12, (x / 2) + (x / 12) + (x / 24), y / 2);
                gp.drawLine((x / 2) + (x / 12), (y / 2) + (y / 12), (x / 2) + (x / 12) + (x / 24), y - y / 4 - y / 24);
                gp.drawLine((x / 2) + (x / 12), (y / 2) + (y / 12), (x / 2) + (x / 12) - (x / 24), y - y / 4 - y / 24);
                break;
        }
    }

    /**
     * Starts the whole game
     */
    public void startGame()
    {

        InputWordDLG iwdlg = new InputWordDLG(null, true);
        iwdlg.setVisible(true);
        if (iwdlg.isOk())
        {
            inp = iwdlg.getWord();
        } else
        {
            pgui.showGameChooser();
        }

        if (inp != null)
        {
            inp = inp.trim();
            c = new char[inp.length()];
            c1 = new char[inp.length()];
            inp = inp.toUpperCase();
            for (int i = 0; i < c1.length; i++)
            {
                c1[i] = inp.charAt(i);
            }
            for (int i = 0; i < c.length; i++)
            {
                if (c1[i] == ' ')
                {
                    lenght++;
                    c[i] = ' ';
                } else
                {
                    c[i] = '_';
                }
            }
            for (int i1 = 0; i1 < c.length; i1++)
            {
                output += c[i1] + " ";
            }
            lbAnzeige.setText(output);
            usedChar = new HashSet<>();
            gameRunning = true;
        }
        ftfInput.requestFocus();
    }

    /**
     * Sets all parameter to a start value restarts the game 
     */
    public void restartGame()
    {
        art = 0;
        lenght = 0;
        inp = "";
        output = "";
        ftfInput.setCaretPosition(0);
        startGame();
    }

    /**
     * Compares the searchLetter with usedChar and if it isn't used looks up if the letter is in the word
     * @param searchLetter 
     */
    public void searchLetter(char searchLetter)
    {
        ftfInput.setText("");
        if (!usedChar.contains(searchLetter))
        {
            usedChar.add(searchLetter);
            boolean boo = true;

            for (int i1 = 0; i1 < c1.length; i1++)
            {
                if (c[i1] == searchLetter)
                {
                    boo = false;
                } else if (c1[i1] == searchLetter)
                {
                    c[i1] = searchLetter;
                    lenght += 1;
                    boo = false;
                }
            }

            output = "";
            for (int i = 0; i < c.length; i++)
            {
                output += c[i] + " ";
            }

            if (boo == true)
            {
                art += 1;
            }

            repaint();
            if (art == 10)
            {
                JOptionPane.showMessageDialog(null, "Game Over\n The word was '" + inp + "'", "", JOptionPane.ERROR_MESSAGE);
                gameRunning = false;
            }
            lbAnzeige.setText(output);
            if (lenght == inp.length())
            {
                JOptionPane.showMessageDialog(null, "Congratulation!!\n You won :DD");
                gameRunning = false;
            }

        } else
        {
            JOptionPane.showMessageDialog(null, "Letter already used");
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        pmGame = new javax.swing.JPopupMenu();
        miRestart = new javax.swing.JMenuItem();
        paTop = new javax.swing.JPanel();
        lbAnzeige = new javax.swing.JLabel();
        paZeichne = new javax.swing.JPanel();
        paBottom = new javax.swing.JPanel();
        ftfInput = new javax.swing.JFormattedTextField();

        miRestart.setText("Restart");
        miRestart.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                onRestart(evt);
            }
        });
        pmGame.add(miRestart);

        setLayout(new java.awt.BorderLayout());

        paTop.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 5, 0));
        paTop.setLayout(new java.awt.GridLayout(1, 0));

        lbAnzeige.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        paTop.add(lbAnzeige);

        add(paTop, java.awt.BorderLayout.PAGE_START);

        paZeichne.setBackground(new java.awt.Color(0, 0, 0));
        paZeichne.setForeground(new java.awt.Color(255, 255, 255));
        paZeichne.setComponentPopupMenu(pmGame);

        javax.swing.GroupLayout paZeichneLayout = new javax.swing.GroupLayout(paZeichne);
        paZeichne.setLayout(paZeichneLayout);
        paZeichneLayout.setHorizontalGroup(
            paZeichneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        paZeichneLayout.setVerticalGroup(
            paZeichneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 253, Short.MAX_VALUE)
        );

        add(paZeichne, java.awt.BorderLayout.CENTER);

        paBottom.setLayout(new java.awt.GridLayout(1, 1));

        ftfInput.setBorder(javax.swing.BorderFactory.createTitledBorder("Input"));
        try
        {
            ftfInput.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("L")));
        } catch (java.text.ParseException ex)
        {
            ex.printStackTrace();
        }
        ftfInput.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ftfInput.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                onKeyReleased(evt);
            }
        });
        paBottom.add(ftfInput);

        add(paBottom, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Is called when player releases a key, if key was enter it reads the text of the input field 
     * @param evt 
     */
    private void onKeyReleased(java.awt.event.KeyEvent evt)//GEN-FIRST:event_onKeyReleased
    {//GEN-HEADEREND:event_onKeyReleased
        if (gameRunning)
        {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER)
            {
                String input = ftfInput.getText();
                if (!input.equals(" ") && !input.isEmpty())
                {
                    input = input.toUpperCase();
                    char ch = input.charAt(0);
                    searchLetter(ch);
                }
            }
        }
    }//GEN-LAST:event_onKeyReleased

    /**
     * Function of menuItem Restart, it restarts the game
     * @param evt 
     */
    private void onRestart(java.awt.event.ActionEvent evt)//GEN-FIRST:event_onRestart
    {//GEN-HEADEREND:event_onRestart
        restartGame();
    }//GEN-LAST:event_onRestart


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField ftfInput;
    private javax.swing.JLabel lbAnzeige;
    private javax.swing.JMenuItem miRestart;
    private javax.swing.JPanel paBottom;
    private javax.swing.JPanel paTop;
    private javax.swing.JPanel paZeichne;
    private javax.swing.JPopupMenu pmGame;
    // End of variables declaration//GEN-END:variables
}
