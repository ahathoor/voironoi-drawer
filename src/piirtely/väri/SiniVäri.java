/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package piirtely.väri;

import java.awt.Color;

/**
 *
 * @author ahathoor
 */
public class SiniVäri extends IndeksoituVäri {

    int koko;

    public SiniVäri(int koko) {
        this.koko = koko;
    }

    @Override
    public int väri(int index) {
        if (index == -1)
            return Color.pink.getRGB();
        return (255 << 24) + ((index % koko) * (256 / koko));
    }
}
