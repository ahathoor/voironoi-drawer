/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package piirtely.väri;

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
        return (255 << 24) + ((index % koko) * (256 / koko));
    }
}
