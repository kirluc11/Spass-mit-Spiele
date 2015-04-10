package games.hangman.gui;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class HangmanPanel extends javax.swing.JPanel
{

    /**
     * Creates new form HangmanPanel
     */
    private int art = 0;
    private String eing;
    private int laenge;
    char[] c;
    private String ausg = "";
    private char[] c1;
    private int help = 0;

    public HangmanPanel()
    {
        initComponents();
        input();
    }

    
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

    public void input()
    {
        eing = JOptionPane.showInputDialog("Bitte geben sie ein Wort ein:\n"
                + "(in Kleinbuchstaben)");
        c = new char[eing.length()];
        c1 = new char[eing.length()];
        for (int i = 0; i < c1.length; i++)
        {
            c1[i] = eing.charAt(i);
        }
        for (int i = 0; i < c.length; i++)
        {
            c[i] = '_';
        }
        for (int i1 = 0; i1 < c.length; i1++)
        {
            ausg += c[i1] + " ";
        }
        lbAnzeige.setText(ausg);
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

        paTop = new javax.swing.JPanel();
        lbAnzeige = new javax.swing.JLabel();
        paZeichne = new javax.swing.JPanel();
        paBottom = new javax.swing.JPanel();
        tfInput = new javax.swing.JTextField();
        btSearch = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        paTop.setBorder(javax.swing.BorderFactory.createTitledBorder("Hangman"));
        paTop.setLayout(new java.awt.GridLayout(1, 0));
        paTop.add(lbAnzeige);

        add(paTop, java.awt.BorderLayout.PAGE_START);

        paZeichne.setBackground(new java.awt.Color(0, 0, 0));
        paZeichne.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout paZeichneLayout = new javax.swing.GroupLayout(paZeichne);
        paZeichne.setLayout(paZeichneLayout);
        paZeichneLayout.setHorizontalGroup(
            paZeichneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        paZeichneLayout.setVerticalGroup(
            paZeichneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 254, Short.MAX_VALUE)
        );

        add(paZeichne, java.awt.BorderLayout.CENTER);

        paBottom.setLayout(new java.awt.GridLayout(1, 2));
        paBottom.add(tfInput);

        btSearch.setText("Search");
        btSearch.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                onSearch(evt);
            }
        });
        paBottom.add(btSearch);

        add(paBottom, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void onSearch(java.awt.event.ActionEvent evt)//GEN-FIRST:event_onSearch
    {//GEN-HEADEREND:event_onSearch
        String str = tfInput.getText();
        char ch = str.charAt(0);
        boolean boo = true;

        for (int i1 = 0; i1 < c1.length; i1++)
        {
            if (c[i1] == ch)
            {
                boo = false;
            } else if (c1[i1] == ch)
            {
                c[i1] = ch;
                help += 1;
                boo = false;
            }
        }

        ausg = "";
        for (int i = 0; i < c.length; i++)
        {
            ausg += c[i] + " ";
        }

        if (boo == true)
        {
            art += 1;
        }

        repaint();
        if (art == 10)
        {
            JOptionPane.showMessageDialog(null, "Game Over\n Das Wort war '" + eing + "'", "", JOptionPane.ERROR_MESSAGE);
        }
        lbAnzeige.setText(ausg);
        if (help == eing.length())
        {
            JOptionPane.showMessageDialog(null, "Glückwunsch!!\nDu hast das Wort erraten :DD");
        }
    }//GEN-LAST:event_onSearch


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btSearch;
    private javax.swing.JLabel lbAnzeige;
    private javax.swing.JPanel paBottom;
    private javax.swing.JPanel paTop;
    private javax.swing.JPanel paZeichne;
    private javax.swing.JTextField tfInput;
    // End of variables declaration//GEN-END:variables
}