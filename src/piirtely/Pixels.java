/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package piirtely;

import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author ahathoor
 */
public class Pixels extends JPanel{

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawLine(0, 0, 50, 70);
    }
    
}
