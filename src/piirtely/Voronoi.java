/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package piirtely;

import java.util.Random;

/**
 *
 * @author ahathoor
 */
public class Voronoi {
    int width;
    int height;
    int[][] generators;

    public Voronoi(int width, int height, int gen) {
        this.width = width;
        this.height = height;
        this.generators = new int[gen][2];
        for (int i = 0; i < this.generators.length; i++) {
            this.generators[i][0] = new Random().nextInt(width);
            this.generators[i][1] = new Random().nextInt(height);
        }
    }
    public double dist(int[] xy, int[] xy2) {
        return Math.sqrt((xy[0]-xy2[0])*(xy[0]-xy2[0])+(xy[1]-xy2[1])*(xy[1]-xy2[1]));
    }
    public int whose(int[] xy) {
        double mind = Integer.MAX_VALUE;
        int mini = -1;
        for (int i = 0; i < generators.length; i++) {
            int[] is = generators[i];
            double dist = dist(is,xy);
            if (dist < mind) {
                mind = dist;
                mini = i;
            }
        }
        return mini;
    }
    
}
