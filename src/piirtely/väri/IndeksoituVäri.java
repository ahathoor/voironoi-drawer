/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package piirtely.väri;

/**
 *
 * @author ahathoor
 */
abstract public class IndeksoituVäri {
    abstract public int väri(int index);
    
    public static IndeksoituVäri satunnaisVäri(int koko) {
        return new SatunnaisVäri(koko);
    }
    
    public static IndeksoituVäri siniVäri(int koko) {
        return new SiniVäri(koko);
    }
}
