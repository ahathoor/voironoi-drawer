/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package piirtely;

import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author mikko
 */
public class Box {
    private Rectangle rect;
    private Point target;

    public Box(int x, int y, int width, int height) {
        rect = new Rectangle(x, y, width, height);
    }

    public Rectangle getRect() {
        return rect;
    }

    public Point getTarget() {
        return target;
    }

    public void setTarget(Point target) {
        this.target = target;
    }
    
    
}
