/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package piirtely;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.TreeSet;

/**
 *
 * @author ahathoor
 */
public class Voronoi {

    int width;
    int height;
    int[][] generators;
    HashMap<Long, ArrayList<Integer>> buckets;
    private int bucketsSq = 64;

    private long bucketHash(int[] xy) {
            return bucketHash(xy, 0, 0);
    }    
    private long bucketHash(int[] xy, int xoffs, int yoffs) {
            long x = xy[0] * bucketsSq / width;
            x+= xoffs;
            long y = xy[1] * bucketsSq / height;
            y+=yoffs;
            return  (x << 32) + y;
    }
    public Voronoi(int width, int height, int gen) {
        bucketsSq = (int)Math.sqrt(gen)/2;
        buckets = new HashMap();
        this.width = width;
        this.height = height;
        this.generators = new int[gen][2];
        for (int i = 0; i < this.generators.length; i++) {
            this.generators[i][0] = new Random().nextInt(width);
            this.generators[i][1] = new Random().nextInt(height);
            
            long bh = bucketHash(generators[i]);
            if (!buckets.containsKey(bh)) {
                buckets.put(bh, new ArrayList());
            }
            buckets.get(bh).add(i);
        }
    }

    public double manhattan_dist(int[] xy, int[] xy2) {
        return Math.abs((xy[0] - xy2[0])) + Math.abs((xy[1] - xy2[1]));
    }

    public double dist(int[] xy, int[] xy2) {
        return Math.sqrt((xy[0] - xy2[0]) * (xy[0] - xy2[0]) + (xy[1] - xy2[1]) * (xy[1] - xy2[1]));
    }

    public int whose(int[] xy) {
        double mind = Integer.MAX_VALUE;
        int mini = -1;
        for (int i = -1; i != 2; i++) {
            for (int j = -1; j != 2; j++) {
                long bh = bucketHash(xy, i, j);
                ArrayList<Integer> neighbours = buckets.get(bh);
                if (neighbours == null)
                    continue;
                for (int k = 0; k < neighbours.size(); k++) {
                    int p = neighbours.get(k);
                    int[] is = generators[p];
                    double dist = dist(is, xy);
                    if (dist < mind) {
                        mind = dist;
                        mini = p;
                    }
                }
            }
        }
        return mini;
    }
}
