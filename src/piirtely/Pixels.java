/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package piirtely;

import piirtely.väri.SatunnaisVäri;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.IndexColorModel;
import javax.swing.JPanel;
import piirtely.väri.IndeksoituVäri;

/**
 *
 * @author ahathoor
 */
public class Pixels extends JPanel{
    BufferedImage bi;
    public Pixels() {
        bi = new BufferedImage(512, 512, BufferedImage.TYPE_INT_ARGB);
        Voronoi voronoi = new Voronoi(bi.getWidth(),bi.getHeight(),300);
        IndeksoituVäri väri = IndeksoituVäri.siniVäri(2);
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
        g.drawImage(bi.getScaledInstance(512, 512, BufferedImage.SCALE_FAST),0,0,null);
    }
    
}
