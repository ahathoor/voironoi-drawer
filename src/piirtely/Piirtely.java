/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package piirtely;

import javax.swing.JFrame;

/**
 *
 * @author ahathoor
 */
public class Piirtely extends JFrame {

    Pixels p = new Pixels();
    
    public Piirtely() {

    //1. Create the frame.
        JFrame frame = new JFrame("FrameDemo");

    //2. Optional: What happens when the frame closes?
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(p);
    //4. Size the frame.
        frame.pack();

    //5. Show it.
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Piirtely piirtely = new Piirtely();
    }
}
