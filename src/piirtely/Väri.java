/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package piirtely;

import java.awt.Transparency;
import java.awt.image.DataBuffer;
import java.awt.image.IndexColorModel;

/**
 *
 * @author ahathoor
 */
public class Väri {

    static IndexColorModel väri() {
        IndexColorModel cm = new IndexColorModel(8, 3, new int[] {255<<24,255<<16,255<<8}, 0, false, -1, DataBuffer.TYPE_USHORT);
        return cm;
    }
}
