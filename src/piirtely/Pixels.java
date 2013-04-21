/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package piirtely;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author ahathoor
 */
public class Pixels extends JPanel implements MouseListener, MouseMotionListener {

    ArrayList<Box> boxes = new ArrayList();
    ArrayList<Box> selected = new ArrayList();

    public Pixels() {
        boxes.add(new Box(10, 10, 10, 10));
        boxes.add(new Box(10, 90, 10, 10));
        boxes.add(new Box(70, 190, 10, 10));
        boxes.add(new Box(570, 190, 10, 10));

        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (dragging) {
            g.drawRect(dragRect.x, dragRect.y, dragRect.width, dragRect.height);
        }
        for (Box box : boxes) {
            g.setColor(Color.RED);
            if (selected.contains(box)) {
                g.setColor(Color.GREEN);
            }
            g.drawRect(box.getRect().x, box.getRect().y, box.getRect().width, box.getRect().height);
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
    }
    boolean dragging = false;
    Point dragStart;
    Point dragEnd;
    Rectangle dragRect = new Rectangle();

    @Override
    public void mousePressed(MouseEvent me) {
        dragging = true;
        selected.clear();
        dragStart = me.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        for (Box box : boxes) {
            if (dragRect.contains(box.getRect())) {
                selected.add(box);
            }
        }
        dragging = false;
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        dragEnd = e.getPoint();
        dragRect.setFrameFromDiagonal(dragStart, dragEnd);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
