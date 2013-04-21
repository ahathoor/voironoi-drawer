/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package piirtely.Voronoi;

import piirtely.Voronoi.Voronoi;

/**
 *
 * @author mikko
 */
public class ManhattanVoronoi extends Voronoi{

    public ManhattanVoronoi(int width, int height, int gen) {
        super(width, height, gen);
    }

    @Override
    public double dist(int[] xy, int[] xy2) {
        return Math.abs((xy[0] - xy2[0])) + Math.abs((xy[1] - xy2[1]));
    }
    
}
