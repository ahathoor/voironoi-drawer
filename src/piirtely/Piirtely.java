/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package piirtely;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author ahathoor
 */
public class Piirtely extends JFrame {

    Pixels p = new Pixels();
    
    class TickListener implements ActionListener {

        JPanel totick;

        public TickListener(JPanel totick) {
            this.totick = totick;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            totick.update(totick.getGraphics());
        }
        
    }
    
   

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

        frame.setSize(1024, 780);
        
        Timer tick = new Timer(100,  new TickListener(p));
    }

    public static void main(String[] args) {
        Piirtely piirtely = new Piirtely();
    }
}
