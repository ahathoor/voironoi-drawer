/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package piirtely;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import piirtely.väri.IndeksoituVäri;

/**
 *
 * @author ahathoor
 */
public class Pixels extends JPanel{
    BufferedImage bi;
    public Pixels() {
        bi = new BufferedImage(1920, 1024, BufferedImage.TYPE_INT_ARGB);
        Voronoi voronoi = new Voronoi(bi.getWidth(),bi.getHeight(),10000);
        IndeksoituVäri väri = IndeksoituVäri.siniVäri(256);
        for (int i = 0; i < bi.getWidth(); i++) {
            for (int j = 0; j < bi.getHeight(); j++) {
                bi.setRGB(i, j, väri.väri(voronoi.whose(new int[] {i,j})));
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawLine(0, 0, 50, 70);
        g.drawImage(bi.getScaledInstance(1920, 1024, BufferedImage.SCALE_FAST),0,0,null);
    }
    
}
