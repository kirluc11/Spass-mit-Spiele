/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.BoxJumper.bl;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

/**
 * BoxJumper for the same called game BoxJumper
 * 
 * @since 12.06.2015
 * @author Lukas Kirchsteiger
 */
public class BoxJumper extends Rectangle2D.Double{
    
    private final Color color = Color.ORANGE;

    /**
     * Returns color of the boxjumper
     * 
     * @return color
     */
    public Color getColor() {
        return color;
    }
    
    
    
}
