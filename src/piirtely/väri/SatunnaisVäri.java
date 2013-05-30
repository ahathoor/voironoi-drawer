/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package piirtely.väri;

import java.awt.Color;
import java.util.Random;

/**
 *
 * @author ahathoor
 */
public class SatunnaisVäri extends IndeksoituVäri {

    int[] värit;
    
    public SatunnaisVäri(int koko) {
        Random r = new Random();
        värit = new int[koko];
        for (int i = 0; i < värit.length; i++) {
            värit[i] += 255<<24;//(r.nextInt(255)<<24);   
            värit[i] += (r.nextInt(255)<<16);
            värit[i] += (r.nextInt(255)<<8);
            värit[i] += (r.nextInt(255));         
        }
    }

    @Override
    public int väri(int i) {
        if (i == -1)
            return Color.pink.getRGB();
        return värit[i%värit.length];
    }
}
