/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package piirtely.Voronoi;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import piirtely.väri.IndeksoituVäri;

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
        x += xoffs;
        long y = xy[1] * bucketsSq / height;
        y += yoffs;
        return (x << 32) + y;
    }

    public Voronoi(int width, int height, int gen) {
        bucketsSq = (int) Math.sqrt(gen) / 2;
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

    public double dist(int[] xy, int[] xy2) {
        return Math.sqrt((xy[0] - xy2[0]) * (xy[0] - xy2[0]) + (xy[1] - xy2[1]) * (xy[1] - xy2[1]));
    }

    /**
     * Makes increasing size search boxes around the bucket the point is in.
     * @param xy
     * @return 
     */
    public int whose2(int[] xy) {
        double mind = Integer.MAX_VALUE;
        int mini = -1;
        for (int ringsize = 1; ringsize < 100; ringsize++) {
            for (int i = -ringsize; i <= ringsize; i++) {
                for (int j = -ringsize; j <= ringsize; j++) {
                    if (i == ringsize || j == ringsize || i == -ringsize || j == -ringsize || ringsize == 1) {
                        long bh = bucketHash(xy, i, j);
                        ArrayList<Integer> neighbours = buckets.get(bh);
                        if (neighbours == null) {
                            continue;
                        }
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
            }
            if (mini != -1) {
                break;
            }
        }
        return mini;
    }

    public int whose(int[] xy) {
        double mind = Integer.MAX_VALUE;
        int mini = -1;
        if (2 + 2 == 4) {
            return whose2(xy);
        }
        for (int i = -1; i != 2; i++) {
            for (int j = -1; j != 2; j++) {
                long bh = bucketHash(xy, i, j);
                ArrayList<Integer> neighbours = buckets.get(bh);
                if (neighbours == null) {
                    continue;
                }
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

    public BufferedImage getBufferedImage(IndeksoituVäri väri) {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int i = 0; i < bi.getWidth(); i++) {
            for (int j = 0; j < bi.getHeight(); j++) {
                bi.setRGB(i, j, väri.väri(this.whose(new int[]{i, j})));
            }
        }
        return bi;
    }
}
